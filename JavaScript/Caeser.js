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
    var ciphertext = "";

    for(var i=0; i<text_lower.length; i++)
    {

	//place the current letter is in the alphabet
	var plaintext_index = alphabet.indexOf(text_lower[i]);

	if (plaintext_index != -1)
        {
	    //place the index is shifted 
	    var ciphertext_index = (plaintext_index - shift)%alphabet.length;
	
            //retrieve the new letter
            ciphertext = ciphertext + alphabet[ciphertext_index];
        }
    }

    return ciphertext;
}

function ciphertext()
{
    /*Only a copy of the cipher text is known*/
}

function known_plaintext()
{
    /*a copy of both the plaintext and ciphertext is known*/
}

function chosen_plaintext()
{
   /*access to encryption*/
}

function chosen_ciphertext()
{
   /*access to decryption machine*/
}


