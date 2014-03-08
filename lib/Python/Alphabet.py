

class Alphabet:
    def __init__(self, filename):

        f = open(filename, 'r')
        self.alphabet = []
        self.frequency = []

        for line in f:
            line_split = line.split(",")
            self.alphabet.append(line_split[0])
            self.frequency.append(float(line_split[1].strip('%\n')))
               
    def __contains__(self, item):
        return item in self.alphabet

    def __str__(self):
        temp =  zip(self.alphabet, self.frequency)
        return str(temp)
    
    def __getitem__(self, a):
        temp = zip(self.alphabet, self.frequency)
        return temp[a]

    def index(self, a): 
        i = self.alphabet.index(a)
        return i


if __name__=="__main__":
    a = Alphabet("../../Frequency/English.csv")
    print a.index("a")
    print a[1]
    print a
    print "a" in a 
