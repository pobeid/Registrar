public class Course {
    private String name;
    private String code;
    private Instructor instructor;
    private int max_students;
    private int number_students;
    private Student[] students;

    public Course(String name, String code, int max_students) {
        this.name = name;
        this.code = code;
        this.max_students = max_students;
        number_students = 0;
        instructor = null;
        students = new Student[max_students];
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getMax_students() {
        return max_students;
    }

    public Student[] getStudents() {
        return students;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student searchForStudent(Student student) {
        for (int x = 0; x < students.length; x++) {
            // get student at position x then stores it in a variable called one
            Student one = students[x];
            if (one != null && student.getID() == one.getID()) {
                return one;
            }
        }
        return null;
    }

    public boolean addStudentToTheCourse(Student student) {
        Student result = searchForStudent(student);
        if (result != null) {
            System.out.println("Student is already enrolled");
            return false;
        }
        if (number_students >= max_students) {
            System.out.println("The class is full");
            return false;
        }
        // student is new and course is not already at capacity

        // add the new student at the first available null position
        // call the new method and store its result in a new integer variable
        Integer position = searchForNullPosition();
        if (position != null) {
            students [position] = student;
            number_students++;
            return true;
        }

        return false;
    }

    public Integer searchForStudent(int iD) {
        for (int x = 0; x < students.length; x++) {
            // get student at position x then stores it in a variable called one
            Student value = students[x];
            if (iD == value.getID()) {
                return x; //autoboxed primitive x to an Integer
            }
        }
        return null;
    }

    public boolean dropStudentFromTheCourse(Student student) {
        Integer answer = searchForStudent(student.getID());
        if (answer != null ) {
         // set that position to null in the array
         students[answer] = null; //auto unbox object answer to a primitive int
            number_students--;
            return true;
        }
        return false;
    }

    public Integer searchForNullPosition() {
        for (int x = 0; x < students.length; x++) {
            // get student at position x then stores it in a variable called one
            //if the value of position x is null
            if (null == students[x]) {
                return x; //autoboxed primitive x to an Integer
            }
        }
        return null;
    }
}









