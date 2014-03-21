import java.util.ArrayList;

/*******************************
 * @author Adam Ortiz
 * implementation of Caeser/shift 
 * cipher in Java.
 ********************************/
public class Caesar
{	
	private int shift;
	private Alphabet alphabet;
	
	/**
	 * creates a new Caeser, in order to encrypt and 
	 * decrypt from 
	 * @param the caesar shift
	 * @param the alphabet to be used*/
	public Caesar(int shiftVal, Alphabet alphabetVal) throws Exception
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
	public Caesar(int shiftVal) throws Exception
	{
		this.shift = shiftVal;
		
		/*decided to go with an arraylist because
		 *it would allow quick search of elements as opposed to
		 *a simple array where a seperate function would need 
		 *to have been written*/
        this.alphabet = new Alphabet("../../etc/Frequency/English.csv");
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
			int x = this.alphabet.indexOf(String.valueOf(plaintext.charAt(i)));
			int encodedIndex = mod((x + this.shift), this.alphabet.size());
			ciphertext += (this.alphabet.get(encodedIndex)).get(0);
			
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
			int x = this.alphabet.indexOf(String.valueOf(ciphertext.charAt(i)));
			int decodedIndex = mod((x - this.shift), this.alphabet.size());
			plaintext += (this.alphabet.get(decodedIndex)).get(0);
			
		}
		
		return plaintext;
	}
	
	
}

