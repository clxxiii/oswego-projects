package assignments.assignment04;

import assignments.assignment04.Student;
import utility.Benchmark;

public class LinearSearch {
	public static Student search(Student[] array, String searchType, String searchKey) {
		Benchmark benchmark = new Benchmark();

		benchmark.resetCounterLS();
		for (int i = 0; i < array.length; i++) {
			benchmark.increaseCounterLS();

			Student student = array[i];

			if (searchType.equalsIgnoreCase("name")) {
				if (searchKey.equalsIgnoreCase(student.getName())) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("id")) {
				if (searchKey.equalsIgnoreCase(student.getId())) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("q1")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[0])) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("q2")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[1])) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("q3")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[2])) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("midterm")) {
				if (searchKey.equalsIgnoreCase("" + student.getMid())) {
					return student;
				}
			} else if (searchType.equalsIgnoreCase("final")) {
				if (searchKey.equalsIgnoreCase("" + student.getFinalExam())) {
					return student;
				}
			}
		}
		return null;
	}
}
