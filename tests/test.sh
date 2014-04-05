#!/usr/bin/env bash

SCRIPT_PATH="` cd \`dirname $0\` ; pwd -P `"

if [ $# -gt 0 ]; then
    TEST_DIRS="$@"
elif [ x"$CRYPTO_DEFAULT_TESTS" != x ]; then
    TEST_DIRS=$CRYPTO_DEFAULT_TESTS
else
    TEST_DIRS="`cd $SCRIPT_PATH;\
                find . -maxdepth 1 ! -path . -type d -printf '%f '`"
fi

{   IFS=" "
    for dir in $TEST_DIRS; do
        cd $SCRIPT_PATH/$dir
        echo "**************Test $dir*******************"
        $SCRIPT_PATH/$dir/test.sh
        echo "**************Test $dir*******************"
    done
}
