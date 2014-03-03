#include <iostream>
#include <string>
using namespace std;

/*******************************
 * By Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in C++.
 ********************************/

char alpha [26] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
                   'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
                   'w', 'x', 'y', 'z'};

int mod(int n, int m)
{
    return ((n % m) + m) % m;
}


class Caesar
{
    private:
        int shift;
        char *alphabet;
    public:
        Caesar(int);
        Caesar(int, char*); 
        string encrypt(string);
        string decrypt(string);
        int indexOf(char); //should be changed to T type
};

 /*
  * creates a new Caeser, in order to encrypt and 
  * decrypt from. uses standard alphabet.
  * shiftval - shift value number
  */
Caesar::Caesar(int shiftVal)
{
    shift = shiftVal;
    alphabet = alpha;
}

/*
 * creates a new Caeser, in order to encrypt and 
 * decrypt from
 * shiftval - shift value number
 * alphabetVal - alphabet array to be used 
 */
Caesar::Caesar(int shiftVal, char *alphaVal)
{
    shift = shiftVal;
    alphabet = alphaVal;
}

/* 
 * Caesar funcion used to find the index of 
 * a particular alphabet
 */
int Caesar::indexOf(char character)
{
    for (int i = 0; i < 26; ++i)
    {
        if (character == alphabet[i]) // change the alphabet order according to frequency
        {
            return i;
        } 
    }
    
    return -1;
}

/*
 * Used to encrypt plaintext,
 * param plaintext
 * returns ciphertext 
 */
string Caesar::encrypt(string plaintext)
{
    string ciphertext ="";
    int i, index;

    for (i=0; i<plaintext.size(); ++i)
    {
        index = Caesar::indexOf(plaintext[i]);
        index = mod((index + shift), 26);
        ciphertext = ciphertext + alphabet[index];
    }
    return ciphertext;
}

 /*
  * Used to decrypt ciphertext,
  * param ciphertext 
  * returns plaintext
  */
string Caesar::decrypt(string ciphertext)
{
    string plaintext ="";
    int i, index;

    for (i=0; i<ciphertext.size(); ++i)
    {
        index = Caesar::indexOf(ciphertext[i]);
        index = mod((index - shift), 26);
        plaintext = plaintext + alphabet[index];
    }
    return plaintext;
}

/*
 * class which holds the known exploits for caesar cipher
 */
class Exploits
{
    private:
        char *alphabet;
    public:
        Exploits();
        Exploits(char*);
        int indexOf(char);
        void ciphertext_only(string);
        int known_plaintext(string, string);
        int chosen_plaintext(Caesar);
        int chosen_ciphertext(Caesar);
};


/* 
 * Exploit funcion used to find the index of 
 * a particular alphabet
 */
int Exploits::indexOf(char character)
{
    for (int i = 0; i < 26; ++i)
    {
        if (character == alphabet[i]) // change the alphabet order according to frequency
        {
            return i;
        } 
    }
    
    return -1;
}

/*
 * constructor for Exploits using default alphabet
 */
Exploits::Exploits()
{
    alphabet = alpha;
}

/*
 * constructor for exploits using given alphabet
 */
Exploits::Exploits(char *alphaVal)
{
    alphabet = alphaVal;
}

/* 
 * Only a copy of the cipher text is known, the best
 * course of action in this case is to try all 
 * possibilities.
 */
void Exploits::ciphertext_only(string ciphertext)
{
    int i;
    for(i=0; i < 26; ++i)
    {
        Caesar c (i);
        cout << c.decrypt(ciphertext) + "\n";
    }
}

/* 
 * a copy of both the plaintext and ciphertext is known,
 * deduce the key
 */
int Exploits::known_plaintext(string plaintext, string ciphertext)
{
    int i = mod((indexOf(ciphertext[0]) - indexOf(plaintext[0])), 26); 
    return i;
}

/* 
 * access to encryption machine. currently passes a
 * an object instance.
 */
int Exploits::chosen_plaintext(Caesar cipher)
{
    string a (1,alphabet[0]);
    string c = cipher.encrypt(a);
    int i = indexOf(c[0]);
    return i;
}

/* 
 * access to decryption machine. currently passes a
 * an object instance.
 */
int Exploits::chosen_ciphertext(Caesar cipher)
{
    string a (1,alphabet[0]);
    string c = cipher.decrypt(a);
    int i = indexOf(c[0]);
    i = mod(i * (-1), 26);
    return i;
}

int main() 
{
    Caesar c (3);
    cout << c.encrypt("hello") + "\n";
    cout << c.decrypt("khoor") + "\n";
    Exploits e;
    e.ciphertext_only("khoor");
    cout << e.known_plaintext("hello", "khoor");
    cout << "\n";
    cout << e.chosen_plaintext(c);
    cout << "\n";
    cout << e.chosen_ciphertext(c);
    cout << "\n";
}
