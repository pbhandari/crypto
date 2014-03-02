#include <iostream>
#include <string>
using namespace std;

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
        index = mod((index + shift), 26);
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
        index = mod((index - shift), 26);
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
        int indexOf(char);
        void ciphertext_only(string);
        int known_plaintext(string, string);
        int chosen_plaintext(Caesar);
        int chosen_ciphertext(Caesar);
};


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
    for(i=0; i < 26; ++i)
    {
        Caesar c (i);
        cout << c.decrypt(ciphertext) + "\n";
    }
}

int Exploits::known_plaintext(string plaintext, string ciphertext)
{
    int i = mod((indexOf(ciphertext[0]) - indexOf(plaintext[0])), 26); 
    return i;
}

int Exploits::chosen_plaintext(Caesar cipher)
{
    string a (1,alphabet[0]);
    string c = cipher.encrypt(a);
    int i = indexOf(c[0]);
    return i;
}

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
