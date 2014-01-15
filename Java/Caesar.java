import java.util.ArrayList;

/*******************************
2 * @author Adam Ortiz
3 * implementation of Caeser/shift 
4 * cipher in Java.
5 ********************************/
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
		shift =  shiftVal;
		alphabet = alphabetVal;
	}
	
	/**
	 * creates a new Caeser, in order to encrypt and 
	 * decrypt from. uses standard alphabet
	 * @param the caesar shift*/
	public Caesar(int shiftVal)
	{
		shift = shiftVal;
		
		/*decided to go with an arraylist because
		 *it would allow quick search of elements as opposed to
		 *a simple array where a seperate function would need 
		 *to have been written*/
		
		alphabet = new ArrayList();
		
		alphabet.add(0, 'a');
		alphabet.add(1, 'b');
		alphabet.add(2, 'c');
		alphabet.add(3, 'd');
		alphabet.add(4, 'e');
		alphabet.add(5, 'f');
		alphabet.add(6, 'g');
		alphabet.add(7, 'h');
		alphabet.add(8, 'i');
		alphabet.add(9, 'j');
		alphabet.add(10, 'k');
		alphabet.add(11, 'l');
		alphabet.add(12, 'm');
		alphabet.add(13, 'n');
		alphabet.add(14, 'o');
		alphabet.add(15, 'p');
		alphabet.add(16, 'q');
		alphabet.add(17, 'r');
		alphabet.add(18, 's');
		alphabet.add(19, 't');
		alphabet.add(20, 'u');
		alphabet.add(21, 'v');
		alphabet.add(22, 'w');
		alphabet.add(23, 'x');
		alphabet.add(24, 'y');
		alphabet.add(25, 'z');
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
			int x = alphabet.indexOf(plaintext.charAt(i));
			int encodedIndex = (x + shift)%alphabet.size();
			ciphertext += alphabet.get(encodedIndex);
			
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
			int x = alphabet.indexOf(ciphertext.charAt(i));
			int decodedIndex = (x - shift)%alphabet.size();
			plaintext += alphabet.get(decodedIndex);
			
		}
		
		return plaintext;
	}
	
	public static void main(String[] args)
	{
		Caesar a = new Caesar(3);
		exploits c = new exploits();
		System.out.println(a.encrypt("hello"));
		System.out.println(a.decrypt("khoor"));
	}
	
}