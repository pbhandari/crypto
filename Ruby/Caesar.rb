
def mod(n, m)
    return ((n % m) + m) % m
end

class Caesar

    def initialize(shiftVal, alphaVal=('a'..'z').to_a)
        @shift = shiftVal
        @alphabet = alphaVal
    end
    
    def encrypt(plaintext)
        ciphertext = ""
        for i in 0...plaintext.length
            key = @alphabet.index(plaintext[i]);
            key = mod(key+@shift, @alphabet.length)
            ciphertext = ciphertext + @alphabet[key]
        end
        return ciphertext
    end

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

class Exploits

    def initialize(alphaVal=('a'..'z').to_a)
        @alphabet = alphaVal
    end

    def ciphertext_only(ciphertext)
        for i in 0...@alphabet.length
            c = Caesar.new(i)
            print c.decrypt(ciphertext) + "\n"
        end 
    end
    
    def known_plaintext(plaintext, ciphertext)
        i = @alphabet.index(plaintext[0])
        j = @alphabet.index(ciphertext[0])
        key = mod(j-i, @alphabet.length)
        return key
    end

    def chosen_plaintext(cipher)
        letter = cipher.encrypt(@alphabet[0])
        return @alphabet.index(letter)
    end
    
    def chosen_ciphertext(cipher)
        letter = cipher.decrypt(@alphabet[0])
        index =  @alphabet.index(letter)
        index = mod(index * (-1), @alphabet.length)
        return index
    end

end

c = Caesar.new(3)
print c.encrypt("hello")
print "\n"
print c.decrypt("khoor")
print "\n"
e = Exploits.new()
e.ciphertext_only("khoor")
print e.known_plaintext("hello","khoor")
print "\n"
print e.chosen_plaintext(c)
print "\n"
print e.chosen_ciphertext(c)
print "\n"
