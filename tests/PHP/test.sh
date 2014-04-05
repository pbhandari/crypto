#!/usr/bin/env bash
cd `dirname $0`
echo '**************Test Caesar*****************'
php ./bin/phpunit.phar CaesarTest.php
echo '**************Test Caesar*****************'

