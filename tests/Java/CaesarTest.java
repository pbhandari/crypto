import static org.junit.Assert.*;
import java.util.ArrayList;
import java.lang.Throwable;
import java.lang.Exception;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CaesarTest
{

    private Alphabet alphabet;
    @Before
    public void setUp()
    {
        try{
            alphabet = new Alphabet("../../../etc/Frequency/English.csv");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    } 
   
    @Test 
    public void testSanity()
    {
        try
        {
            Caesar a = new Caesar(3, alphabet);
            CaesarExploits c = new CaesarExploits(alphabet);
            assertEquals("encrypt sanity check", "khoor", a.encrypt("hello"));
            assertEquals("decrypt sanity check", "hello", a.decrypt("khoor"));
            //c.ciphertextOnly("khoor"); //need to fix this function to return an arraylist
            assertEquals("known plaintext sanity check", 3, c.knownPlaintext("hello", "khoor"));
            assertEquals("chosen plaintext sanity check", 3, c.chosenPlaintext(a));
            assertEquals("chosen ciphertext sanity check", 3,c.chosenCiphertext(a));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
       
     
} 
