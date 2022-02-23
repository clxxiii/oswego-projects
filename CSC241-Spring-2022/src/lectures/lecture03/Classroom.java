package lectures.lecture03;

abstract class Classroom {
    String roomNum;
    int capacity;
    boolean [] classTime;           // Boolean is class, boolean is primitive

    Classroom (String roomNum, int capacity) {
        this.roomNum = roomNum;
        this.capacity = capacity;
    }

    abstract void initializeClassTime(int classHours);      // abstract method
}

class LectureClassRoom extends Classroom {
    LectureClassRoom (String roomNum, int capacity) {
        super(roomNum, capacity);
    }

    void initializeClassTime(int classHours) {              // subclass MUST implement the abstract method inherited from abstract class
        classTime = new boolean[classHours];
        // maybe initialize all elements in the array
    }

    void setClassTime(int classHour) {
        classTime[classHour-1] = true;
    }
}
