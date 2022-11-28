#include <stdio.h>
#include <stdlib.h>


int main() {
	int s = 4;
	int e = 5;

	int** matrix = malloc(sizeof(int*) * s);
	for (int i = 0; i < s; i++) {
		int* line = malloc(sizeof(int) * e);
		*(matrix + i) = line;
		for (int j = 0; j < e; j++) {
			*(line + j) = i + j;
		}
	}

	// Print the matrix:
	for (int i = 0; i < s; i++) {
		int* row = matrix[i];
		for (int j = 0; j < e; j++) {
			int cell = *(row + j);
			printf("%i ", cell);
		}
		printf("\n");
	}
}



