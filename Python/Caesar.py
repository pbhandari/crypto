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

def known_plaintext(plaintext, ciphertext, alphabet=alpha):
    '''Both the ciphertext and plaintext are known, derive
    the key'''
    
    return (alphabet.index(ciphertext[0]) - alphabet.index(plaintext[0]))%len(alphabet)

def chosen_ciphertext(cipher, alphabet=alpha):
    '''given access to the encryption machine
       derive the key'''

    #decode the first letter in the alphabet
    decoded_letter = cipher.decrypt(alphabet[0])

    #find the negative shift
    key = alphabet.index(decoded_letter)

    #derive the key
    key = (key * (-1))%len(alphabet)

    return key

def chosen_plaintext(cipher, alphabet=alpha):
    '''given access to the decryption machine
    derive the key'''

    #decode the first letter in the alphabet
    encoded_letter = cipher.decrypt(alphabet[0])

    #find the negative shift
    key = (alphabet.index(encoded_letter))%len(alphabet)
    
    return key


if __name__ == "__main__":
    a = Caesar(3) 
    print(a.encrypt("hello"))
    print(a.decrypt("khoor"))
    print(ciphertext_only("khoor")) 
