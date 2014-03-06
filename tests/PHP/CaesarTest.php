<?php
include '../../lib/PHP/Caesar.php';

class CaesarTest extends PHPUnit_Framework_TestCase
{
    // ...

    public function testSanityCheck()
    {
        $c = new Caesar(3);
        $this->assertEquals("khoor", $c->encrypt("hello"));
        $this->assertEquals("hello", $c->decrypt("khoor"));
        $e = new Exploits();
         //$e->ciphertext_only("khoor");
        $this->assertEquals(3, $e->known_plaintext("hello", "khoor"));
        $this->assertEquals(3, $e->chosen_plaintext($c));
        $this->assertEquals(3, $e->chosen_ciphertext($c));

    }
}
?>
