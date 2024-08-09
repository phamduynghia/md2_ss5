import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Hiển thị danh sách tất cả học sinh");
            System.out.println("2. Thêm mới học sinh");
            System.out.println("3. Sửa thông tin học sinh dựa vào mã học sinh");
            System.out.println("4. Xoá học sinh");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 5);
    }

    private static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sách học sinh trống.");
        } else {
            for (Student student : studentList) {
                student.displayData();
                System.out.println();
            }
        }
    }

    private static void addNewStudent() {
        Student student = new Student();
        student.inputData();
        studentList.add(student);
        System.out.println("Học sinh đã được thêm.");
    }

    private static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã HS của học sinh cần sửa: ");
        String id = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                student.inputData();
                System.out.println("Thông tin học sinh đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy học sinh với mã HS này.");
    }

    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã HS của học sinh cần xóa: ");
        String id = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                studentList.remove(student);
                System.out.println("Học sinh đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy học sinh với mã HS này.");
    }
}
