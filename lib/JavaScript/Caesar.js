/*******************************
* Written By Adam Ortiz
*implementation of Caeser/shift 
*cipher in JavaScript
********************************/

var alpha = new Array("a", "b", "c", "d", "e", "f", "g", 
			  "h", "i", "j", "k", "l", "m", "n", 
			  "o", "p", "q", "r", "s", "t", "u", 
			  "v", "w", "x", "y", "z");

function mod(n, m) {
        return ((m % n) + n) % n;
}

exports.Caesar = function (shift, alphabet){

    var shift=shift;
    this.alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
     
    this.encrypt=encrypt;
    function encrypt(text)
    {
        /*
        *basic encryption algorithm
        */

        //for other alphabets
        var text_lower = text.toLowerCase();

        //holds the ciphertext
        var ciphertext = "";

        for(var i=0; i<text_lower.length; i++)
        {

	    //place the current letter is in the alphabet
	    var plaintext_index = this.alphabet.indexOf(text_lower[i]);

	    if (plaintext_index != -1)
            {
	        //place the index is shifted 
	        var ciphertext_index = (this.alphabet.length, (plaintext_index + shift));
	
                //retrieve the new letter
                ciphertext = ciphertext + this.alphabet[ciphertext_index];
            }
        }

        return ciphertext;

    }

    this.decrypt=decrypt;
    function decrypt(text)
    {
        /*
        *basic decryption algorithm
        */

        //for other alphabets
        var text_lower = text.toLowerCase();

        //holds the ciphertext
        var plaintext = "";

        for(var i=0; i<text_lower.length; i++)
        {

	    //place the current letter is in the alphabet
	    var ciphertext_index = this.alphabet.indexOf(text_lower[i]);

	    if (ciphertext_index != -1)
            {
	        //place the index is shifted 
	        var plaintext_index = mod(this.alphabet.length,(ciphertext_index - shift));
	
                //retrieve the new letter
                plaintext = plaintext + this.alphabet[plaintext_index];
            }
        }

        return plaintext;
    }

}

function rand_encrypt(plaintext, alphabet)
{
    /*encrypt with a random key*/

    //check the alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 

    //generate random shift
    var random = Math.floor((Math.random()*alphabet.length)+1);

    //initialize cipher
    var cipher = new Caesar(random, alphabet);    

    //encrypt plaintext
    var ciphertext = cipher.encrypt(plaintext);

    return ciphertext
}

function rand_decrypt(ciphertext, alphabet)
{
    /*decrypt with a random key*/

    //check the alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 

    //generate random shift
    var random = Math.floor((Math.random()*alphabet.length)+1);

    //initialize cipher
    var cipher = new Caesar(random, alphabet);

    //decrypt ciphertext
    var plaintext = cipher.decrypt(ciphertext);

    return plaintext;
}

exports.Exploit = function(alphabet){
    
    this.alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    
    this.ciphertext_only=ciphertext_only;
    function ciphertext_only( ciphertext)
    {
        /*Only a copy of the cipher text is known, the best
        * course of action in this case is to try all 
        * possibilities*/

        var all_shifts = new Array();

        for(var i=0; i<this.alphabet.length;i++)
        {
            var cipher = new Caesar(i, this.alphabet);
            all_shifts[i] = cipher.decrypt(ciphertext);
        }

        return all_shifts;

    }
    
    this.known_plaintext=known_plaintext;
    function known_plaintext(plaintext, ciphertext)
    {
        /*a copy of both the plaintext and ciphertext is known,
        *deduce the key*/


        //deduce the key
        var key = mod(this.alphabet.length, (this.alphabet.indexOf(ciphertext[0]) - this.alphabet.indexOf(plaintext[0])));

        return key;
    }

    this.chosen_plaintext=chosen_plaintext;
    function chosen_plaintext(cipher)
    {
        /*access to encryption machine. currently passes a
        * an object instance.
        */

   
        //get encrypted letter
        var coded_letter = cipher.encrypt(this.alphabet[0], this.alphabet);
   
        //the new index is the key used to shift 
        var key = this.alphabet.indexOf(coded_letter);

        return key;
    }

    this.chosen_ciphertext=chosen_ciphertext;
    function chosen_ciphertext(cipher)
    {
        /*access to decryption machine. currently passes a
        * an object instance.
        */

    
        //get encrypted letter
        var decoded_letter = cipher.decrypt(this.alphabet[0], this.alphabet);
   
        //the new index is the key used to shift 
        var key = this.alphabet.indexOf(decoded_letter);

        key = mod(this.alphabet.length, (key * (-1)));

        return key;
    }
}

function sanity_checks(){

    //test initialization encryption and decryption
    var c = new Caesar(3);
    var hey = c.encrypt("hello");
    console.log(hey);
    console.log(c.decrypt(hey));

    //test ciphertext only
    var possible = ciphertext_only(hey);
    console.log(possible);

    //test known  plaintext
    console.log(known_plaintext("hello", "khoor"));

    //test chosen ciphertext and chosen plaintext
    var c = new Caesar(8);
    console.log(chosen_plaintext(c)); 
    console.log(chosen_ciphertext(c));


}

