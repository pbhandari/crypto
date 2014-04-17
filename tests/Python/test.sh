#!/usr/bin/env bash

cd `dirname $0`

tests=""

opts=$(getopt -o"hf:" -l"help,func:" -n${0##*/} -- "$@");
eval set -- "${opts%--}"

until [[ -z "$1" ]]; do
    case $1 in
        -f|--func)
            TEST_FUNC=$2
            shift
            ;;
        -h|--help)
            echo "Usage: $0 [-f FUNCTIONS]"
            exit 0
            ;;
    esac
    shift
done

for te in *.py; do
    if echo "$TEST_FUNC" | grep -i "${te/Test.py}" > /dev/null ; then
        tests+="$te "
    fi
done

for t in ${tests:-*.py}; do
    echo "**************Test ${t/Test.py}*****************"
    PYTHONPATH=../../lib/Python/:../../etc/Alphabet/Python/ python2.7 $t
    echo "**************Test ${t/Test.py}*****************"
    echo
done
