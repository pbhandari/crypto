import java.util.ArrayList;

/*******************************
 * @author Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in Java.
 ********************************/
public class Caesar
{	
	private int shift;
	private ArrayList alphabet;
	
	/**
	 * creates a new Caeser, in order to encrypt and 
	 * decrypt from 
	 * @param the caesar shift
	 * @param the alphabet to be used*/
	public Caesar(int shiftVal, ArrayList alphabetVal)
	{
		this.shift =  shiftVal;
		this.alphabet = alphabetVal;
	}

    public int mod(int n, int m) {
        return ((n % m) + m) % m;
    }
	
	/**
	 * creates a new Caeser, in order to encrypt and 
	 * decrypt from. uses standard alphabet
	 * @param the caesar shift*/
	public Caesar(int shiftVal)
	{
		this.shift = shiftVal;
		
		/*decided to go with an arraylist because
		 *it would allow quick search of elements as opposed to
		 *a simple array where a seperate function would need 
		 *to have been written*/
		
		this.alphabet = new ArrayList();
		
		this.alphabet.add(0, 'a');
		this.alphabet.add(1, 'b');
		this.alphabet.add(2, 'c');
		this.alphabet.add(3, 'd');
		this.alphabet.add(4, 'e');
		this.alphabet.add(5, 'f');
		this.alphabet.add(6, 'g');
		this.alphabet.add(7, 'h');
		this.alphabet.add(8, 'i');
		this.alphabet.add(9, 'j');
		this.alphabet.add(10, 'k');
		this.alphabet.add(11, 'l');
		this.alphabet.add(12, 'm');
		this.alphabet.add(13, 'n');
		this.alphabet.add(14, 'o');
		this.alphabet.add(15, 'p');
		this.alphabet.add(16, 'q');
		this.alphabet.add(17, 'r');
		this.alphabet.add(18, 's');
		this.alphabet.add(19, 't');
		this.alphabet.add(20, 'u');
		this.alphabet.add(21, 'v');
		this.alphabet.add(22, 'w');
		this.alphabet.add(23, 'x');
		this.alphabet.add(24, 'y');
		this.alphabet.add(25, 'z');
	}
	
	/**
	 * Used to encrypt the given string plaintext. 
	 * @param plaintext 
	 * @return ciphertext*/
	public String encrypt(String plaintext)
	{
		plaintext = plaintext.toLowerCase();
		String ciphertext = "";
		
		for(int i=0; i < plaintext.length(); i++)
		{
			int x = this.alphabet.indexOf(plaintext.charAt(i));
			int encodedIndex = mod((x + this.shift), this.alphabet.size());
			ciphertext += this.alphabet.get(encodedIndex);
			
		}
		
		return ciphertext;
	}
	
	/**
	 * Used to decrypt the given string ciphertext. 
	 * @param ciphertext 
	 * @return plaintext*/
	public String decrypt(String ciphertext)
	{
		ciphertext = ciphertext.toLowerCase();
		String plaintext = "";
		
		for(int i=0; i < ciphertext.length(); i++)
		{
			int x = this.alphabet.indexOf(ciphertext.charAt(i));
			int decodedIndex = mod((x - this.shift), this.alphabet.size());
			plaintext += this.alphabet.get(decodedIndex);
			
		}
		
		return plaintext;
	}
	
	public static void main(String[] args)
	{
		Caesar a = new Caesar(3);
		Exploits c = new Exploits();
		System.out.println(a.encrypt("hello"));
		System.out.println(a.decrypt("khoor"));
        c.ciphertextOnly("khoor");
        System.out.println(c.knownPlaintext("hello", "khoor"));
        System.out.println(c.chosenPlaintext(a));
        System.out.println(c.chosenCiphertext(a));
        
	}
	
}

/*
 * class which holds the known exploits for caesar cipher
*/
class Exploits
{
    public ArrayList alphabet;

    public Exploits()
    {
		this.alphabet = new ArrayList();
		
		this.alphabet.add(0, 'a');
		this.alphabet.add(1, 'b');
		this.alphabet.add(2, 'c');
		this.alphabet.add(3, 'd');
		this.alphabet.add(4, 'e');
		this.alphabet.add(5, 'f');
		this.alphabet.add(6, 'g');
		this.alphabet.add(7, 'h');
		this.alphabet.add(8, 'i');
		this.alphabet.add(9, 'j');
		this.alphabet.add(10, 'k');
		this.alphabet.add(11, 'l');
		this.alphabet.add(12, 'm');
		this.alphabet.add(13, 'n');
		this.alphabet.add(14, 'o');
		this.alphabet.add(15, 'p');
		this.alphabet.add(16, 'q');
		this.alphabet.add(17, 'r');
		this.alphabet.add(18, 's');
		this.alphabet.add(19, 't');
		this.alphabet.add(20, 'u');
		this.alphabet.add(21, 'v');
		this.alphabet.add(22, 'w');
		this.alphabet.add(23, 'x');
		this.alphabet.add(24, 'y');
		this.alphabet.add(25, 'z');
    }

    public Exploits(ArrayList alphabetVal)
    {
        this.alphabet = alphabetVal;
    }

    public int mod(int n, int m) {
        return ((n % m) + m) % m;
    }
    
    /* Only a copy of the cipher text is known, the best
     * course of action in this case is to try all 
     * possibilities*/
    public void ciphertextOnly(String ciphertext)
    {

        Caesar c; 
        for(int i=0; i < alphabet.size(); i++)
        {
            c = new Caesar(i, this.alphabet);
            System.out.println(c.decrypt(ciphertext));
        }
    }
    
    /* a copy of both the plaintext and ciphertext is known,
     * deduce the key
     */
    public int knownPlaintext(String plaintext, String ciphertext)
    {
        return alphabet.indexOf(ciphertext.charAt(0)) - alphabet.indexOf(plaintext.charAt(0)); 
    }

    /* access to encryption machine. currently passes a
     * an object instance.
     */
    public int chosenPlaintext(Caesar cipher)
    {
        char ciphertext = cipher.encrypt(String.valueOf(this.alphabet.get(0))).charAt(0);
        return alphabet.indexOf(ciphertext); 
    }

    /* access to decryption machine. currently passes a
     * an object instance.
     */
    public int chosenCiphertext(Caesar cipher)
    {
        char plaintext = cipher.decrypt(String.valueOf(this.alphabet.get(0))).charAt(0);
        int key =  alphabet.indexOf(plaintext); 
        key = mod(key * (-1), this.alphabet.size());
        return key;
    }
}

