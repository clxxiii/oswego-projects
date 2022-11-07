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

void hexToBin(char *bin, char *hex)
{
    char *binary = malloc(sizeof(char) * (strlen(hex) * 4));
    *binary = " ";
    while (*hex != '\0' || *hex != '\n')
    {
        switch (*hex)
        {
        case '0':
            strcat("0000", binary);
            break;
        case '1':
            // strcat("0001", binary);
            break;
        case '2':
            strcat("0010", binary);
            break;
        case '3':
            strcat("0011", binary);
            break;
        case '4':
            strcat("0100", binary);
            break;
        case '5':
            strcat("0101", binary);
            break;
        case '6':
            strcat("0110", binary);
            break;

        case '7':
            strcat("0111", binary);
            break;
        case '8':
            strcat("1000", binary);
            break;
        case '9':
            strcat("1001", binary);
            break;
        case 'a':
            strcat("1010", binary);
            break;
        case 'b':
            strcat("1011", binary);
            break;
        case 'c':
            strcat("1100", binary);
            break;
        case 'd':
            strcat("1101", binary);
            break;
        case 'e':
            strcat("1110", binary);
            break;
        case 'f':
            strcat("1111", binary);
            break;
        }
        hex++;
    }
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
        printf("%s", addressHex);
        hexToBin(binary, addressHex);
        printf("%s", binary);
    }

    // Free allocated memory
    free(binary);
    free(addressHex);
}

// GOOD LUCK!
