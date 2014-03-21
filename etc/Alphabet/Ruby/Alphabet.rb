
class Alphabet
    def initialize(fname="../../Frequency/English.csv")
        f = File.open(fname, "r")
        @alphabet = []
        @frequency = []
        while line = f.gets
            lineSplit = line.split(",")
            @alphabet.push(lineSplit[0])
            @frequency.push(lineSplit[1])
        end
        @length = @alphabet.length
    end

    def index(chr)
        return @alphabet.index(chr)
    end

    def length()
        return @length
    end

    def at(i)
        return [@alphabet[i], @frequency[i]]
    end
end
