import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus; // 0: Đang bán, 1: Hết hàng, 2: Không bán

    // Constructor mặc định
    public Product() {}

    // Constructor với tham số
    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    // Getter và Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    // Phương thức nhập dữ liệu
    public void inputData(Scanner scanner, Categories[] arrCategories) {
        System.out.print("Nhập mã sản phẩm (4 ký tự, bắt đầu bằng C/S/A): ");
        this.productId = scanner.nextLine();
        while (this.productId.length() != 4 || !this.productId.matches("[CS|SA][0-9]{3}")) {
            System.out.print("Mã sản phẩm không hợp lệ. Nhập lại: ");
            this.productId = scanner.nextLine();
        }

        System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
        this.productName = scanner.nextLine();
        while (this.productName.length() < 10 || this.productName.length() > 50) {
            System.out.print("Tên sản phẩm phải có từ 10 đến 50 ký tự. Nhập lại: ");
            this.productName = scanner.nextLine();
        }

        System.out.print("Nhập giá sản phẩm (lớn hơn 0): ");
        this.price = scanner.nextFloat();
        while (this.price <= 0) {
            System.out.print("Giá sản phẩm phải lớn hơn 0. Nhập lại: ");
            this.price = scanner.nextFloat();
        }
        scanner.nextLine(); // Xóa bộ đệm

        System.out.print("Nhập mô tả sản phẩm: ");
        this.description = scanner.nextLine();

        System.out.print("Nhập ngày nhập sản phẩm (dd/MM/yyyy): ");
        String dateStr = scanner.nextLine();
        try {
            this.created = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (Exception e) {
            System.out.println("Ngày không hợp lệ. Sử dụng ngày hiện tại.");
            this.created = new Date();
        }

        System.out.println("Chọn mã danh mục từ các danh mục sau:");
        for (Categories category : arrCategories) {
            if (category != null) {
                System.out.println("Mã danh mục: " + category.getCatalogId() + ", Tên danh mục: " + category.getCatalogName());
            }
        }
        System.out.print("Nhập mã danh mục: ");
        this.catalogId = scanner.nextInt();

        System.out.print("Nhập trạng thái sản phẩm (0: Đang bán, 1: Hết hàng, 2: Không bán): ");
        this.productStatus = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm
    }

    // Phương thức hiển thị dữ liệu
    public void displayData() {
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá sản phẩm: " + price);
        System.out.println("Mô tả sản phẩm: " + description);
        System.out.println("Ngày nhập sản phẩm: " + new java.text.SimpleDateFormat("dd/MM/yyyy").format(created));
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Trạng thái sản phẩm: " + (productStatus == 0 ? "Đang bán" : productStatus == 1 ? "Hết hàng" : "Không bán"));
    }
}
