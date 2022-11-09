//
// cachelab.c - Cache lab template
//
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>
#include "cachelab.h"
// more libraries if needed for your program

// print result of cache simulation showing hit number, miss number, miss rate, and total running time
void printResult(int *hits, int *misses)
{
    int missRate = ((float)*misses / (float)(*hits + *misses) * 100);
    int runTime = *misses * MISS_PENALTY + *hits * HIT_TIME;
    printf("[result] hits: %d misses: %d miss rate: %d%% total running time: %d cycle\n", *hits, *misses, missRate, runTime);
}

// main function should be coded here
int main(int argc, char **argv)
{
    int hits, misses = 0;
    // Get options from command
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

    int **cache = malloc(sizeof(int *) * pow(2, s));
    printf("sizeof(int*) * 2^s = %f\n", sizeof(int) * pow(2, s));
    printf("Size of cache: %ld\n", sizeof(cache));

    FILE *aFile = fopen(i, "r");
    char *addressHex = malloc(sizeof(char) * 5);
    char *binary = malloc(sizeof(char) * m);
    while (fgets(addressHex, 5, aFile))
    {
        printf("%s", addressHex);
        hexToBin(binary, addressHex, &m);
        int blockNumber = b;
        while (blockNumber > 0)
        {
            int strLen = strlen(binary);
            *(binary + strLen - 1) = '\0';
            blockNumber--;
        }
        printf("Blocked Address: %s\n", binary);
        char *setIndex = binary;
        int bitsToRemove = abs(s - (m - b));
        while (bitsToRemove > 0)
        {
            setIndex++;
            bitsToRemove--;
        }

        char tag[m - s - b];
        strncpy(tag, binary, m - s - b);
        tag[m - s - b] = '\0';
        int setInt = binToInt(setIndex);
        int tagInt = binToInt(tag);
        printf("Set Index: %s (%d); Tag: %s (%d)\n", setIndex, setInt, tag, tagInt);

        int *set = realloc(set, sizeof(int) * pow(2, e));
        set = *(cache + (setInt - 1));
        int cellNum = 0;
        int hit = 0;
        while (cellNum <= e)
        {
            int tagCheck = *(set + e);
            // if (tagCheck == tagInt)
            // {
            //     hit = 1;
            // }
            cellNum++;
        }

        *binary = '\0';
    }
    printResult(&hits, &misses);
    // Free allocated memory
    free(cache);
    free(addressHex);
    free(binary);
}

// GOOD LUCK!
