#include <stdio.h>
#include <string.h>

int main()
{
    char str[] = "Hello C";

    char cipher[10];
    for (int i = 0; i < strlen(str); i++)
    {
        cipher[i] = str[i];
    }

    printf("String starting at char 0: %s\n", cipher);

    char cipher2[10];
    for (int i = 1; i < strlen(str); i++)
    {
        cipher2[i - 1] = str[i];
    }

    printf("String starting at char 1: %s\n", cipher2);
}