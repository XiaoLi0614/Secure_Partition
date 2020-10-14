#!/bin/bash

ScriptPath=`dirname $0`
ThisPath=`pwd`
cd $ScriptPath

echo ===============================================================
files=`ls out`
for f in $files; do
    TestCase=$(basename $f .s)
    ./UnitTest.sh $TestCase
done
echo ===============================================================


cd $ThisPath
