#! /bin/bash
echo '**************Test Caesar*****************'
PYTHONPATH=../../lib/Python/:../../etc/Alphabet/Python/ python CaesarTest.py
echo '**************Test Caesar*****************'
echo '**************Test Affine*****************'
PYTHONPATH=../../lib/Python/:../../etc/Alphabet/Python/ python AffineTest.py
echo '**************Test Affine*****************'
