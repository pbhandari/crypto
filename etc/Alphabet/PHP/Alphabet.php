<?php
class Alphabet
{
    private $alphabet;
    private $frequency;
    private $length;

    function __construct()
    {
        $argn = func_num_args();
        $argv = func_get_args();
        if (method_exists($this, $f='__construct'.$argn))
        {
            call_user_func_array(array($this, $f), $argv);
        }
    }

    function __construct0()
    {
        $lines = file("../../etc/Frequency/English.csv");
        foreach ($lines as $line_num => $line)
        {
            $lineSplit = preg_split( "/,/",$line);
            $this->alphabet[$line_num] = $lineSplit[0];
            $this->frequency[$line_num] = $lineSplit[1];
            $this->length = $line_num;
        }
    }

    function __construct1($fname)
    {
        $lines = file($fname);
        foreach ($lines as $line_num => $line)
        {
            $lineSplit = preg_split( "/,/",$line);
            $this->alphabet[$line_num] = $lineSplit[0];
            $this->frequency[$line_num] = $lineSplit[1];
            $this->length = $line_num;
        }
    }

    function indexOf($item)
    {
        return array_search($item, $this->alphabet);
    }

    function get($i)
    {
        return array( 0 => $this->alphabet[$i], 1 => $this->frequency[$i]);
    }

    function sizeOf()
    {
        return $this->length;
    }
}

?>
