#include <stdio.h>

typedef struct
{
    int a[2];
    double d;
} struct_t;

double fun(int i)
{
    volatile struct_t s;
    s.d = 3.14;
    s.a[i] = 1073741824; // Possibly out of bounds
    return s.d;
}

int main()
{
    printf("f(0) = %f\n", fun(0));
    printf("f(1) = %f\n", fun(1));
    printf("f(2) = %f\n", fun(2));
    printf("f(3) = %f\n", fun(3));
    printf("f(4) = %f\n", fun(4));
    printf("f(5) = %f\n", fun(5));
    printf("f(6) = %f\n", fun(6));
}