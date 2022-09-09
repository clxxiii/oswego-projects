#include <stdio.h>

int main()
{
    int slices = 20;
    int *p = &slices;

    printf("Slices Address: %p\n", &slices);

    printf("Slices: %d\n", slices);
    printf("Slices (through pointer): %d\n", *p);

    slices = 21;

    printf("Slices: %d\n", slices);
    printf("Slices (through pointer): %d\n", *p);

    *p = 25;

    printf("Slices: %d\n", slices);
    printf("Slices (through pointer): %d\n", *p);

    slices++;
    // If we want to increase using the pointer,
    // We cannot use *p++
    // The addition operator happens first,
    // so this would increase the pointer, then try
    // to dereference.
    // *p++; --> *(p++)
    (*p)++;

    printf("Slices: %d\n", slices);
    printf("Slices (through pointer): %d\n", *p);

    return 0;
}