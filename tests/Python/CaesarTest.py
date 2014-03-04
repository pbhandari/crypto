from Caesar import *
from CaesarExploits import *
import unittest

class TestCaesar(unittest.TestCase):
    
    def setup(self):
        self.alphabet =  ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
   
    def test_sanity(self):
        c = Caesar(3)
        e = CaesarExploits()
        self.assertTrue(c.encrypt("hello")=="khoor") 
        self.assertTrue(c.decrypt("khoor")=="hello")
        self.assertTrue(e.known_plaintext("hello", "khoor") == 3)
        self.assertTrue(e.chosen_plaintext(c)==3)
        self.assertTrue(e.chosen_ciphertext(c)==3)

if __name__ == "__main__":
    unittest.main()
