require '../../etc/Alphabet/Ruby/Alphabet.rb'

# This is an implementation of the Affine Cipher done in Ruby
#
# Author::      Prajjwal Bhandari(mailto:pbhandari@pbhandari.ca)

# Returns the modular inverse of a in mod m. This code is a
# slightly modified version of the one at
# [Rosetta Code](http://rosettacode.org/wiki/Modular_inverse#Ruby)
#
# Params:
# +a+:: The number for which to find the modular inverse
# +m+:: The number which limits modular space
#
# Returns:
# a^-1 (mod m)
def invmod(a, m)

    raise "Cowardly refusing to find #{a} inverse (mod 0)" if m == 0

    last_rem, rem = a.abs, m.abs
    old_inv,  inv = 0, 1
    until rem == 0
        last_rem, (quot, rem) = rem, last_rem.divmod(rem)
        old_inv,  inv         = inv - quot * old_inv, old_inv
    end

    raise "gcd of #{a} and #{m} is not 1" unless last_rem == 1

    (inv * (a < 0 ? -1 : 1)) % m
end

# This class contains the implementaiton of the Affine Cipher. The
# encrypt and decrypt methods do the all the hard work required.
class Affine
    def initialize(shift1, shift2, alphabet="../../etc/Frequency/English.csv")
        @a = shift1
        @b = shift2
        @alph = alphabet.is_a?(Alphabet) ? alphabet : Alphabet.new(alphabet)
        @inv_a = invmod(@a, @alph.length)
    end

    # Encrypts the given plaintext using an Affine Cipher
    #
    # Params:
    # +plaintext+:: The plaintext to encrypt
    #
    # Returns:
    # The plaintext encrypted using the Affine Cipher.
    def encrypt(plaintext)
        plaintext.downcase.each_char.inject("") do |cipher, letter|
            i = @alph.index(letter)
            cipher + (i.nil? ? letter
                             : @alph.at(((i * @a) + @b) % @alph.length)[0])
        end
    end

    # Decrypts the ciphertext, encrypted using the Affine Cipher, into plaintext
    #
    # Params:
    # +ciphertext+:: The ciphertext to decrypt
    #
    # Returns:
    # The ciphertext decrypted using the Affine Cipher.
    def decrypt(ciphertext)
        ciphertext.downcase.each_char.inject("") do |plain, letter|
            i = @alph.index(letter)
            plain + (i.nil? ? letter
                            : @alph.at(@inv_a * (i - @b) % @alph.length)[0])
        end
    end
end
