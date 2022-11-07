//
// cachelab.c - Cache lab template
//
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "cachelab.h"
// more libraries if needed for your program

// print result of cache simulation showing hit number, miss number, miss rate, and total running time
void printResult(int hits, int misses, int missRate, int runTime)
{
    printf("[result] hits: %d misses: %d miss rate: %d%% total running time: %d cycle\n", hits, misses, missRate, runTime);
}

// main function should be coded here
int main(int argc, char **argv)
{
    // Get options from command line
    int opt, m, s, e, b;
    char *i, *r;

    while (-1 != (opt = getopt(argc, argv, "m:s:e:b:i:r:")))
    {
        switch (opt)
        {
        case 'm':
            m = atoi(optarg);
            break;
        case 's':
            s = atoi(optarg);
            break;
        case 'e':
            e = atoi(optarg);
            break;
        case 'b':
            b = atoi(optarg);
            break;
        case 'i':
            i = optarg;
            break;
        case 'r':
            r = optarg;
            break;
        default:
            printf("wrong argument dummy\n");
            break;
        }
    }
    printf("%d, %d, %d, %d, %s, %s\n", m, s, e, b, i, r);

    FILE *aFile = fopen(i, "r");
    char *addressHex = malloc(sizeof(char) * 5);
    char *binary = malloc(sizeof(char) * m);
    while (fgets(addressHex, 5, aFile))
    {
        printf("Hex to convert: %s", addressHex);
        hexToBin(binary, addressHex, m);
        printf("Binary: %s\n", binary);
        binary[0] = '\0';
    }
    free(binary);

    // Free allocated memory
    free(addressHex);
}

// GOOD LUCK!
