#!/usr/bin/env bash

cd `dirname $0`

tests=""

if [ "$(uname)" == "Darwin" ]; then
    opts=$(getopt "hf:" -- "$@");
else
    opts=$(getopt -o"hf:" -n${0##*/} -- "$@");
fi
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

for te in *.js; do
    if echo "$TEST_FUNC" | grep -i "${te/Test.js}" > /dev/null ; then
        tests+="$te "
    fi
done

for t in ${tests:-*.js}; do
    echo "**************Test ${t/Test.js}*****************"
    node $t
    echo "**************Test ${t/Test.js}*****************"
    echo
done
