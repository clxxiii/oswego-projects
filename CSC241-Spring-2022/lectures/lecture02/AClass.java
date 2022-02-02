package lectures.lecture02;

public class AClass {

    private String[] names;
    private String crn;
    private String time;
    private int numOfStudents;

    public AClass(String[] names, String crn, String time) {
        this.names = names;
        this.crn = crn;
        this.time = time;
        numOfStudents = names.length;
    }

    public AClass(String crn, String time) {
        this.crn = crn;
        this.time = time;
        numOfStudents = 0;
    }

    public void addStudent(String name) {
        names[numOfStudents++] = name;
    }

    public String getTime() {
        return time;
    }

    public String getCrn() {
        return crn;
    }
}
