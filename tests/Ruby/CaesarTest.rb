require '../../lib/Ruby/Caesar.rb'
require 'test/unit'

class CaesarTest < Test::Unit::TestCase

    def test_sanity
        c = Caesar.new(3)
        assert_equal("khoor", c.encrypt("hello"))
        assert_equal("hello", c.decrypt("khoor"))
        e = Exploits.new()
        #e.ciphertext_only("khoor")
        assert_equal(3, e.known_plaintext("hello","khoor"))
        assert_equal(3, e.chosen_plaintext(c))
        assert_equal(3, e.chosen_ciphertext(c))
    end
end
