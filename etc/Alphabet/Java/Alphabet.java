import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Throwable;

class Alphabet
{

    private ArrayList alphabet;
    private ArrayList frequency;
    private int size;

    public Alphabet(Alphabet a)
    {
         //TODO  
    }

    public Alphabet(String fname) throws Exception
    {
        this.alphabet = new ArrayList();
        this.frequency = new ArrayList();
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
        
        this.size = this.alphabet.size() > this.frequency.size() ? this.frequency.size() : this.frequency.size();
    }

    public int size()
    {
        return this.size;
    }

    public String toString()
    {
        return this.alphabet.toString() + this.frequency.toString();
    }

    public ArrayList get(int i)
    {
        ArrayList temp = new ArrayList();
        temp.add(this.alphabet.get(i));
        temp.add(this.frequency.get(i));
        return temp;
    }

    public int indexOf(String o)
    {
        return (o==null ? null : this.alphabet.indexOf(o));
    }

    public static void main(String[] args)
    {
        try{
            Alphabet a = new Alphabet("../../Frequency/English.csv");
            System.out.println(a);
            System.out.println(a.size());
            System.out.println(a.get(0));
            System.out.println(a.indexOf("a"));
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
