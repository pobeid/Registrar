public class Student {
    private String name;
    private int iD;
    private int credits;
    private int points;

   public Student(String name, int iD) {
        this.name = name;
        this.iD = iD;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return iD;
    }

    public double getGPA() {
        return (double)points/credits;
    }
}
