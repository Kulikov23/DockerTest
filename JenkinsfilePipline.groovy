pipeline {
    agent any
    environment {
        MVN_GOAL = 'clean verify'
        M2_HOME = '/opt/maven'
        X_SCREEN = '1920x1080x16'
    }
    stages {
        stage('Execute tests') {
            steps {
                wrap([$class: 'Xvfb', screen: "${env.X_SCREEN}", autoDisplayName: true]) {
                    sh "${env.M2_HOME}/bin/mvn ${env.MVN_GOAL}"
                }
            }
        }
    }
    post {
        always {
            publishHTML(target: [
                    reportName           : 'Serenity',
                    reportDir            : 'target/site/serenity',
                    reportFiles          : 'index.html',
                    keepAll              : true,
                    alwaysLinkToLastBuild: true,
                    allowMissing         : false
            ])
        }
    }
}