cmake_minimum_required(VERSION 3.23)
project(cachelab C)

set(CMAKE_C_STANDARD 99)

add_executable(cachelab cachelab.c cachelab.h)
target_link_libraries(cachelab m)