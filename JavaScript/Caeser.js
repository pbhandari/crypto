/*******************************
* Written By Adam Ortiz
*implementation of Caeser/shift 
*cipher in JavaScript
********************************/

var alpha = new Array("a", "b", "c", "d", "e", "f", "g", 
			  "h", "i", "j", "k", "l", "m", "n", 
			  "o", "p", "q", "r", "s", "t", "u", 
			  "v", "w", "x", "y", "z");

function encrypt(text, shift, alphabet)
{
    /*
    *basic encryption algorithm
    */

    //for other alphabets
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    var text_lower = text.toLowerCase();

    //holds the ciphertext
    var ciphertext = "";

    for(var i=0; i<text_lower.length; i++)
    {

	//place the current letter is in the alphabet
	var plaintext_index = alphabet.indexOf(text_lower[i]);

	if (plaintext_index != -1)
        {
	    //place the index is shifted 
	    var ciphertext_index = (plaintext_index + shift)%alphabet.length;
	
            //retrieve the new letter
            ciphertext = ciphertext + alphabet[ciphertext_index];
        }
    }

    return ciphertext;

}


function decrypt(text, shift, alphabet)
{
    /*
    *basic decryption algorithm
    */

    //for other alphabets
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    var text_lower = text.toLowerCase();

    //holds the ciphertext
    var plaintext = "";

    for(var i=0; i<text_lower.length; i++)
    {

	//place the current letter is in the alphabet
	var ciphertext_index = alphabet.indexOf(text_lower[i]);

	if (ciphertext_index != -1)
        {
	    //place the index is shifted 
	    var plaintext_index = (ciphertext_index - shift)%alphabet.length;
	
            //retrieve the new letter
            plaintext = plaintext + alphabet[plaintext_index];
        }
    }

    return ciphertext;
}

function ciphertext_only( ciphertext, alphabet)
{
    /*Only a copy of the cipher text is known, the best
    * course of action in this case is to try all 
    * possibilities*/

    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    var all_shifts = new Array();

    for(var i=0; i<alphabet.length;i++)i
    {
        all_shifts[i] = decrypt(ciphertext, i, alphabet);
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
    var key = (alphabet.indexOf(ciphertext[0]) - alphabet.indexOf(plaintext[0]))%alphabet.length;

    return key;
}

function chosen_plaintext(encryp_func, alphabet)
{
    /*access to encryption*/

    //check for alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
   
    //get encrypted letter
    var coded_letter = encryp_funct(a[0], alphabet);
   
    //the new index is the key used to shift 
    var key = alphabet.indexOf(coded_letter);

    return key;
}

function chosen_ciphertext(decrypt_func, alphabet)
{
    /*access to decryption machine*/

    //check for alphabet
    var alphabet = typeof alphabet !== 'undefined' ? alphabet : alpha; 
    
    //get encrypted letter
    var decoded_letter = decryp_funct(a[0], alphabet);
   
    //the new index is the key used to shift 
    var key = alphabet.indexOf(decoded_letter);

    key = (key * (-1)) % alphabet.length;

    return key;
}


