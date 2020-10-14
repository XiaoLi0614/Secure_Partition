#!/bin/bash

JARPATH=../../lib

#echo ===============================================================
echo ---------------------------------------------------------------
echo Test Case: $1
#echo ---------------------------------------------------------------
#echo Out prog:
#java -jar mars.jar nc <input-file>
java -jar $JARPATH/mars.jar nc out/$1.s > run/out/$1.txt
#echo ---------------------------------------------------------------
#echo Src prog:
java -jar $JARPATH/vapor.jar run -mips src/$1.vaporm > run/target/$1.txt
cat NewLine.txt >> run/target/$1.txt
#java -jar $JARPATH/mars.jar nc target/$1.s > run/target/$1.txt
diff run/out/$1.txt run/target/$1.txt > run/diff/$1.txt
RETVAL=$?
[ $RETVAL -eq 0 ] && echo Success
[ $RETVAL -ne 0 ] && echo Failure
#echo ===============================================================



