<?php 
/*******************************
 * By Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in PHP.
 ********************************/
function mod($n, $m) {
    return (($n % $m) + $m) % $m;
}

    class Caesar 
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
            $this->alphabet = $alphabetVal;
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
                $encodedIndex = mod(($x + $this->shift), sizeOf($this->alphabet));
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
                $decodedIndex = mod(($x - $this->shift), sizeOf($this->alphabet));
                $plaintext = $plaintext . $this->alphabet[$decodedIndex];
            }
            return $plaintext;
        }
        
    }

    /*
     * class which holds the known exploits for caesar cipher
     */
    class Exploits
    {
        
        private $alphabet;

        function __construct()
        {
            $argn = func_num_args();
            $argv = func_get_args();

            if (method_exists($this, $f='__construct'.$argn))
            {
                call_user_func_array(array($this,$f), $argv);
            }
        }

        function __construct0()
        {
    
            $this->alphabet = array( 0=>"a", 1=>"b", 2=>"c", 3=>"d", 4=>"e", 
                               5=>"f", 6=>"g", 7=>"h", 8=>"i", 9=>"j", 
                               10=>"k", 11=>"l", 12=>"m", 13=>"n", 14=>"o", 
                               15=>"p", 16=>"q", 17=>"r", 18=>"s", 19=>"t", 
                               20=>"u", 21=>"v", 22=>"w", 23=>"x", 24=>"y", 
                               25=>"z");
        }

        function __construct1($alphabetVal)
        {
            $this->alphabet = $alphabetVal;
        }

        /* Only a copy of the cipher text is known, the best
         * course of action in this case is to try all 
         * possibilities.*/
        function ciphertext_only($ciphertext)
        {
            
            for($i=0; $i < sizeOF($this->alphabet); $i++)
            {
                $cipher = new Caesar($i, $this->alphabet);
                echo $cipher->decrypt($ciphertext) . "\n";
            }
        }
        
        /* a copy of both the plaintext and ciphertext is known,
         * deduce the key
         */
        function known_plaintext($plaintext, $ciphertext)
        {
            return mod((array_search($ciphertext[0], $this->alphabet) - array_search($plaintext[0], $this->alphabet)), sizeOf($this->alphabet));
        }
        
        /* access to encryption machine. currently passes a
         * an object instance.
         */
        function chosen_plaintext($cipher)
        {
            $ciphertext = $cipher->encrypt($this->alphabet[0]);
            return array_search($ciphertext, $this->alphabet);
        }

        /* access to decryption machine. currently passes a
         * an object instance.
         */
        function chosen_ciphertext($cipher)
        {
            $plaintext = $cipher->decrypt($this->alphabet[0]);
            $key = array_search($plaintext, $this->alphabet);
            $key = mod($key * (-1), sizeOf($this->alphabet));
            return $key;
        }

    }

    $c = new Caesar(3);
    echo $c->encrypt("hello") . "\n"; 
    echo $c->decrypt("khoor") . "\n";
    $e = new Exploits();
    $e->ciphertext_only("khoor");
    echo $e->known_plaintext("hello", "khoor") . "\n";
    echo $e->chosen_plaintext($c). "\n";
    echo $e->chosen_ciphertext($c). "\n";
?>
