#!/usr/bin/env bash

SCRIPT_PATH="$(cd $(dirname $0) ; pwd -P )"

if [[ "$(uname)" == "Darwin" ]]; then
    opts=$(getopt "hl:f:" -- "$@");
else
    opts=$(getopt -o"hl:f:" -n${0##*/} -- "$@");
fi

if [[ $? != 0 ]]; then
    echo "Usage: $0 [-l LANGUAGES] [-f FUNCTIONS]"
    exit 1
fi

eval set -- "${opts%--}"

until [[ -z "$1" ]]; do
    case $1 in
        -l|--lang)
            TEST_LANG=$2
            shift
            ;;
        -f|--func)
            TEST_FUNC=$2
            shift
            ;;
        -h|--help)
            echo "Usage: $0 [-l LANGUAGES] [-f FUNCTIONS]"
            exit 0
            ;;
    esac
    shift
done

if [[ -z "$TEST_LANG" ]]; then
    TEST_LANG="`cd $SCRIPT_PATH;
                find . -maxdepth 1 ! -path . -type d -printf '%f '`"
fi

{
    for lang in $TEST_LANG; do
        echo "**************Test $lang*******************"
        $SCRIPT_PATH/$lang/test.sh ${TEST_FUNC+"-f $TEST_FUNC"}
        echo "**************Test $lang*******************"
        echo
    done
}
