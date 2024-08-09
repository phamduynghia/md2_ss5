import java.util.Scanner;

public class Categories {
    private static int nextCatalogId = 1; // ID tự tăng
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    // Constructor mặc định
    public Categories() {
        this.catalogId = nextCatalogId++;
    }

    // Constructor với tham số
    public Categories(String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = nextCatalogId++;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    // Getter và Setter
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    // Phương thức nhập dữ liệu
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên danh mục: ");
        this.catalogName = scanner.nextLine();
        while (this.catalogName.length() > 50) {
            System.out.print("Tên danh mục không được vượt quá 50 ký tự. Nhập lại: ");
            this.catalogName = scanner.nextLine();
        }

        System.out.print("Nhập mô tả danh mục: ");
        this.descriptions = scanner.nextLine();

        System.out.print("Nhập trạng thái danh mục (true/false): ");
        this.catalogStatus = scanner.nextBoolean();
        scanner.nextLine(); // Xóa bộ đệm
    }

    // Phương thức hiển thị dữ liệu
    public void displayData() {
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + catalogName);
        System.out.println("Mô tả danh mục: " + descriptions);
        System.out.println("Trạng thái danh mục: " + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
    }
}
