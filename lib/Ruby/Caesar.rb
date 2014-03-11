=begin
*******************************
 * By Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in PHP.
*******************************
=end
def mod(n, m)
    return ((n % m) + m) % m
end

class Caesar

=begin
    * creates a new Caeser, in order to encrypt and 
    * decrypt from
    * shiftval - shift value number
    * alphabetVal - alphabet array to be used 
=end
    def initialize(shiftVal, alphaVal=('a'..'z').to_a)
        @shift = shiftVal
        @alphabet = alphaVal
    end
    
=begin
    * Used to encrypt plaintext,
    * param plaintext
    * returns ciphertext 
=end
    def encrypt(plaintext)
        ciphertext = ""
        for i in 0...plaintext.length
            key = @alphabet.index(plaintext[i]);
            key = mod(key+@shift, @alphabet.length)
            ciphertext = ciphertext + @alphabet[key]
        end
        return ciphertext
    end

=begin
    * Used to decrypt ciphertext,
    * param ciphertext 
    * returns plaintext
=end
    def decrypt(ciphertext)
        plaintext = ""
        for i in 0...ciphertext.length
            key = @alphabet.index(ciphertext[i]);
            key = mod(key-@shift, @alphabet.length)
            plaintext = plaintext + @alphabet[key]
        end
        return plaintext
    end
end

=begin
* class which holds the known exploits for caesar cipher
=end
class Exploits

    def initialize(alphaVal=('a'..'z').to_a)
        @alphabet = alphaVal
    end

=begin
    * Only a copy of the cipher text is known, the best
    * course of action in this case is to try all 
    * possibilities.
=end
    def ciphertext_only(ciphertext)
        for i in 0...@alphabet.length
            c = Caesar.new(i)
            print c.decrypt(ciphertext) + "\n"
        end 
    end
    
=begin
    * a copy of both the plaintext and ciphertext is known,
    * deduce the key
=end
    def known_plaintext(plaintext, ciphertext)
        i = @alphabet.index(plaintext[0])
        j = @alphabet.index(ciphertext[0])
        key = mod(j-i, @alphabet.length)
        return key
    end

=begin
    * access to encryption machine. currently passes a
    * an object instance.
=end
    def chosen_plaintext(cipher)
        letter = cipher.encrypt(@alphabet[0])
        return @alphabet.index(letter)
    end
    
=begin
    * access to decryption machine. currently passes a
    * an object instance.
=end
    def chosen_ciphertext(cipher)
        letter = cipher.decrypt(@alphabet[0])
        index =  @alphabet.index(letter)
        index = mod(index * (-1), @alphabet.length)
        return index
    end

end

