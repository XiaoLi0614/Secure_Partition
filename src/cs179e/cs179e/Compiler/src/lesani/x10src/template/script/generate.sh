#!/bin/sh

. ./setPaths.sh

echo Regenerating lib source ...

# -----------------------------------------------------
rm $SHARED_PATH_SRC/*

# Substitute SDT for Matrix
sed 's/SDT/Int/g' $TEMPLATE_PATH_SRC/SDTMatrix.x10 > $TEMPLATE_PATH_OUT/SDTIntMatrix.x10
sed 's/SDT/Double/g' $TEMPLATE_PATH_SRC/SDTMatrix.x10 > $TEMPLATE_PATH_OUT/SDTDoubleMatrix.x10
sed 's/SDT/Boolean/g' $TEMPLATE_PATH_SRC/SDTMatrix.x10 > $TEMPLATE_PATH_OUT/SDTBooleanMatrix.x10

# Substitute NDT for Matrix
sed 's/NDT/Int/g' $TEMPLATE_PATH_SRC/NDTMatrix.x10 > $TEMPLATE_PATH_OUT/NDTIntMatrix.x10
sed 's/NDT/Double/g' $TEMPLATE_PATH_SRC/NDTMatrix.x10 > $TEMPLATE_PATH_OUT/NDTDoubleMatrix.x10

# Substitute NDT3 for Matrix

sed 's/NDT1/Int/g' $TEMPLATE_PATH_SRC/NDT3Matrix.x10 | sed 's/NDT2/Int/g' | sed 's/NDT3/Int/g' > $TEMPLATE_PATH_OUT/NDT3IntMatrix.x10
sed 's/NDT1/Int/g' $TEMPLATE_PATH_SRC/NDT3Matrix.x10 | sed 's/NDT2/Double/g' | sed 's/NDT3/Double/g' >> $TEMPLATE_PATH_OUT/NDT3IntMatrix.x10
sed 's/NDT1/Double/g' $TEMPLATE_PATH_SRC/NDT3Matrix.x10 | sed 's/NDT2/Int/g' | sed 's/NDT3/Double/g' > $TEMPLATE_PATH_OUT/NDT3DoubleMatrix.x10
sed 's/NDT1/Double/g' $TEMPLATE_PATH_SRC/NDT3Matrix.x10 | sed 's/NDT2/Double/g' | sed 's/NDT3/Double/g' >> $TEMPLATE_PATH_OUT/NDT3DoubleMatrix.x10

# Substitute NDTBoolean for Matrix
#sed 's/NDT/Int/g' $TEMPLATE_PATH_SRC/NDTBooleanMatrix.x10 > $TEMPLATE_PATH_OUT/NDTIntBooleanMatrix.x10
#sed 's/NDT/Double/g' $TEMPLATE_PATH_SRC/NDTBooleanMatrix.x10 > $TEMPLATE_PATH_OUT/NDTDoubleBooleanMatrix.x10

# Make Final Files
cat $TEMPLATE_PATH_OUT/SDTIntMatrix.x10 $TEMPLATE_PATH_OUT/NDTIntMatrix.x10 $TEMPLATE_PATH_OUT/NDT3IntMatrix.x10 $TEMPLATE_PATH_SRC/IntMatrix.x10 > $SHARED_PATH_SRC/IntMatrix.x10
cat $TEMPLATE_PATH_OUT/SDTDoubleMatrix.x10 $TEMPLATE_PATH_OUT/NDTDoubleMatrix.x10 $TEMPLATE_PATH_OUT/NDT3DoubleMatrix.x10 $TEMPLATE_PATH_SRC/DoubleMatrix.x10 > $SHARED_PATH_SRC/DoubleMatrix.x10
cat $TEMPLATE_PATH_OUT/SDTBooleanMatrix.x10 $TEMPLATE_PATH_SRC/BooleanMatrix.x10 > $SHARED_PATH_SRC/BooleanMatrix.x10
#$TEMPLATE_PATH_OUT/NDTIntBooleanMatrix.x10 $TEMPLATE_PATH_OUT/NDTDoubleBooleanMatrix.x10

# -----------------------------------------------------

# Substitute SDTLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/SDTLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/SDTLib.x10
fi
touch $TEMPLATE_PATH_OUT/SDTLib.x10
sed 's/SDT/Int/g' $TEMPLATE_PATH_SRC/SDTLib.x10 >> $TEMPLATE_PATH_OUT/SDTLib.x10
sed 's/SDT/Double/g' $TEMPLATE_PATH_SRC/SDTLib.x10 >> $TEMPLATE_PATH_OUT/SDTLib.x10
sed 's/SDT/Boolean/g' $TEMPLATE_PATH_SRC/SDTLib.x10 >> $TEMPLATE_PATH_OUT/SDTLib.x10

# Substitute NDTLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTLib.x10
sed 's/NDT/Int/g' $TEMPLATE_PATH_SRC/NDTLib.x10 >> $TEMPLATE_PATH_OUT/NDTLib.x10
sed 's/NDT/Double/g' $TEMPLATE_PATH_SRC/NDTLib.x10 >> $TEMPLATE_PATH_OUT/NDTLib.x10

# Substitute NDT3Lib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDT3Lib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDT3Lib.x10
fi
touch $TEMPLATE_PATH_OUT/NDT3Lib.x10
sed 's/NDT1/Int/g' $TEMPLATE_PATH_SRC/NDT3Lib.x10 | sed 's/NDT2/Int/g' | sed 's/NDT3/Int/g' >> $TEMPLATE_PATH_OUT/NDT3Lib.x10
sed 's/NDT1/Int/g' $TEMPLATE_PATH_SRC/NDT3Lib.x10 | sed 's/NDT2/Double/g' | sed 's/NDT3/Double/g' >> $TEMPLATE_PATH_OUT/NDT3Lib.x10
sed 's/NDT1/Double/g' $TEMPLATE_PATH_SRC/NDT3Lib.x10 | sed 's/NDT2/Int/g' | sed 's/NDT3/Double/g' >> $TEMPLATE_PATH_OUT/NDT3Lib.x10
sed 's/NDT1/Double/g' $TEMPLATE_PATH_SRC/NDT3Lib.x10 | sed 's/NDT2/Double/g' | sed 's/NDT3/Double/g' >> $TEMPLATE_PATH_OUT/NDT3Lib.x10

# { Substitute NDT3RelLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDT3RelLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDT3RelLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDT3RelLib.x10
#CMD="sed \"s/NDT1/\$T1/g\" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10"
T1=Int; T2=Int; T3=Int
REL="=="; NAME="eq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">"; NAME="gt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">="; NAME="gteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10

T1=Int; T2=Double; T3=Double
REL="=="; NAME="eq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">="; NAME="gt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">"; NAME="gteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10

T1=Double; T2=Int; T3=Double
REL="=="; NAME="eq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">="; NAME="gt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">"; NAME="gteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10

T1=Double; T2=Double; T3=Double
REL="=="; NAME="eq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">"; NAME="gt"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
REL=">="; NAME="gteq"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3RelLib.x10 >> $TEMPLATE_PATH_OUT/NDT3RelLib.x10
# } Substitute NDT3RelLib


# { Substitute NDTRelLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTRelLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTRelLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTRelLib.x10
NDT=Int
REL="=="; NAME="eq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL=">"; NAME="gt"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL=">="; NAME="gteq"

NDT=Double
REL="=="; NAME="eq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="!="; NAME="neq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="<"; NAME="lt"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL="<="; NAME="lteq"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL=">"; NAME="gt"
sed -e "s/NDT/$NDT/g" -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTRelLib.x10 >> $TEMPLATE_PATH_OUT/NDTRelLib.x10
REL=">="; NAME="gteq"

# } Substitute NDTRelLib


# { Substitute RelLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/RelLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/RelLib.x10
fi
touch $TEMPLATE_PATH_OUT/RelLib.x10

REL="<"; NAME="lt"
sed -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/RelLib.x10 >> $TEMPLATE_PATH_OUT/RelLib.x10
REL="<="; NAME="lteq"
sed -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/RelLib.x10 >> $TEMPLATE_PATH_OUT/RelLib.x10
REL=">"; NAME="gt"
sed -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/RelLib.x10 >> $TEMPLATE_PATH_OUT/RelLib.x10
REL=">="; NAME="gteq"
sed -e "s/REL/$REL/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/RelLib.x10 >> $TEMPLATE_PATH_OUT/RelLib.x10

# } Substitute RelLib

# { Substitute NDT3LogicLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDT3LogicLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
T1=Int; T2=Int; T3=Int
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10

T1=Int; T2=Double; T3=Double
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10

T1=Double; T2=Int; T3=Double
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10

T1=Double; T2=Double; T3=Double
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3LogicLib.x10 >> $TEMPLATE_PATH_OUT/NDT3LogicLib.x10

# } Substitute NDT3LogicLib


# { Substitute NDTLogicLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTLogicLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTLogicLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTLogicLib.x10
NDT=Int
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT/$NDT/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTLogicLib.x10 >> $TEMPLATE_PATH_OUT/NDTLogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT/$NDT/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTLogicLib.x10 >> $TEMPLATE_PATH_OUT/NDTLogicLib.x10

NDT=Double
LOGIC="\&\&"; NAME="and"
sed -e "s/NDT/$NDT/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTLogicLib.x10 >> $TEMPLATE_PATH_OUT/NDTLogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/NDT/$NDT/g" -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTLogicLib.x10 >> $TEMPLATE_PATH_OUT/NDTLogicLib.x10
# } Substitute NDTLogicLib


# { Substitute LogicLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/LogicLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/LogicLib.x10
fi
touch $TEMPLATE_PATH_OUT/LogicLib.x10
LOGIC="\&\&"; NAME="and"
sed -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/LogicLib.x10 >> $TEMPLATE_PATH_OUT/LogicLib.x10
LOGIC="||"; NAME="or"
sed -e "s/LOGIC/$LOGIC/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/LogicLib.x10 >> $TEMPLATE_PATH_OUT/LogicLib.x10

# } Substitute LogicLib


# Math
# { Substitute NDT3MathLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDT3MathLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDT3MathLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDT3MathLib.x10
T1=Int; T2=Int; T3=Int
MATH="+"; NAME="plus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
T3=Double
MATH="as Double \/"; NAME="divide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10

T1=Int; T2=Double; T3=Double
MATH="+"; NAME="plus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10

T1=Double; T2=Int; T3=Double
MATH="+"; NAME="plus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10

T1=Double; T2=Double; T3=Double
MATH="+"; NAME="plus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathLib.x10

# } Substitute NDT3MathLib


# { Substitute NDTMathLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTMathLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTMathLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTMathLib.x10
NDT=Int
MATH="+"; NAME="plus"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10


NDT=Double
MATH="+"; NAME="plus"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="-"; NAME="minus"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathMMLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="*"; NAME="times"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/NDT/$NDT/g" -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathLib.x10
# } Substitute NDTMathLib


# { Substitute MathLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/MathLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/MathLib.x10
fi
touch $TEMPLATE_PATH_OUT/MathLib.x10
MATH="+"; NAME="plus"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathMMLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
MATH="-"; NAME="minus"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathMMLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
MATH="*"; NAME="dotTimes"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathMMLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
MATH="\/"; NAME="dotDivide"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathMMLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
MATH="*"; NAME="times"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10
MATH="\/"; NAME="divide"
sed -e "s/MATH/$MATH/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathLib.x10 >> $TEMPLATE_PATH_OUT/MathLib.x10

# } Substitute MathLib


# MathFun
# { Substitute NDT3MathFunLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10
T1=Int; T2=Int; T3=Int
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

T1=Int; T2=Double; T3=Double
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

T1=Double; T2=Int; T3=Double
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

T1=Double; T2=Double; T3=Double
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT1/$T1/g" -e "s/NDT2/$T2/g" -e "s/NDT3/$T3/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDT3MathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10
# } Substitute NDT3MathFunLib


# { Substitute NDTMathFunLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTMathFunLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTMathFunLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTMathFunLib.x10
NDT=Int
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

NDT=Double
FUN="Math.pow"; NAME="dotPower"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathFunLib.x10

# } Substitute NDTMathFunLib


# { Substitute MathFunLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/MathFunLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/MathFunLib.x10
fi
touch $TEMPLATE_PATH_OUT/MathFunLib.x10
FUN="Math.pow"; NAME="dotPower"
sed -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathFunLib.x10 >> $TEMPLATE_PATH_OUT/MathFunLib.x10

FUN="Math.min"; NAME="min"
sed -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathFunLib.x10 >> $TEMPLATE_PATH_OUT/MathFunLib.x10

FUN="Math.max"; NAME="max"
sed -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/MathFunLib.x10 >> $TEMPLATE_PATH_OUT/MathFunLib.x10

# } Substitute MathFunLib



# { Substitute NDTMathUFunLib
if [ "$(ls -A $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10)" ]; then
    rm $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10
fi
touch $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10
NDT=Int
FUN="Math.abs"; NAME="abs"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathUFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10

NDT=Double
FUN="Math.abs"; NAME="abs"
sed -e "s/NDT/$NDT/g" -e "s/FUN/$FUN/g" -e "s/NAME/$NAME/g" $TEMPLATE_PATH_SRC/NDTMathUFunLib.x10 >> $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10
# } Substitute NDTMathUFunLib


cat $TEMPLATE_PATH_SRC/Lib.x10 $TEMPLATE_PATH_OUT/SDTLib.x10 $TEMPLATE_PATH_OUT/NDTLib.x10 $TEMPLATE_PATH_OUT/NDT3Lib.x10 $TEMPLATE_PATH_OUT/NDT3RelLib.x10 $TEMPLATE_PATH_OUT/NDTRelLib.x10 $TEMPLATE_PATH_OUT/RelLib.x10 $TEMPLATE_PATH_OUT/NDT3LogicLib.x10 $TEMPLATE_PATH_OUT/NDTLogicLib.x10 $TEMPLATE_PATH_OUT/LogicLib.x10 $TEMPLATE_PATH_OUT/NDT3MathLib.x10 $TEMPLATE_PATH_OUT/NDTMathLib.x10 $TEMPLATE_PATH_OUT/MathLib.x10 $TEMPLATE_PATH_OUT/NDT3MathFunLib.x10 $TEMPLATE_PATH_OUT/NDTMathFunLib.x10 $TEMPLATE_PATH_OUT/MathFunLib.x10 $TEMPLATE_PATH_OUT/NDTMathUFunLib.x10 $TEMPLATE_PATH_SCRIPT/Close.x10 > $SHARED_PATH_SRC/Lib.x10

#-----------------------------------------------------

cp $TEMPLATE_PATH_SRC/static/* $SHARED_PATH_SRC/

#-----------------------------------------------------
#The $ vars are evaluated in sed when " is used instead of '.
