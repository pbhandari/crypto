from Affine import *
import unittest

class TestAffine(unittest.TestCase):
    
    def test_sanity(self):
        c = Affine(3, 4)
        self.assertTrue(c.encrypt("hello")=="zqllu")
        self.assertTrue(c.decrypt("zqllu")=="hello")


if __name__ == "__main__":
    unittest.main()


