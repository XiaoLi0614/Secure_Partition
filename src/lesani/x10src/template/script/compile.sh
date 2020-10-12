#!/bin/sh

. setPaths.sh

# -----------------------------------------------------

# I wanted to compile the lib only once.
# We don't need to compile the lib together with every program.
# It doesn't work. X10 compiler compiles the lib every time.

echo Compiling lib ...
cp $TEMPLATE_PATH_SCRIPT/Main.x10 $SHARED_PATH_SRC
x10c++ -d $SHARED_PATH_OUT -MAIN_CLASS=Main `find $SHARED_PATH_SRC -name "*.x10"`
rm $SHARED_PATH_SRC/Main.x10
rm $SHARED_PATH_OUT/Main*

# -----------------------------------------------------
