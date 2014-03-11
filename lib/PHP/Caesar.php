<?php
include(realpath(dirname(__FILE__) . '/../../etc/Alphabet/PHP/Alphabet.php')); 
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
            $this->alphabet = new Alphabet(); 
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
                $x = $this->alphabet->indexOf($plaintext[$i]);
                $encodedIndex = mod(($x + $this->shift), $this->alphabet->sizeOf());
                $ciphertext = $ciphertext . ($this->alphabet->get($encodedIndex)[0]);
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
                $x = $this->alphabet->indexOf($ciphertext[$i]);
                $decodedIndex = mod(($x - $this->shift), $this->alphabet->sizeOf());
                $plaintext = $plaintext . ($this->alphabet->get($decodedIndex)[0]);
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
    
            $this->alphabet = new Alphabet(); 
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
            
            for($i=0; $i < $this->alphabet->sizeOf(); $i++)
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
            return mod( $this->alphabet->indexOf($ciphertext[0]) - $this->alphabet->indexOf($plaintext[0]), $this->alphabet->sizeOf());
        }
        
        /* access to encryption machine. currently passes a
         * an object instance.
         */
        function chosen_plaintext($cipher)
        {
            $ciphertext = $cipher->encrypt($this->alphabet->get(0)[0]);
            return $this->alphabet->indexOf($ciphertext);
        }

        /* access to decryption machine. currently passes a
         * an object instance.
         */
        function chosen_ciphertext($cipher)
        {
            $plaintext = $cipher->decrypt($this->alphabet->get(0)[0]);
            $key = $this->alphabet->indexOf($plaintext);
            $key = mod($key * (-1), $this->alphabet->sizeOf());
            return $key;
        }

    }

?>
