#
# Cache Lab Grader
#
CC = gcc
#CFLAGS = -g -Wall -Werror -std=c99 -m64
PRGM = cachelab
FILE_DIR = .
FILE = $(PRGM).c
ADDR_DIR = addresses
OUTPUT_DIR = out

all: compile run display 

compile:
	$(CC) -o $(FILE_DIR)/$(PRGM) $(FILE_DIR)/$(FILE) -lm
	@echo COMPILED

run: test1 test2 test3 test4 extra1 extra2
	@echo DONE

test1:
	$(FILE_DIR)/$(PRGM) -m 64 -s 4 -e 0 -b 4 -i $(ADDR_DIR)/address01 -r lru > $(OUTPUT_DIR)/$(PRGM)_t1.out

test2:
	$(FILE_DIR)/$(PRGM) -m 64 -s 2 -e 0 -b 2 -i $(ADDR_DIR)/address03 -r lru > $(OUTPUT_DIR)/$(PRGM)_t2.out

test3:
	$(FILE_DIR)/$(PRGM) -m 64 -s 2 -e 1 -b 2 -i $(ADDR_DIR)/address03 -r lru > $(OUTPUT_DIR)/$(PRGM)_t3.out

test4:
	$(FILE_DIR)/$(PRGM) -m 64 -s 2 -e 1 -b 3 -i $(ADDR_DIR)/address02 -r lru > $(OUTPUT_DIR)/$(PRGM)_t4.out

extra1:
	$(FILE_DIR)/$(PRGM) -m 64 -s 2 -e 1 -b 3 -i $(ADDR_DIR)/address02 -r fifo > $(OUTPUT_DIR)/$(PRGM)_te1.out

extra2:
	$(FILE_DIR)/$(PRGM) -m 64 -s 2 -e 1 -b 3 -i $(ADDR_DIR)/address02 -r optimal > $(OUTPUT_DIR)/$(PRGM)_te2.out

display:
	cat $(OUTPUT_DIR)/$(PRGM)_t*.out | grep [result]

grade:
	cat $(OUTPUT_DIR)/$(PRGM)_t*.out | grep [result] > $(OUTPUT_DIR)/$(PRGM)_key.out
	diff $(OUTPUT_DIR)/$(PRGM)_key.out $(ADDR_DIR)/answer_key.out || exit 0
	@echo DONE
#
#
# Clean the src dirctory
#
#clean:
#	rm -rf *.o
#	rm -f *.tar
#	rm -f csim
#	rm -f .csim_results .marker
