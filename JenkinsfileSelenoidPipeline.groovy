def browsers
def get_versions_sh = '/shell/get_versions.sh'
def pull_images_sh = '/shell/pull_images.sh'
def browsers_conf_folder = 'browsers/'

node {
    stage 'Checkout'
    checkout scm
    echo 'Getting browsers versions'
    browsers = sh(script: "sh " +
            "'${pwd()}${get_versions_sh}' " +
            "'${pwd()}${browsers_conf_folder}browsers.json'", returnStdout: true).trim()
}

pipeline {
    agent any
    parameters {
        choice(name: 'browser_one', choices: "${browsers}", description: 'Browser #1')
        choice(name: 'browser_two', choices: "${browsers}", description: 'Browser #2 (If the parallel execution is selected)')
        booleanParam(defaultValue: false, description: 'Run in parallel ?', name: 'parallel')
        booleanParam(defaultValue: false, description: 'Enable VNC ?', name: 'vncenabled')
    }
    environment {
        MVN_GOAL = "clean verify"
        M2_HOME = '/usr/share/maven'
        REMOTE_URL = "http://localhost:4444/wd/hub"
        SELENOID_URL = 'http://selenoid:4444'
        SELENOID_UI_PORT = '8081'
    }
    stages {
        stage('Set up browsers') {
            steps {
                sh """                                 
                        '${pwd()}${pull_images_sh}' '${pwd()}${browsers_conf_folder}'browsers.json
                   """
            }
        }
        stage('Start Selenoid') {
            steps {
                sh """                                 
                        docker run -d --name selenoid                                \\
                        -p 4444:4444                                                 \\
                        -v '${pwd()}${browsers_conf_folder}':/etc/selenoid:ro        \\
                        -v /var/run/docker.sock:/var/run/docker.sock                 \\
                        aerokube/selenoid:latest-release
                    """
            }
        }
        stage('Start Selenoid UI') {
            steps {
                sh "docker run -d --name selenoid-ui --link selenoid -p ${env.SELENOID_UI_PORT}:8080 aerokube/selenoid-ui --selenoid-uri=${env.SELENOID_URL}"
            }
        }
        stage('Run Tests') {
            parallel {
                stage('Test on browser 2') {
                    when {
                        expression { return params.parallel }
                    }
                    agent {
                        docker {
                            image 'maven:3-jdk-8-alpine'
                            args "-v maven-repository-two:/root/.m2 --net=host"
                        }
                    }
                    steps {
                        script {
                            def version = (params.browser_two =~ /[0-9]{2,5}/)[0]
                            def browser = params.browser_two.replaceAll(/[^a-z]/, "")
                            sh "{env.M2_HOME}/bin/mvn ${env.MVN_GOAL} " +
                                    "-Dwebdriver.remote.url=${env.REMOTE_URL}" +
                                    " -Dwebdriver.remote.driver=${browser}" +
                                    " -Dserenity.driver.capabilities=enableVNC:${params.vncenabled}" +
                                    " -Dwebdriver.remote.browser.version=${version}"
                        }
                    }
                    post {
                        always {
                            publishHTML(target: [
                                    reportName           : 'Serenity browser 2',
                                    reportDir            : 'target/site/serenity',
                                    reportFiles          : 'index.html',
                                    keepAll              : true,
                                    alwaysLinkToLastBuild: true,
                                    allowMissing         : false
                            ])
                        }
                    }
                }
                stage('Test on browser 1') {
                    agent {
                        docker {
                            image 'maven:3-jdk-8-alpine'
                            args "-v maven-repository-one:/root/.m2 --net=host"
                        }
                    }
                    steps {
                        script {
                            def version = (params.browser_one =~ /[0-9]{2,5}/)[0]
                            def browser = params.browser_one.replaceAll(/[^a-z]/, "")
                            sh "{env.M2_HOME}/bin/mvn ${env.MVN_GOAL} " +
                                    "-Dwebdriver.remote.url=${env.REMOTE_URL}" +
                                    " -Dwebdriver.remote.driver=${browser}" +
                                    " -Dserenity.driver.capabilities=enableVNC:${params.vncenabled}" +
                                    " -Dwebdriver.remote.browser.version=${version}"
                        }
                    }
                    post {
                        always {
                            publishHTML(target: [
                                    reportName           : 'Serenity browser 1',
                                    reportDir            : 'target/site/serenity',
                                    reportFiles          : 'index.html',
                                    keepAll              : true,
                                    alwaysLinkToLastBuild: true,
                                    allowMissing         : false
                            ])
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            sh """       
                        echo 'Stopping containers'
                        docker stop selenoid && docker rm selenoid
                        docker stop selenoid-ui && docker rm selenoid-ui
               """
        }
    }
}