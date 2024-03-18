#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int int_to_int(int k)
{
    if (k == 0)
        return 0;
    if (k == 1)
        return 1; /* optional */
    return (k % 2) + 10 * int_to_int(k / 2);
}

int main()
{
    size_t buf = 100;
    char *arr = malloc(buf);
    fgets(arr, buf, stdin);

    size_t pos = 0;

    while (1)
    {
        char c = *(arr + pos);
        printf("%c --> %d: %d\n", c, c, int_to_int(c));
        if (c == '\n' || c == '\0')
        {
            return 0;
        }
        pos++;
    }
}