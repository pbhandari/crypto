var assert = require("assert");
var Caesar = require("../../lib/JavaScript/Caesar.js");

var c = new Caesar.Caesar(3);
var e = new Caesar.Exploit();

//test initialization encryption and decryption
//test initialization encryption and decryption
assert.equal(c.encrypt("hello"), "khoor", "sanity check encrypt");
assert.equal(c.decrypt("khoor"), "hello", "sanity check decrypt");

//test ciphertext only
//var possible = ciphertext_only(hey);
//console.log(possible);

//test known  plaintext
assert.equal(e.known_plaintext("hello", "khoor"), 3, "senity check known plaintext");

//test chosen ciphertext and chosen plaintext
assert.equal(e.chosen_plaintext(c), 3, "sanity check chosen plaintext"); 
assert.equal(e.chosen_ciphertext(c), 3, "sanity check chosen ciphertext");
