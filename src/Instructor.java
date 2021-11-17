public class Instructor {
    private String name;
    private int iD;
    private String department;

    public Instructor(String name, int iD) {
        this.name = name;
        this.iD = iD;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID(){
        return iD;
    }

    public void setID(int ID) {
        this.iD = ID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

