import java.io.*;
import java.util.*;
public class StudentManagementSystem {
    private static final String FILE_NAME = "students.ser";
    private static Map<Integer, Student> studentMap = new HashMap<>();
    public static void main(String[] args) {
        loadFromFile();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> updateStudent(sc);
                case 3 -> deleteStudent(sc);
                case 4 -> viewAll();
                case 5 -> searchStudent(sc);
                case 6 -> saveToFile();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
    private static void addStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        Student student = new Student(roll, name, course, marks);
        studentMap.put(roll, student);
        System.out.println("Student added successfully.");
    }
    private static void updateStudent(Scanner sc) {
        System.out.print("Enter Roll No of Student to Update: ");
        int roll = sc.nextInt();
        sc.nextLine();
        Student s = studentMap.get(roll);
        if (s != null) {
            System.out.print("Enter New Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Course: ");
            String course = sc.nextLine();
            System.out.print("Enter New Marks: ");
            double marks = sc.nextDouble();
            s.setName(name);
            s.setCourse(course);
            s.setMarks(marks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }
    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Roll No to Delete: ");
        int roll = sc.nextInt();
        if (studentMap.containsKey(roll)) {
            studentMap.remove(roll);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }
    private static void viewAll() {
        if (studentMap.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            for (Student s : studentMap.values()) {
                System.out.println("------------");
                s.display();
            }
        }
    }
    private static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll No to Search: ");
        int roll = sc.nextInt();
        Student s = studentMap.get(roll);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found!");
        }
    }
    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(studentMap);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                studentMap = (Map<Integer, Student>) ois.readObject();
                System.out.println("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }
}