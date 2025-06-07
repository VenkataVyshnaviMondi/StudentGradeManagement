import java.io.Serializable;
public class Student implements Serializable {
    private int rollNo;
    private String name;
    private String course;
    private double marks;
    public Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks);
    }
}