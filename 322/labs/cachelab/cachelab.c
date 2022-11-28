//
// cachelab.c - Cache lab template
//
//

// more libraries if needed for your program
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>
#include "cachelab.h"

// If you'd like code colors, then uncomment the following:
// #define CONSOLE_RED "\x1b[31m"
// #define CONSOLE_GREEN "\x1b[32m"
// #define CONSOLE_YELLOW "\x1b[33m"
// #define CONSOLE_BLUE "\x1b[34m"
// #define CONSOLE_MAGENTA "\x1b[35m"
// #define CONSOLE_CYAN "\x1b[36m"
// #define CONSOLE_RESET "\x1b[0m"

#define CONSOLE_RED ""
#define CONSOLE_GREEN ""
#define CONSOLE_YELLOW ""
#define CONSOLE_BLUE ""
#define CONSOLE_MAGENTA ""
#define CONSOLE_CYAN ""
#define CONSOLE_RESET ""

// print result of cache simulation showing hit number, miss number, miss rate, and total running time
void printResult(int *hits, int *misses)
{
    float missRate = (float)*misses / (float)(*hits + *misses);
    float averageAccessTime = HIT_TIME + (missRate * MISS_PENALTY);
    int runTime = (*hits + *misses) * averageAccessTime;
    printf("[" CONSOLE_BLUE "result" CONSOLE_RESET "] hits: " CONSOLE_GREEN "%d" CONSOLE_RESET " misses: " CONSOLE_RED "%d" CONSOLE_RESET " miss rate: " CONSOLE_YELLOW "%d%%" CONSOLE_RESET " total running time: " CONSOLE_MAGENTA "%d" CONSOLE_RESET " cycle\n", *hits, *misses, (int)(missRate * 100), runTime);
}

