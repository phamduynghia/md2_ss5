import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ShopManagement {
    private static Categories[] arrCategories = new Categories[100];
    private static Product[] arrProducts = new Product[100];
    private static int categoryCount = 0;
    private static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("---------------------------SHOP MENU---------------------------");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    manageCategories(scanner);
                    break;
                case 2:
                    manageProducts(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
        scanner.close();
    }

    private static void manageCategories(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("---------------------------CATEGORIES MENU---------------------------");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Quay lại menu SHOP MANAGEMENT");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    inputCategories(scanner);
                    break;
                case 2:
                    displayCategories();
                    break;
                case 3:
                    updateCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                    updateCategoryStatus(scanner);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void manageProducts(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("---------------------------PRODUCT MANAGEMENT---------------------------");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b");
            System.out.println("8. Quay lại menu SHOP MANAGEMENT");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    inputProducts(scanner);
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    sortProductsByPrice();
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    searchProductsByName(scanner);
                    break;
                case 7:
                    searchProductsByPriceRange(scanner);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void inputCategories(Scanner scanner) {
        System.out.print("Nhập số lượng danh mục cần thêm: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm

        for (int i = 0; i < count; i++) {
            if (categoryCount < arrCategories.length) {
                Categories category = new Categories();
                category.inputData(scanner);
                arrCategories[categoryCount++] = category;
            } else {
                System.out.println("Danh mục đã đầy.");
                break;
            }
        }
    }

    private static void displayCategories() {
        for (int i = 0; i < categoryCount; i++) {
            arrCategories[i].displayData();
            System.out.println();
        }
    }

    private static void updateCategory(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần cập nhật: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm
        boolean found = false;

        for (int i = 0; i < categoryCount; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                arrCategories[i].inputData(scanner);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mã danh mục không tồn tại.");
        }
    }

    private static void deleteCategory(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm
        boolean found = false;

        for (int i = 0; i < categoryCount; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                boolean hasProduct = false;
                for (int j = 0; j < productCount; j++) {
                    if (arrProducts[j].getCatalogId() == catalogId) {
                        hasProduct = true;
                        break;
                    }
                }

                if (hasProduct) {
                    System.out.println("Danh mục này có chứa sản phẩm, không thể xóa.");
                } else {
                    for (int k = i; k < categoryCount - 1; k++) {
                        arrCategories[k] = arrCategories[k + 1];
                    }
                    arrCategories[--categoryCount] = null;
                    System.out.println("Danh mục đã được xóa.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mã danh mục không tồn tại.");
        }
    }

    private static void updateCategoryStatus(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần cập nhật trạng thái: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm
        boolean found = false;

        for (int i = 0; i < categoryCount; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                arrCategories[i].setCatalogStatus(!arrCategories[i].isCatalogStatus());
                System.out.println("Trạng thái danh mục đã được cập nhật.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mã danh mục không tồn tại.");
        }
    }

    private static void inputProducts(Scanner scanner) {
        System.out.print("Nhập số lượng sản phẩm cần thêm: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm

        for (int i = 0; i < count; i++) {
            if (productCount < arrProducts.length) {
                Product product = new Product();
                product.inputData(scanner, arrCategories);
                arrProducts[productCount++] = product;
            } else {
                System.out.println("Danh sách sản phẩm đã đầy.");
                break;
            }
        }
    }

    private static void displayProducts() {
        for (int i = 0; i < productCount; i++) {
            arrProducts[i].displayData();
            System.out.println();
        }
    }

    private static void sortProductsByPrice() {
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 0; i < productCount; i++) {
            productList.add(arrProducts[i]);
        }

        Collections.sort(productList, Comparator.comparing(Product::getPrice));

        for (Product product : productList) {
            product.displayData();
            System.out.println();
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String productId = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                arrProducts[i].inputData(scanner, arrCategories);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mã sản phẩm không tồn tại.");
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                for (int j = i; j < productCount - 1; j++) {
                    arrProducts[j] = arrProducts[j + 1];
                }
                arrProducts[--productCount] = null;
                System.out.println("Sản phẩm đã được xóa.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mã sản phẩm không tồn tại.");
        }
    }

    private static void searchProductsByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(name.toLowerCase())) {
                arrProducts[i].displayData();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    private static void searchProductsByPriceRange(Scanner scanner) {
        System.out.print("Nhập giá thấp nhất: ");
        float minPrice = scanner.nextFloat();
        System.out.print("Nhập giá cao nhất: ");
        float maxPrice = scanner.nextFloat();
        scanner.nextLine(); // Xóa bộ đệm
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            float price = arrProducts[i].getPrice();
            if (price >= minPrice && price <= maxPrice) {
                arrProducts[i].displayData();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm trong khoảng giá này.");
        }
    }
}
