import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrarProgram {
    public static void main(String[] args) {
        // Step 1 Declare variables for data and objects whose services are needed
        Scanner input;
        input = new Scanner(System.in);
        List<Course> courses = new ArrayList<Course>();
        List<Instructor> instructors = new ArrayList<Instructor>();
        List<Student> students = new ArrayList<Student>();


        // Step 2 Begin our interaction loop
        while (true) {
            // THESE STEPS ARE NOT YET ATOMIC

            // Provide the user with their options. What can they do? Show them a menu
            int choice = showMenu(input);

            // Step 7 The choice is valid. Act upon all available choices.
            if (choice == 1) {
                // Step 7a User chose "Create a new course" C of CRUD - CREATE
                Course course = createNewCourse(input);
                // add the new course to the array list
                courses.add(course);
            }else if (choice ==2 ) {
                assignInstructorToCourse(input,courses,instructors);
            } else if (choice == 3) {
                // Step 7b User chose "Show all courses" R of CRUD - READ
                showAllCourses(courses);
            } else if (choice == 4){
                showEnrollment(input,courses);
            } else if (choice == 5) {
                // Step 7c User chose "Register student for a course" - This is a CREATE for a Student, but an UPDATE (U) for a Course
                registerStudentForCourse(input, courses, students);
            } else if (choice == 6) {
                // Step 7d User chose "Drop student from a course" - D for Student (delete Student) or an UPDATE (U) for a Course
                dropStudentFromCourse(input);
            } else if (choice == 7) {
                // Step 7e User chose "Exit"
                System.exit(0);
            }
        }
    }

    public static int showMenu(Scanner input) {
        // Step 3 Ask the user what do you want to do?
        System.out.println("1. Create a Course");
        System.out.println("2. Assign Instructor to Course");
        System.out.println("3. Show all Courses");
        System.out.println("4. Show enrollment");
        System.out.println("5. Register a student for a Course");
        System.out.println("6. Drop a Student from a Course");
        System.out.println("7. Exit");

        // Step 4 Wait for the user to make a choice. Get the choice from the user
        System.out.print("Your choice?: ");
        int choice = input.nextInt();

        // Step 5 Make sure the user made a valid choice

        // Step 6 If the choice is invalid, tell the user to try again
        input.nextLine();
        return choice;
    }

    public static Course createNewCourse(Scanner input) {
        System.out.println("You chose Create a New Course");
        // Step 1 prompt the user for the name of the course
        System.out.println("What's the name of your course?");
        // Step 2 get the name from the input and store in a variable
        String courseName = input.nextLine();
        // Step 3 prompt the user for the course code
        System.out.println("What's the code of your course?");
        // Step 4 get the code from the input and store in a variable
        String courseCode = input.nextLine();
        // Step 5 prompt the user for capacity of the course
        System.out.println("What's the capacity of your course?");
        // Step 6 get the capacity from the input and store in a variable
        int capacity = input.nextInt();
        input.nextLine();
        // Step 7 Create a course using this information
        Course course = new Course(courseName, courseCode, capacity);
        // Step 8 Return the course
        return course;
    }

    public static void showAllCourses(List<Course> courses) {
        System.out.println("You chose Show All Courses");
        for (int i = 0; i < courses.size(); i++) {
            // get the course at position i
            Course course = courses.get(i);
            // declare a variable to hold the instructor assigned to the course
            Instructor instructor = course.getInstructor();
            // print the code, name, and capacity of the course
            // System.out.println(course.getName() + course.getCode() + course.getMax_students());
            System.out.printf("%-10s%7d %-25s%-25s%3d%n", course.getCode(), instructor.getID(), instructor.getName(), course.getName(), course.getMax_students());
        }
    }

    public static void registerStudentForCourse(Scanner input, List<Course> courses, List<Student> students ) {
        System.out.println("You chose register student for a course");
        Student student = getStudent(students,input);
        if (student == null) {
            //1. I'm here to register for a course (User chose Menu Item to register)
            //2. Ask the user for their name
            System.out.println("What's your name?");
            String studentName = input.nextLine();
            //3. Ask the user for their ID
            System.out.println("What's your ID?");
            int studentID = input.nextInt();
            input.nextLine();
            //4. Ask the user to choose the course they want to register for
            System.out.println("Please choose the course you want to register for.");
            // todo: figure out how the user can search from existing courses
            //5. Attempt to register the user in that course
            //5a. Create a new student object using the values provided above
            student = new Student(studentName, studentID);
            // 5b. Store the new student in the array list
            students.add(student);
        }
        //5b. Get the selected course from the array list
        Course course = getCourse(courses, input);
        //5c. Ask the course object to add the student and get the result
       boolean result = course.addStudentToTheCourse(student);
       if (result == true) {
           System.out.println("You are successfully registered!");
       }
    }

    public static Course getCourse(List<Course> courses, Scanner input) {
        System.out.println("Choose from the following courses");
        for (int i = 0; i < courses.size(); i++) {
            // get the course at position i
            Course course = courses.get(i);
            // print the code, name, and capacity of the course
            // System.out.println(course.getName() + course.getCode() + course.getMax_students());
            System.out.printf("%d. %-10s%-25s%3d%n", i+1, course.getCode(), course.getName(), course.getMax_students());
        }
        System.out.println("Please choose a course");
        // get their choice from the scanner and store in an int
        int courseChoice = input.nextInt();
        input.nextLine();
        //subtract 1 from the choice
        courseChoice = courseChoice -1;
        //and use that number to return the course from the list
        return courses.get(courseChoice);
    }

    public static void dropStudentFromCourse(Scanner input) {
        System.out.println("You chose drop a student from a course");
    }

    public static void showEnrollment(Scanner input, List<Course> courses ){
        // Step 1: Ask the user to select a course
        Course course = getCourse(courses, input);
        // Step 2: Get the array of Students from that course
       Student [] students = course.getStudents();
        // Step 3: Display the List of Students
        for (int x =0; x < students.length ; x++) {
            // get the student at position x and store it in a student variable
            Student student = students [x];
            if (student != null ) {
                // print the student's name and ID
                System.out.println(student.getName() + " " + student.getID());
            }
        }
    }

    public static void assignInstructorToCourse(Scanner input, List<Course> courses, List<Instructor> instructors ) {

        // 1. Choose Menu to assign an instructor to a course
        System.out.println("You chose assign instructor to a course");
        // 1a. Display all instructors and ask the user to choose
        Instructor instructor = getInstructor(instructors,input);
        if (instructor == null) {
            // 2. Ask the user for the instructor's name
            System.out.println("What's the instructor's name");
            String instructorName = input.nextLine();
            // 3. Ask the user for the instructor’s ID
            System.out.println("What's the instructor's ID");
            int instructorID = input.nextInt();
            input.nextLine();
            // 5. Create a new instructor object using (name and ID)
            instructor = new Instructor(instructorName, instructorID);
            // 5a. Store the new instructor in the array list
             instructors.add(instructor);
        }
        // 4. Ask the user to choose the course they want to assign the instructor to
        System.out.println("Please choose the course you want to assign the instructor to");

        // 6. Get the selected course from the array List
        Course course = getCourse(courses, input);
        // 7. Ask the instructor object to add the teacher and get the result
        course.setInstructor(instructor);
        System.out.println("Instructor is successfully registered!");
    }

    public static Instructor getInstructor(List<Instructor> instructors, Scanner input) {
        if (instructors.size() == 0) {
            return null;
        }
        System.out.println("Choose from the following instructors");
        for (int i = 0; i < instructors.size(); i++) {
            // get the instructors at position i
            Instructor instructor = instructors.get(i);
            // print the ID, name, and department of the instructor
            // System.out.println(course.getName() + course.getCode() + course.getMax_students());
            System.out.printf("%d. %-10d%-25s%s%n", i+1, instructor.getID(), instructor.getName(), instructor.getDepartment());
        }
        System.out.println("Please choose a instructor or 0 to create a new one");
        // get their choice from the scanner and store in an int
        int instructorChoice = input.nextInt();
        input.nextLine();
        //subtract 1 from the choice
        instructorChoice = instructorChoice -1;
        if (instructorChoice <0) {
            return null;
        }
        //and use that number to return the course from the list
        return instructors.get(instructorChoice);
    }

    public static Student getStudent(List<Student> students, Scanner input) {
        if (students.size() == 0) {
        return null;
        }
        System.out.println("Choose from the following students");
        for (int i = 0; i < students.size(); i++) {
            // get the instructors at position i
            Student student = students.get(i);
            // print the ID, name, and department of the instructor
            // System.out.println(course.getName() + course.getCode() + course.getMax_students());
            System.out.printf("%d. %-10d%-25s%n", i+1, student.getID(), student.getName());
        }
        System.out.println("Please choose a student or 0 to create a new one");
        // get their choice from the scanner and store in an int
        int studentChoice = input.nextInt();
        input.nextLine();
        //subtract 1 from the choice
        studentChoice = studentChoice -1;
        if (studentChoice <0) {
            return null;
        }
        //and use that number to return the course from the list
        return students.get(studentChoice);
    }
}