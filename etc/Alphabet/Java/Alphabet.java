import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Throwable;

class Alphabet
{

    private ArrayList<String> alphabet;
    private ArrayList<String> frequency;
    private int size;

    public Alphabet(Alphabet a)
    {
         //TODO
    }

    public Alphabet(String fname) throws Exception
    {
        this.alphabet = new ArrayList<String>();
        this.frequency = new ArrayList<String>();
        File f =  new File(fname);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        String[] lineSplit;

        while((line = br.readLine()) != null)
        {
            lineSplit = line.split(",");
            this.alphabet.add(lineSplit[0]);
            this.frequency.add(lineSplit[1]);
        }

        // XXX: Huh?
        this.size = this.alphabet.size() > this.frequency.size()
                    ? this.frequency.size()
                    : this.frequency.size();
    }

    public int size()
    {
        return this.size;
    }

    public String toString()
    {
        return this.alphabet.toString() + this.frequency.toString();
    }

    public ArrayList<String> get(int i)
    {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(this.alphabet.get(i));
        temp.add(this.frequency.get(i));
        return temp;
    }

    public Integer indexOf(String o)
    {
        return (o==null ? null : this.alphabet.indexOf(o));
    }

    public static void main(String[] args)
    {
        try
        {
            Alphabet a = new Alphabet("../../Frequency/English.csv");
            System.out.println(a);
            System.out.println(a.size());
            System.out.println(a.get(0));
            System.out.println(a.indexOf("a"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
