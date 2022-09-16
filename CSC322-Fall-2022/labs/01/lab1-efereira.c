#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool running = true;

int main()
{
    int ceasarFactor = 5;
    char *loginName = "efereira";
    bool inputError = false;

    while (running)
    {
        printf("%s $ ", loginName);
        char input[100];
        fgets(input, 100, stdin);

        bool shouldExit = strncmp("exit", input, strlen("exit")) == 0;
        bool shouldEncrypt = strncmp("encrypt(", input, strlen("encrypt(")) == 0;
        bool shouldDecrypt = strncmp("decrypt(", input, strlen("decrypt(")) == 0;

        if (shouldExit)
        {
            running = false;
        }
        else if (shouldEncrypt)
        {
            int i = strlen("encrypt(") - 1;
            bool readingInput = true;
            char encryptedString[100];

            while (readingInput)
            {
                i++;
                if (i > 100)
                {
                    inputError = true;
                }
                if (input[i] == ')')
                {
                    readingInput = false;
                    continue;
                }

                char cipherChar = input[i] + ceasarFactor;
                encryptedString[i - strlen("encrypt(")] = cipherChar;
            }

            if (!inputError)
            {
                printf("%s \n", encryptedString);
            }
        }
        else if (shouldDecrypt)
        {
            int i = strlen("decrypt(") - 1;
            bool readingInput = true;
            char decryptedString[100];

            while (readingInput)
            {
                i++;
                if (i > 100)
                {
                    inputError = true;
                }
                if (input[i] == ')')
                {
                    readingInput = false;
                    continue;
                }

                char decipherChar = input[i] - ceasarFactor;
                decryptedString[i - strlen("decrypt(")] = decipherChar;
            }

            if (!inputError)
            {
                printf("%s \n", decryptedString);
            }
        }
        else
        {
            inputError = true;
        }

        if (inputError)
        {
            printf("[Error] Please enter the command correctly\n");
            inputError = false;
        }
    }
    return 0;
}