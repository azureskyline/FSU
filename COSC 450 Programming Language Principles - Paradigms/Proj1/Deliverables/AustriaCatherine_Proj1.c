#import <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[] ){
	//input file
	FILE * infile;		//pass file name as cmd line argument
	infile = fopen(argv[1], "r");
	//output file		
	FILE * outfile;		
	outfile = fopen("COSC450_P1_Output.txt", "w");
    //printf("Program name is:%s\n", argv[0]);	//test
	int size = 500; //demo initial size
///////////////////////////////////////////////////////////////////////////////////
	//exit if no file found
	if (infile == NULL){
        printf("Error Reading File\n");
        exit (0);
    }

	//read characters from file
	int arr[size];
	int arrsize = 0;	//SUPER IMPORTANT SIZE OF ALL ELEMENTS READ IN FILE
    while (fscanf(infile, "%d,", &arr[arrsize])!=0){	//fill arrays
    	arrsize++;
    	if (feof(infile)){
    		break;
    	}
    }
///////////////////////////////////////////////////////////////////////////////////
    int matrixNum = arrsize/5;
    //printf("arrsize: is %d\n", arrsize);		// size of elements
    //printf("arrsize/5: is %d\n", matrixNum);	// column of matrix one| row for matrix two

    //populate matrices
    //matrix one
    int one[5][matrixNum];
    //initialize matix to 0
	for(int i = 0; i < 5; ++i){
		for(int j = 0; j < matrixNum; ++j){
			one[i][j] = 0;
		}
	}
	//populate
    int elementCountOne=0;
    for (int j = 0; j < 5; ++j){
    	for (int k = 0; k < matrixNum; ++k){
    		one[j][k]=arr[elementCountOne];
    		elementCountOne++;
    	}//each col element
    }//each row
    //matrix two
    int two[matrixNum][5];
    //initialize matix to 0
	for(int i = 0; i < matrixNum; ++i){
		for(int j = 0; j < 0; ++j){
			two[i][j] = 0;
		}
	}
	//populate
    int elementCountTwo=0;
    for (int j = 0; j < matrixNum; ++j){
    	for (int k = 0; k < 5; ++k){
    		two[j][k]=arr[elementCountTwo];
    		elementCountTwo++;
    	}//each col element
    }//each row
///////////////////////////////////////////////////////////////////////////////////
    //print matrix one
    printf("%s\n", " ");
    printf("Matrix 1:%s\n", " ");
    fprintf(outfile, "Matrix 1: %s\n", "");	//print to output file
    for (int i = 0; i < 5; ++i){//row = 5
    	for (int j = 0; j < matrixNum; ++j){ //col
    		printf("%d\t", one[i][j]);
    		printf("%s", " ");
    		fprintf(outfile, "%d\t", one[i][j]);	//print to output file
    	}
    	printf("%s\n", " ");
    	fprintf(outfile, "%s\n", "");
    }
    printf("%s\n\n", " ");
    //print matrix two
    printf("Matrix 2:%s\n", " ");
    fprintf(outfile, "Matrix 2: %s\n", "");	//print to output file
    for (int i = 0; i < matrixNum; ++i){ //row
    	for (int j = 0; j < 5; ++j){ //col = 5
    		printf("%d\t", two[i][j]);
    		printf("%s", " ");
    		fprintf(outfile, "%d\t", two[i][j]);	//print to output file
    	}
    	printf("%s\n", " ");
    	fprintf(outfile, "%s\n", "");
    }
    printf("%s\n", " ");
    fprintf(outfile, "%s\n", "");
///////////////////////////////////////////////////////////////////////////////////
    //multiply matrices
    int product[5][5];		//the prodict matrix
    //initialize product matix to 0
	for(int i = 0; i < matrixNum; ++i){
		for(int j = 0; j < matrixNum; ++j){
			product[i][j] = 0;
		}
	}
	//multiplication
    for(int i = 0; i < 5; ++i){
		for(int j = 0; j < 5; ++j){
			for(int k=0; k<matrixNum; ++k){
				product[i][j] += one[i][k] * two[k][j];
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////
    //print product matrix
    printf("Product Matrix: %s\n", " ");
    fprintf(outfile, "Product Matrix: %s\n", "");	//print to output file
    for (int i = 0; i < 5; ++i){
    	for (int j = 0; j < 5; ++j){
    		printf("%d\t\t", product[i][j]);
    		printf("%s", " ");
    		fprintf(outfile, "%d\t", product[i][j]);	//print to output file
    	}
    	printf("%s\n", " ");
    	fprintf(outfile, "%s\n", "");
    }
    printf("%s\n", " ");
    fprintf(outfile, "%s\n", "");
///////////////////////////////////////////////////////////////////////////////////
    //BONUS: sort matrix and print it
    //sort
    int temp, tempr;
    //sort columns
    for(int k=0 ; k < 5 ; ++k){
        for(int j=0; j < 5; j++){
                for(int i=0; i < 4 - j; i++){
                        if (product[i][k]>product[i+1][k]){ // compare adjacent item and swap if needed
                                temp =product[i][k];
                                product[i][k]=product[i+1][k];
                                product[i+1][k]=temp;
                        }
                }
        }
    }
    //sort rows
    for (int i=0;i<5;++i){
		for (int j=0;j<5;++j){
			for (int k=(j+1);k<5;++k){
				if (product[i][j] > product[i][k]){
					tempr = product[i][j];
					product[i][j] = product[i][k];
					product[i][k] = tempr;
				}
			}
		}
	}
    //print sorted product matrix
    printf("Sorted Product Matrix: %s\n", " ");
    fprintf(outfile, "Sorted Product Matrix: %s\n", "");	//print to output file
    for (int i = 0; i < 5; ++i){
    	for (int j = 0; j < 5; ++j){
    		printf("%d\t\t", product[i][j]);
    		printf("%s", " ");
    		fprintf(outfile, "%d\t", product[i][j]);	//print to output file
    	}
    	printf("%s\n", " ");
    	fprintf(outfile, "%s\n", "");
    }
    printf("%s\n", " ");
    fprintf(outfile, "%s\n", "");
/////////////////////////////////////////////////////////////////////////////////// 
    fclose(infile);
    fclose(outfile);

    return 0;
}