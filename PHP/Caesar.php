<?php 
/*******************************
 * By Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in PHP.
 ********************************/
    class Caeser 
    {

        private $shift;
        private $alphabet;
        
        /*
        * Constructor which calls on the other constructors 
        * to simulate function overloading. 
        */
        function __construct()
        {
            $argn = func_num_args();
            $argv = func_get_args();

            if (method_exists($this, $f='__construct'.$argn))
            {
                call_user_func_array(array($this,$f), $argv);
            }
        }


        /*
        * creates a new Caeser, in order to encrypt and 
        * decrypt from
        * shiftval - shift value number
        * alphabetVal - alphabet array to be used 
        */
        function __construct2($shiftVal, $alphabetVal)
        {
            $this->shift = $shiftVal;
            $this->alphabet = alphabetVal;
        }

        /*
        * creates a new Caeser, in order to encrypt and 
        * decrypt from. uses standard alphabet.
        * shiftval - shift value number
        */
        function __construct1($shiftVal)
        {
            $this->shift = $shiftVal;
            
            $this->alphabet = array( 0=>"a", 1=>"b", 2=>"c", 3=>"d", 4=>"e", 
                               5=>"f", 6=>"g", 7=>"h", 8=>"i", 9=>"j", 
                               10=>"k", 11=>"l", 12=>"m", 13=>"n", 14=>"o", 
                               15=>"p", 16=>"q", 17=>"r", 18=>"s", 19=>"t", 
                               20=>"u", 21=>"v", 22=>"w", 23=>"x", 24=>"y", 
                               25=>"z");
        }
     
        /*
        * Used to encrypt plaintext,
        * param plaintext
        * returns ciphertext 
        */
        function encrypt($plaintext)
        {
           $plaintext = strtolower($plaintext);
           $ciphertext = "";
        
            for ( $i = 0; $i < strlen($plaintext); $i++ )
            {
                $x = array_search($plaintext[$i],$this->alphabet);
                $encodedIndex = ($x + $this->shift) % sizeOf($this->alphabet);
                $ciphertext = $ciphertext . $this->alphabet[$encodedIndex];
            }
            return $ciphertext;
        }
       
         
        /*
        * Used to decrypt ciphertext,
        * param ciphertext 
        * returns plaintext
        */
        function decrypt($ciphertext)
        {
           $ciphertext = strtolower($ciphertext);
           $plaintext = "";
        
            for ( $i = 0; $i < strlen($ciphertext); $i++ )
            {
                $x = array_search($ciphertext[$i],$this->alphabet);
                $decodedIndex = ($x - $this->shift) % sizeOf($this->alphabet);
                $plaintext = $plaintext . $this->alphabet[$decodedIndex];
            }
            return $plaintext;
        }
        
    }

    $c = new Caeser(3);
    echo $c->encrypt("hello") . "\n"; 
    echo $c->decrypt("khoor") . "\n";

?>
