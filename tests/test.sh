#!/usr/bin/env bash

SCR_PATH="` cd \`dirname $0\` ; pwd -P `"

if [ $# -gt 0 ]; then
    TEST_DIRS="$@"
else
    TEST_DIRS="`cd $SCR_PATH; find . -maxdepth 1 ! -path . -type d -printf '%f '`"
fi
{ IFS=" "
    for dir in $TEST_DIRS; do
        cd $SCR_PATH/$dir
        echo "**************Test $dir*******************"
        $SCR_PATH/$dir/test.sh
        echo "**************Test $dir*******************"
    done
}
