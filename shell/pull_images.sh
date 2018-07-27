#!/bin/bash
#
# Script Name: pull_images.sh
#
# Author: Viacheslav Timonov
# Date : 17/10/17
#
# Description: The following script reads in a json file called /path/to/file
#              and pull docker images

if [ -z "$1" ]
  then
    echo "The path to a file is not supplied"
fi

grep -o '"image": "[^"]*' "$1" | grep -o '[^"]*$' | xargs -I{} docker pull {}