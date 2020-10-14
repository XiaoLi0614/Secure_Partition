#!/bin/sh

clear
# Inputs
# $FileDir$ $FilePackage$ $FileName$
scp -r $1 lesani@samoa.cs.ucla.edu:~/CopyFolder/
ssh lesani@samoa.cs.ucla.edu "scp -r ~/CopyFolder/$2 lesani@131.179.80.170:~/CopyFolder/; ssh lesani@131.179.80.170 'cd /home/lesani/CopyFolder/$2;/usr/local/MATLAB/R2010b/bin/matlab -nodesktop -r $3'"


