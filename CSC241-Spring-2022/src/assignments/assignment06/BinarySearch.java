package assignments.assignment06;

/*
    CSC 241 Spring 2022
    Assignment 5
    Name: Eli Fereira
    ID: 806061464
 */

import utility.Benchmark;

public class BinarySearch {

	public Student search(Student[] students, String searchType, String searchKey) {
		Benchmark benchmark = new Benchmark();
		Student[] sortedStudents = sort(students, searchType);

		int start = 0;
		int end = sortedStudents.length;
		while (start <= end) {
			benchmark.increaseCounterBS();
			int mid = start + ((end - start) / 2);
			System.out.println("start: " + start + ", middle: " + mid + "; end: " + end);

			if (searchType.equalsIgnoreCase("name")) {
				System.out.println(sortedStudents[mid].name.compareTo(searchKey));
				if (sortedStudents[mid].name.equals(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].name.compareTo(searchKey) < 0) {
					start = mid + 1;
				}

				if (sortedStudents[mid].name.compareTo(searchKey) > 0) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("id")) {
				if (Integer.valueOf(sortedStudents[mid].getId()) == Integer.valueOf(searchKey)) {
					return sortedStudents[mid];
				}

				if (Integer.valueOf(sortedStudents[mid].getId()) < Integer.valueOf(searchKey)) {
					start = mid + 1;
				}

				if (Integer.valueOf(sortedStudents[mid].getId()) > Integer.valueOf(searchKey)) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("q1")) {
				if (sortedStudents[mid].getQuiz()[0] == Integer.valueOf(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].getQuiz()[0] < Integer.valueOf(searchKey)) {
					start = mid + 1;
				}

				if (sortedStudents[mid].getQuiz()[0] > Integer.valueOf(searchKey)) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("q2")) {
				if (sortedStudents[mid].name.equals(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == 1) {
					start = mid + 1;
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == -1) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("q3")) {
				if (sortedStudents[mid].name.equals(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == 1) {
					start = mid + 1;
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == -1) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("midterm")) {
				if (sortedStudents[mid].name.equals(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == 1) {
					start = mid + 1;
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == -1) {
					end = mid - 1;
				}
			} else if (searchType.equalsIgnoreCase("final")) {
				if (sortedStudents[mid].name.equals(searchKey)) {
					return sortedStudents[mid];
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == 1) {
					start = mid + 1;
				}

				if (sortedStudents[mid].name.compareTo(searchKey) == -1) {
					end = mid - 1;
				}
			}

		}

		return null;
	}

	/**
	 * Simple selection sort because binary searches need sorted arrays.
	 */
	private static Student[] sort(Student[] array, String searchType) {

		int arrayLength = array.length;
		for (int i = 0; i < arrayLength - i; i++) {

			int lowerBound = i;
			for (int v = i + 1; v < arrayLength; v++) {

				if (searchType.equalsIgnoreCase("name")) {
					if (array[v].name.compareTo(array[lowerBound].name) == 1) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("id")) {
					if (Integer.valueOf(array[v].getId()) < Integer.valueOf(array[lowerBound].getId())) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("q1")) {
					if (array[v].getQuiz()[0] <= array[lowerBound].getQuiz()[0]) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("q2")) {
					if (array[v].getQuiz()[1] <= array[lowerBound].getQuiz()[1]) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("q3")) {
					if (array[v].getQuiz()[2] <= array[lowerBound].getQuiz()[2]) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("midterm")) {
					if (array[v].getMid() <= array[lowerBound].getMid()) {
						lowerBound = v;
					}
				} else if (searchType.equalsIgnoreCase("final")) {
					if (array[v].getFinalExam() <= array[lowerBound].getFinalExam()) {
						lowerBound = v;
					}
				}
			}

			// Swap first element with "smallest" element
			Student temp = array[lowerBound];
			array[lowerBound] = array[i];
			array[i] = temp;
		}
		return array;
	}
}