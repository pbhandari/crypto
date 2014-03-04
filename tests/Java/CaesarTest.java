import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CaesarTest
{
    @Before
    public void setUp()
    {
        ArrayList alphabet = new ArrayList();

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
   
    @Test 
    public void testSanity()
    {
        Caesar a = new Caesar(3);
        CaesarExploits c = new CaesarExploits();
        assertEquals("encrypt sanity check", a.encrypt("hello"), "khoor");
        assertEquals("decrypt sanity check", a.decrypt("khoor"), "hello");
        //c.ciphertextOnly("khoor"); //need to fix this function to return an arraylist
        assertEquals("known plaintext sanity check", 3, c.knownPlaintext("hello", "khoor"));
        assertEquals("chosen plaintext sanity check", 3, c.chosenPlaintext(a));
        assertEquals("chosen ciphertext sanity check", 3,c.chosenCiphertext(a));
    }
       
     
} 