int main(int argc, char **argv)
{
    int *hits = malloc(sizeof(int));
    int *misses = malloc(sizeof(int));
    *hits = 0;
    *misses = 0;
    // Get options from command
    int opt, m, s, e, b, verbose;
    char *i, *r;
    verbose = 0;

    int mCheck, sCheck, eCheck, bCheck, iCheck, rCheck = 0;
    while (-1 != (opt = getopt(argc, argv, "m:s:e:b:i:r:v")))
    {
        switch (opt)
        {
        case 'm':
            m = atoi(optarg);
            mCheck = 1;
            break;
        case 's':
            s = atoi(optarg);
            sCheck = 1;
            break;
        case 'e':
            e = atoi(optarg);
            eCheck = 1;
            break;
        case 'b':
            b = atoi(optarg);
            bCheck = 1;
            break;
        case 'i':
            i = optarg;
            iCheck = 1;
            break;
        case 'r':
            r = optarg;
            rCheck = 1;
            break;
        case 'v':
            verbose = 1;
            break;
        default:
            exit(0);
        }
    }

    if ((mCheck & sCheck & eCheck & bCheck & iCheck & rCheck) != 1)
    {
        printf("Missing Arguments.");
        exit(0);
    }
    if (
        !(strcmp(r, "lru") == 0 ||
          strcmp(r, "fifo") == 0))
    {
        printf("Invalid Algorithm\n");
        if (strcmp(r, "optimal") == 0)
        {
            printf("Optimal Algorithm is not implemented.\n");
        }
        exit(0);
    }

    // Setup Cache and History Linked List
    int **cache = (int **)malloc(sizeof(int *) * pow(2, s));
    struct Node *history = malloc(sizeof(struct Node) * pow(2, s));
    for (int i = 0; i < pow(2, s); i++)
    {
        *(cache + i) = malloc(sizeof(int) * pow(2, e));
        int *set = *(cache + i);
        (*(history + i)).value = -1;

        for (int i = 0; i < pow(2, e); i++)
        {
            // Fill cache with -1
            *(set + i) = -1;
        }
    }
    FILE *aFile = fopen(i, "r");
    char *addressHex = malloc(sizeof(char) * 5);
    char *binary = malloc(sizeof(char) * m);
    char *noNewLineAddress = malloc(sizeof(char) * (m / 4));
    while (fgets(addressHex, 5, aFile))
    {
        strncpy(noNewLineAddress, addressHex, strlen(addressHex) - 1);

        hexToBin(binary, addressHex, &m);

        if (verbose)
        {
            printf("\n┌─ " CONSOLE_YELLOW "%s" CONSOLE_RESET "\n", noNewLineAddress);
            printf("│ Address: %s\n", binary);
        }

        int blockNumber = b;
        while (blockNumber > 0)
        {
            int strLen = strlen(binary);
            *(binary + strLen - 1) = '\0';
            blockNumber--;
        }
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
        struct Node *setHistory = (history + setInt);

        int *set = *(cache + (setInt));
        if (verbose)
        {
            printf("│ Set Index: %s (%d); Tag: %s (%d)\n", setIndex, setInt, tag, tagInt);
            printf("│ Set %d Cache: ", setInt);
            for (int i = 0; i < pow(2, e); i++)
            {
                printf("%d; ", *(set + i));
            }
            printf("\n");
            printf("│ Set History: ");
            printList(setHistory);
            printf("\n");
        }
        int cellNum = 0;
        int hit = 0;
        while (cellNum < pow(2, e))
        {
            int tagCheck = *(set + cellNum);
            if (tagCheck == tagInt)
            {
                hit = 1;
            }
            cellNum++;
        }

        if (!hit)
        {
            if (!verbose)
            {
                printf("%s " CONSOLE_RED "M\n" CONSOLE_RESET, noNewLineAddress);
            }
            else
            {
                printf("│┌ " CONSOLE_RED "Miss!\n" CONSOLE_RESET);
            }
            int replaced = 0;
            for (int cell = 0; cell < pow(2, e) && !replaced; cell++)
            {
                int cellContents = *(set + cell);
                if (cellContents == -1)
                {
                    if (verbose)
                    {
                        printf("││  Found empty space in set %d, writing tag %d\n", setInt, tagInt);
                    }

                    *(set + cell) = tagInt;
                    if (setHistory->value == -1)
                    {
                        setHistory->value = tagInt;
                    }
                    else
                    {
                        struct Node *lastNode = getLast(setHistory);
                        struct Node *nextNode = malloc(sizeof(struct Node));
                        nextNode->value = tagInt;
                        lastNode->pointingTo = nextNode;

                        *(history + setInt) = *setHistory;
                        nextNode = NULL;
                        free(nextNode);
                    }

                    replaced = 1;
                }
            }

            if (!replaced)
            {
                if (strcmp(r, "lru") == 0)
                {
                    int valueToReplace = setHistory->value;
                    if (verbose)
                    {
                        printf("││ Using %s to find a new spot (Replacing %d)\n", r, valueToReplace);
                    }

                    for (int i = 0; i < pow(2, e); i++)
                    {
                        if (*(set + i) == valueToReplace)
                        {
                            *(set + i) = tagInt;
                        }
                    }

                    if (verbose)
                    {
                        printf("││ Current Set Pointer: %p\n", setHistory);
                        printf("││ Head Value: %d\n", setHistory->value);
                        printf("││ Head Pointer: %p ", setHistory->pointingTo);
                        if (setHistory->pointingTo)
                        {
                            printf(" (%d, %p)\n", setHistory->pointingTo->value, setHistory->pointingTo->pointingTo);
                        }
                        else
                        {
                            printf("\n");
                        }
                    }
                    if (!setHistory->pointingTo)
                    {
                        setHistory->value = tagInt;
                    }
                    else
                    {
                        struct Node *lastNode = getLast(setHistory);
                        struct Node *nextNode = malloc(sizeof(struct Node));
                        nextNode->value = tagInt;
                        lastNode->pointingTo = nextNode;

                        *(history + setInt) = *setHistory->pointingTo;
                        if (verbose)
                        {
                            printf("││ Next Set Head Pointer Value: %d\n", setHistory->value);
                        }

                        nextNode = NULL;
                        free(nextNode);
                    }
                }
                if (strcmp(r, "fifo") == 0)
                {
                    int valueToReplace = getLast(setHistory)->value;
                    if (verbose)
                    {
                        printf("││ Using fifo to find a new spot (Replacing %d)\n", valueToReplace);
                    }

                    for (int i = 0; i < pow(2, e); i++)
                    {
                        if (*(set + i) == valueToReplace)
                        {
                            *(set + i) = tagInt;
                        }
                    }

                    if (!setHistory->pointingTo)
                    {
                        setHistory->value = tagInt;
                    }
                    else
                    {
                        struct Node *last = getLast((history + setInt));
                        if (verbose)
                        {
                            printf("││ Last node: (%d, %p)\n", last->value, last->pointingTo);
                        }

                        last->value = tagInt;
                    }
                }
            }
            if (verbose)
            {
                printf("│└\n");
            }
            (*misses)++;
        }

        else
        {
            if (!verbose)
            {
                printf("%s " CONSOLE_GREEN "H\n" CONSOLE_RESET, noNewLineAddress);
            }
            else
            {
                printf("│┌ " CONSOLE_GREEN "Hit!\n" CONSOLE_RESET);
            }
            // Look for hit in history and move it to the end of the list.
            struct Node *valueNode = findValue(setHistory, tagInt);
            struct Node *lastNode = getLast(setHistory);
            if (valueNode == lastNode)
            {
                if (verbose)
                {
                    printf("││ Node (%d) is at the end of the linked list.\n", valueNode->value);
                }
            }
            else
            {
                if (verbose)
                {
                    printf("││ Node (%d, %p) in the list but not at the end. Moving to the end of the list.\n", valueNode->value, valueNode->pointingTo);
                }

                // Find the node that points to our value, or null.
                struct Node *beforeNode = findPointer(setHistory, valueNode);
                if (verbose)
                {
                    printf("││ Node before value: %p\n", beforeNode);
                }
                if (beforeNode == NULL)
                {
                    // If the value node is the head
                    struct Node *valuePlaceHolder = malloc(sizeof(struct Node));
                    valuePlaceHolder->value = valueNode->value;
                    valuePlaceHolder->pointingTo = valueNode->pointingTo;
                    lastNode->pointingTo = valuePlaceHolder;
                    struct Node *newHeadPointer = malloc(sizeof(struct Node)); // = valueNode->pointingTo;
                    newHeadPointer = valuePlaceHolder->pointingTo;
                    valuePlaceHolder->pointingTo = NULL;
                    *(history + setInt) = *newHeadPointer;
                }
            }

            (*hits)++;
        }
        if (verbose)
        {
            printf("└─\n");
        }
        *binary = '\0';
    }
    printResult(hits, misses);
    // Free allocated memory
    free(hits);
    free(misses);
    free(noNewLineAddress);
    free(history);
    free(cache);
    free(addressHex);
    free(binary);
}
