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


class CaesarExploits:
    def __init__(self, alphabet=alpha):
        self.alphabet = alphabet


    def ciphertext_only(self, ciphertext):
        '''attack used when the only thing the
        attacker has is the ciphertext'''
    
        plaintext = []

        for shift in range(len(self.alphabet)):
            cipher =  Caesar(shift, self.alphabet)
            plaintext.append(cipher.decrypt(ciphertext))
    
        return plaintext

    def known_plaintext(self, plaintext, ciphertext):
        '''Both the ciphertext and plaintext are known, derive
        the key'''
    
        return (self.alphabet.index(ciphertext[0]) - self.alphabet.index(plaintext[0]))%len(self.alphabet)

    def chosen_ciphertext(self, cipher):
        '''given access to the encryption machine
        derive the key'''

        #decode the first letter in the alphabet
        decoded_letter = cipher.decrypt(self.alphabet[0])

        #find the negative shift
        key = self.alphabet.index(decoded_letter)

        #derive the key
        key = (key * (-1))%len(self.alphabet)

        return key 


    def chosen_plaintext(self, cipher):
        '''given access to the decryption machine
        derive the key'''

        #decode the first letter in the alphabet
        encoded_letter = cipher.encrypt(self.alphabet[0])

        #find the negative shift
        key = (self.alphabet.index(encoded_letter))%len(self.alphabet)

        return key
