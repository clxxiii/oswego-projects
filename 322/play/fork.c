#include <stdio.h>
#include <stdlib.h>

int main() {
  pid_t child_process = fork();

  if (child_process != 0) {
    printf("Hi! (from fork)\n");
  }
  else {
    printf("Hi! (from parent)\n");
  }
  return 0;
}
