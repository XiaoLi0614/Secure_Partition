#!/bin/bash

echo ---------------------------------------------------------------
echo Test Case: $1
java Parse src/$1.awk > run/out/$1.txt
diff run/out/$1.txt run/target/$1.txt > run/diff/$1.txt
RETVAL=$?
[ $RETVAL -eq 0 ] && echo Success
[ $RETVAL -ne 0 ] && echo Failure
#echo ===============================================================



