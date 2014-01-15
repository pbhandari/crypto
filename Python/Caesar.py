#!/usr/bin/python
'''*******************************
*Written By Adam Ortiz
*implementation of Caeser/shift 
*cipher in Python
********************************'''

alpha = ['a', 'b', 'c', 'd', 
            'e', 'f', 'g', 'h', 
            'i', 'j', 'k', 'l', 
            'm', 'n', 'o', 'p', 
            'q', 'r', 's', 't',
            'u', 'v', 'w', 'x',
            'y', 'z']

class Caesar:
        
    def __init__(self, shift, alphabet=alpha):
        self.shift = shift
        self.alphabet = alphabet

    def encrypt(self, plaintext):
        '''encrypt the plaintext with the shift
        according to the given alphabet'''

        plaintext = plaintext.lower()
        ciphertext = ""

        for letter in plaintext:
            if letter in self.alphabet:
                x  =  self.alphabet.index(letter)
                ciphertext += alpha[(x+ self.shift)%len(self.alphabet)]
            else:
                ciphertext += letter
           
        return ciphertext 
    
    def decrypt(self, ciphertext):
        '''decrypt the ciphertext with the shift
        according to the given alphabet'''
       
        ciphertext = ciphertext.lower()
        plaintext = ""

        for letter in ciphertext:
            if letter in self.alphabet:
                x  =  self.alphabet.index(letter)
                plaintext += alpha[(x-self.shift)%len(self.alphabet)]
            else:
                plaintext += letter
           
        return plaintext

def ciphertext_only(ciphertext, alphabet=alpha):
    '''attack used when the only thing the
    attacker has is the ciphertext'''
    
    plaintext = []

    for shift in range(len(alphabet)):
        cipher =  Caesar(shift, alphabet)
        plaintext.append(cipher.decrypt(ciphertext))
    
    return plaintext



if __name__ == "__main__":
    a = Caesar(3) 
    print(a.encrypt("hello"))
    print(a.decrypt("khoor"))
    print(ciphertext_only("khoor")) 
