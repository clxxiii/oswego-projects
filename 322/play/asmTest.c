#include <stdio.h>

int main() {
	int test[5] = {10, 20, 30, 40, 50};

	for (int i = 0; i < 5; i++) {
		test[i]++;
	}

	printf("%d\n", test[0]);
}
