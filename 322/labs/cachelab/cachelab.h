//
//  cachelab.h - header file for cachelab
//  : defining global variabls and functions
//

#ifndef cachelab_h
#define cachelab_h

#define HIT_TIME 1       // hit time fixed for calculating running time
#define MISS_PENALTY 100 // miss penalty fixed for calculating running time

//
// printResult
// : providing a standard way for your cache simulator to display its following result
//  - hits: number of hits
//  - misses: number of misses
//  - missRate: miss rate in percentage (=hits/(hits+misses))
//  - rumTime: total running time in cycle
//


void hexToBin(char *bin, char *hex, int *m)
{
    while (*hex != '\n')
    {

        switch (*hex)
        {
        case '0':
            strncat(bin, "0000", 5);
            break;
        case '1':
            strncat(bin, "0001", 5);
            break;
        case '2':
            strncat(bin, "0010", 5);
            break;
        case '3':
            strncat(bin, "0011", 5);
            break;
        case '4':
            strncat(bin, "0100", 5);
            break;
        case '5':
            strncat(bin, "0101", 5);
            break;
        case '6':
            strncat(bin, "0110", 5);
            break;
        case '7':
            strncat(bin, "0111", 5);
            break;
        case '8':
            strncat(bin, "1000", 5);
            break;
        case '9':
            strncat(bin, "1001", 5);
            break;
        case 'A':
            strncat(bin, "1010", 5);
            break;
        case 'B':
            strncat(bin, "1011", 5);
            break;
        case 'C':
            strncat(bin, "1100", 5);
            break;
        case 'D':
            strncat(bin, "1101", 5);
            break;
        case 'E':
            strncat(bin, "1110", 5);
            break;
        case 'F':
            strncat(bin, "1111", 5);
            break;
        }
        hex++;
    }

    if (strlen(bin) != *m)
    {

        if (strlen(bin) < *m)
        {
            char *temp = malloc(sizeof(char) * *m);
            *temp = '\0';
            int stringLength = *m;
            int currentLength = strlen(bin);
            int zeroesToAppend = stringLength - currentLength;
            for (int i = 0; i < zeroesToAppend; i++)
            {
                *(temp + i) = '0';
            }
            *(temp + zeroesToAppend) = '\0';

            strncat(temp, bin, 100);
            strncpy(bin, temp, strlen(temp));
            free(temp);
        }
    }
}

int binToInt(char *bin)
{
    int result = 0;
    for (int i = strlen(bin) - 1; i >= 0; i--)
    {
        if (*(bin + i) == '1')
        {
            result += pow(2, (strlen(bin) - 1) - i);
        }
    }
    return result;
}

#endif /* cachelab_h */
