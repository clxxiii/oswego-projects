#include <stdio.h>

int main()
{
    char negationChars[] = {
        0x41,
        0x00,
    };

    char unionChars[] = {
        0x1F,
        0x99,
    };

    printf("Bitwise operators: \n");
    for (int i = 0; i < 2; i++)
    {
        char currentChar = negationChars[i];

        printf("%d: ", currentChar);
        printf("~%d = %d \n", currentChar, ~currentChar);
    }
}