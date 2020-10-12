#!/bin/bash

JARPATH=../../lib

#echo ===============================================================
echo ---------------------------------------------------------------
echo Test Case: $1
#echo ---------------------------------------------------------------
echo Running out ...
#java -jar vapor.jar run -mips <input-file>
java -jar $JARPATH/vapor.jar run -mips out/$1.vaporm > run/out/$1.txt
#echo ---------------------------------------------------------------
echo Running target ...
java -jar $JARPATH/vapor.jar run src/$1.vapor > run/target/$1.txt
diff run/out/$1.txt run/target/$1.txt > run/diff/$1.txt
RETVAL=$?
[ $RETVAL -eq 0 ] && echo Success
[ $RETVAL -ne 0 ] && echo Failure
#echo ===============================================================



