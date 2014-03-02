#include <iostream>
#include <string>
using namespace std;

char alpha [26] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
                   'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
                   'w', 'x', 'y', 'z'};

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

Caesar::Caesar(int shiftVal)
{
    shift = shiftVal;
    alphabet = alpha;
}

Caesar::Caesar(int shiftVal, char *alphaVal)
{
    shift = shiftVal;
    alphabet = alphaVal;
}

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

string Caesar::encrypt(string plaintext)
{
    string ciphertext ="";
    int i, index;

    for (i=0; i<plaintext.size(); ++i)
    {
        index = Caesar::indexOf(plaintext[i]);
        index = (index + shift)%26;
        ciphertext = ciphertext + alphabet[index];
    }
    return ciphertext;
}

string Caesar::decrypt(string ciphertext)
{
    string plaintext ="";
    int i, index;

    for (i=0; i<ciphertext.size(); ++i)
    {
        index = Caesar::indexOf(ciphertext[i]);
        index = (index - shift)%26;
        plaintext = plaintext + alphabet[index];
    }
    return plaintext;
}

class Exploits
{
    private:
        char *alphabet;
    public:
        Exploits();
        Exploits(char*);
        void ciphertext_only(string);
        int known_plaintext(string);
        int chosen_plaintext(string);
        int chosen_ciphertext(string);
};

Exploits::Exploits()
{
    alphabet = alpha;
}

Exploits::Exploits(char *alphaVal)
{
    alphabet = alphaVal;
}

void Exploits::ciphertext_only(string ciphertext)
{
    int i;
    for(i=0; i < alphabet.size(); ++i)
    {
        Caesar c (i);
        cout << c.decrypt(ciphertext);
    }
}

int main() 
{
    Caesar c (3);
    cout << c.encrypt("hello") + "\n";
    cout << c.decrypt("khoor") + "\n";
}
