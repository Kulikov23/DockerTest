#!/bin/bash
#
# Script Name: get_versions.sh
#
# Author: Viacheslav Timonov
# Date : 17/10/17
#
# Description: The following script reads in a json file called /path/to/file
#              and return the list with the image name and its version:
#              name version (e.g. chrome 61.0)

if [ -z "$1" ]
  then
    echo "The path to a file is not supplied"
fi

grep -o '"image": "[^"]*' "$1" | grep -o '[^/]*$' | sed  -e 's/vnc://g' -e 's/:/ /g' -e 's/_/ /g'