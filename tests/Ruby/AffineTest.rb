require '../../lib/Ruby/Affine.rb'
require 'test/unit'

class AffineTest < Test::Unit::TestCase

    def test_sanity
        c = Affine.new(3, 4)
        assert_equal("zqllu", c.encrypt("hello"))
        assert_equal("hello", c.decrypt("zqllu"))
    end
end
