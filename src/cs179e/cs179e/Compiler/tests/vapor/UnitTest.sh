#!/bin/bash

JARPATH=../../lib

#echo ===============================================================
echo ---------------------------------------------------------------
echo Test Case: $1
#echo ---------------------------------------------------------------
#echo Out prog:
java -jar $JARPATH/vapor.jar run out/$1.vapor > run/out/$1.txt
#echo ---------------------------------------------------------------
#echo Target prog:
#java -jar $JARPATH/vapor.jar run target/$1.vapor > run/target/$1.txt
javac -d run/bin src/$1.java
java  -cp $CLASSPATH::run/bin $1 > run/target/$1.txt
diff run/out/$1.txt run/target/$1.txt > run/diff/$1.txt
RETVAL=$?
[ $RETVAL -eq 0 ] && echo Success
[ $RETVAL -ne 0 ] && echo Failure
#echo ===============================================================


#java -jar vapor.jar run -mips <input-file>

