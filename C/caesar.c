#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#define ALPHAD "abcdefghijklmnopqrstuvwxyz"

/***************************************
*By Adam Ortiz
*an implementation of caesar/shift cipher
* 
*entirely written in c, later
*  --implement alphabet with AVL's
*  --implement piping files
*	--stdout
*	--stdin
*  --errors
*  --free up memory after
****************************************/

int mod(int i, int j);

int getVal(char a, char *alpha){
	/*gets numerical value of the character from the array*/
	int i = 0;

	//loops through the whole alphabet until letter is found
	for(i=0; i < strlen(alpha);i++){
		if(a == alpha[i]){//when alpha found send back val
			return i;
		}//if
	}//for
	perror("invalid character not in alphabet");
	return -1;
}//getval

int Shift(char *alpha, char *c, int shift){
	/*decodes a line of ciphertexts to a specified */
	int i, k, j;
	for(i = 0; i < strlen(c); i++){//loop through ciphertext
		j = getVal(c[i], alpha);

		if(j == -1){
			perror("incomplete");
			return -1;
		}//error checks

		k = (j + shift);
		printf("%c", alpha[mod(k,26)]);
        }//for
	printf("\n");

}//Shift

void decode(char *alpha, char *c){
	/*decodes the given ciphertext
	which uses shift cipher*/
	int shift;//counts which shift currently at
        int i;

        printf("the ciphertext is %s \n", c);

        for(shift = strlen(alpha); shift > 0; shift--){//loop through all possible shifts
		printf("shift %d == ", -(shift - strlen(alpha)));
		i = Shift(alpha, c, shift);
		if(i == -1){
			printf("\n");
			break;
		}//error
        }//for

}//decode

int mod(int i, int j){
	/*i%j*/
	if(i >= 0){
		return i%j;
	}//if
	return mod(i+j, j);
}//mod

int isIn(char a, char *b){
	int i;
	for(i = 0; i < strlen(b); i++){
		if(b[i] == a){
			return 1;
		}
	}//for
	return 0;
}//isIn

int main(int argc, char** argv){
	//d for decode
	//e for encode
	//ds if shift is known decode to shift
	//a specifies alphabet
	char alpha[]=ALPHAD;

	if(isIn('d', argv[1]) && isIn('e', argv[2])){
		perror("trying to encrypt and decrypt");
		return 0;
	}//error trying to encrypt and decrypt

	if (isIn('a', argv[1])){
		char *alpha=argv[argc-1];
	}//checks alphabet specified


	if(argc == 2 || argc == 1){
		perror("to few arguements");
	}//if only flag or one arguement
	else if(isIn('d', argv[1])){
		/*code for decode*/

		printf("******Decode******\n");

		if (isIn('s', argv[1])&& isIn('a', argv[1])&&(argc==5)){
			Shift(alpha, argv[2], -(atoi(argv[3])));
			return 0;
		}//shift and alphabet specified
		else if(isIn('s', argv[1])&& argc == 4){
			Shift(alpha, argv[2], -(atoi(argv[3])));
			return 0;
		}//shift  specified no alpha
		else if(argc == 4 || argc == 3){
			decode(alpha, argv[2]);
			return 0;
		}//decode normall

		perror("to many or to few arguements");

	}//decode
	else if(isIn('e', argv[1])){
		printf("******Encode******\n");
		if(isIn('a', argv[1]) && argc == 5){
			Shift(alpha, argv[2], atoi(argv[3]));
			return 0;
		}//encode
		else if(argc == 4){
			Shift(alpha, argv[2], atoi(argv[3]));
			return 0;
		}//encode no alpha
		perror("to many or to enough arguements");
	}//else if
	return 0;
}//main
