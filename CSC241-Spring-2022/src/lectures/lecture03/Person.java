package lectures.lecture03;

// parent class
class Person {
    String name;
    private int age;

    Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    void getOld() {
        age++;
    }

    void identify(){
        System.out.println("Name " + name + "\tage " + age);
    }
}

// child class
class Student extends Person {
    private String major;
    private String id;

    Student (String name, int age, String id) {
        super(name, age);
        this.id = id;
    }

    String getMajor() {
        return major;
    }

    void setMajor(String major) {
        this.major = major;
    }

    String getID() {
        return id;
    }

    void setID(String id) {
        this.id = id;
    }

    void identify() {
        System.out.println("Student " + name + "\tid " + id);
    }
}

class Instructor extends Person {
    String title;

    Instructor(String name, int age, String title) {
        super(name, age);
        this.title = title;
    }

    void setTitle(String newTitle) {
        title = newTitle;
    }

    void identify() {
        System.out.println("Instructor " + name + "\ttitle " + title);
    }
}
