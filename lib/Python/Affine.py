1 from Alphabet import *

class Affine:
    def __init__(self, shift1, shift2, alphabet=Alphabet("../../etc/Frequency/English.csv")):
        self.a = shift1
        self.b = shift2
        self.alpha = alphabet

    def encrypt(plaintext):

        plaintext = plaintext.lower()
        ciphertext = ""

        for letter in plaintext:
            if letter in self.alpha:
                x = self.alpha.index(letter)
                ciphertext += self.alphabet[(x*self.a + self.b)%len(self.alphabet)][0]
            else:
                ciphertext += letter

        return ciphertext 

    def decrypt(ciphertext):
        
        ciphertext = ciphertext.lower()
        plaintext = ""

        for letter in ciphertext:
            if letter in self.alpha:
                x = self.alpha.index(letter)
                plaintext += self.alphabet[(x*self.a + self.b)%len(self.alphabet)][0]
            else:
                plaintext += letter

        return plaintext 

