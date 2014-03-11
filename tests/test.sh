#! /bin/bash


cd ./Java
echo '**************Test Java*******************'
./test.sh
echo '**************Test Java*******************'
cd ../
cd ./Python
echo '**************Test Python*****************'
./test.sh
echo '**************Test Python*****************'
cd ../
echo '**************Test PHP********************'
cd ./PHP
./test.sh
cd ../
echo '**************Test PHP********************'
echo '**************Test Ruby*******************'
cd ./Ruby
./test.sh
cd ../
echo '**************Test Ruby*******************'
