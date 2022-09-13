#include <stdio.h>
#include <string.h>

int main()
{
    char str[100];
    str[0] = 'h';
    str[1] = 'i';

    for (int i = 0; i < strlen(str); i++)
    {
        printf("%c", str[i]);
    }
    printf("\n");

    for (int i = 0; i < strlen(str); i++)
    {
        printf("%d ", (int)str[i]);
    }
    printf("\n");
}