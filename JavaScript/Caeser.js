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

function Caeser(shift, alphabet){

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
    var cipher = new Caeser(random, alphabet);    

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
    var cipher = new Caeser(random, alphabet);

    //decrypt ciphertext
    var plaintext = cipher.decrypt(ciphertext);

    return plaintext;
}

function ciphertext_only( ciphertext, alphabet)
{
    /*Only a copy of the cipher text is known, the best
    * course of action in this case is to try all 
    * possibilities*/

    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    var all_shifts = new Array();

    for(var i=0; i<alphabet.length;i++)
    {
        var cipher = new Caeser(i, alphabet);
        all_shifts[i] = cipher.decrypt(ciphertext);
    }

    return all_shifts;

}

function known_plaintext(plaintext, ciphertext, alphabet)
{
    /*a copy of both the plaintext and ciphertext is known,
    *deduce the key*/

    //check for alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 

    //deduce the key
    var key = mod(alphabet.length, (alphabet.indexOf(ciphertext[0]) - alphabet.indexOf(plaintext[0])));

    return key;
}

function chosen_plaintext(cipher, alphabet)
{
    /*access to encryption machine. currently passes a
    * an object instance.
    */

    //check for alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
   
    //get encrypted letter
    var coded_letter = cipher.encrypt(alphabet[0], alphabet);
   
    //the new index is the key used to shift 
    var key = alphabet.indexOf(coded_letter);

    return key;
}

function chosen_ciphertext(cipher, alphabet)
{
    /*access to decryption machine. currently passes a
    * an object instance.
    */

    //check for alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    
    //get encrypted letter
    var decoded_letter = cipher.decrypt(alphabet[0], alphabet);
   
    //the new index is the key used to shift 
    var key = alphabet.indexOf(decoded_letter);

    key = mod(alphabet.length, (key * (-1)));

    return key;
}

function sanity_checks(){

    //test initialization encryption and decryption
    var c = new Caeser(3);
    var hey = c.encrypt("hello");
    console.log(hey);
    console.log(c.decrypt(hey));

    //test ciphertext only
    var possible = ciphertext_only(hey);
    console.log(possible);

    //test known  plaintext
    console.log(known_plaintext("hello", "khoor"));

    //test chosen ciphertext and chosen plaintext
    var c = new Caeser(8);
    console.log(chosen_plaintext(c)); 
    console.log(chosen_ciphertext(c));


}
