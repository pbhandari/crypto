from Alphabet import *

class Affine:
    def __init__(self, shift1, shift2, alphabet=Alphabet("../../etc/Frequency/English.csv")):
        self.a = shift1
        self.b = shift2
        self.alpha = alphabet
        self.a_inv = self.modinv(self.a, len(self.alpha))

    def extended_gcd(self, aa, bb):
        lastremainder, remainder = abs(aa), abs(bb)
        x, lastx, y, lasty = 0, 1, 1, 0
        while remainder:
            lastremainder, (quotient, remainder) = remainder, divmod(lastremainder, remainder)
            x, lastx = lastx - quotient*x, x
            y, lasty = lasty - quotient*y, y
        return lastremainder, lastx * (-1 if aa < 0 else 1), lasty * (-1 if bb < 0 else 1)

    def modinv(self, a, m):
        g, x, y = self.extended_gcd(a, m)
        if g != 1:
            raise ValueError
        return x % m

    def encrypt(self, plaintext):

        plaintext = plaintext.lower()
        ciphertext = ""

        for letter in plaintext:
            if letter in self.alpha:
                x = self.alpha.index(letter)
                ciphertext += self.alpha[(x*self.a + self.b)%len(self.alpha)][0]
            else:
                ciphertext += letter

        return ciphertext 

    def decrypt(self, ciphertext):
        
        ciphertext = ciphertext.lower()
        plaintext = ""

        for letter in ciphertext:
            if letter in self.alpha:
                x = self.alpha.index(letter)
                plaintext += self.alpha[(self.a_inv)*(x - self.b)%len(self.alpha)][0]
            else:
                plaintext += letter

        return plaintext 

