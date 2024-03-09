import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static List<Category> categoryList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        do {
            menuLibrary();
            System.out.println("Lựa chọn của bạn: ");
            int chose = Integer.parseInt(scanner.nextLine());
            switch (chose) {
                case 1:
                    int chose2;
                    do {
                        menuCategory();
                        System.out.println("Lựa chọn của bạn: ");
                        chose2 = Integer.parseInt(scanner.nextLine());
                        switch (chose2) {
                            case 1:
                                addCategory();
                                break;
                            case 2:
                                showCategory();
                                break;
                            case 3:

                                break;
                            case 4:
                                updateCategory();
                                break;
                            case 5:
                                deleteCategory();
                                break;
                            case 6:
                                break;
                            default:
                                System.err.println("Mời bạn chọn từ 1-6");
                        }
                        if (chose2 == 6){
                            break;
                        }
                    } while (true);
                    break;
                case 2:
                    int chose3;
                    do {
                        menuBook();
                        System.out.println("Lựa chọn của bạn: ");
                        chose3 = Integer.parseInt(scanner.nextLine());
                        switch (chose3) {
                            case 1:
                              addBook();
                                break;
                            case 2:
                                updateBook();
                                break;
                            case 3:
                              deleteBook();
                                break;
                            case 4:
                                System.out.println("Nhập từ khóa tìm kiếm ");
                                break;
                            case 5:
                                break;
                            case 6:
                              break;
                            default:
                                System.err.println("Mời bạn chọn từ 1-6");
                        }
                        if (chose3 == 6){
                            break;
                        }
                    } while (true);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Chọn từ 1-3, Mời chọn lại: ");
            }
        } while (true);
    }

    public static void menuLibrary() {
        System.out.println("""
                ➢ ===== QUẢN LÝ THƯ VIỆN =====
                1. Quản lý Thể loại
                2. Quản lý Sách
                3. Thoát                                
                """);
    }

    public static void menuCategory() {
        System.out.println("""
                ➢ ===== QUẢN LÝ THỂ LOẠI =====
                1. Thêm mới thể loại
                2. Hiển thị danh sách theo tên A – Z
                3. Thống kê thể loại và số sách có trong mỗi thể loại
                4. Cập nhật thể loại
                5. Xóa thể loại
                6. Quay lại
                """);
    }

    public static void menuBook() {
        System.out.println("""
                ➢ ===== QUẢN LÝ SÁCH =====
                1. Thêm mới sách
                2. Cập nhật thông tin sách
                3. Xóa sách
                4. Tìm kiếm sách
                5. Hiển thị danh sách sách theo nhóm thể loại
                6. Quay lại
                """);
    }

    public static void addCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập số thể loại sách cần thêm:");
        int categoryNum = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < categoryNum; i++) {
            Category category = new Category();
            category.input(scanner);
            categoryList.add(category);
            System.out.println("Thêm thành công thể loại sách thứ " + (i + 1));
        }
    }

    public static void showCategory() {
        Scanner scanner = new Scanner(System.in);
        categoryList.sort(new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getName().charAt(0) - o2.getName().charAt(0);
            }
        });
        for (Category c : categoryList) {
            c.output();
        }
    }

    public static void updateCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập Id cần cập nhật ");
        int updateId = Integer.parseInt(scanner.nextLine());
        if (checkcategoryId(updateId) >= 0) {
            categoryList.get(checkcategoryId(updateId)).inputName();
            categoryList.get(checkcategoryId(updateId)).inputStatus();
            System.out.println("Cập nhật thành công");
            categoryList.get(checkcategoryId(updateId)).output();
        } else {
            System.out.println("ID không tồn tại");
        }
    }

    public static void deleteCategory() {
        System.out.println("Nhập Id thể loại sách cần xóa ");
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        int deleteId = Integer.parseInt(scanner.nextLine());
        for (Category c : categoryList) {
            if (deleteId == c.getId()) {
                check = true;
                break;
            }
        }
        if (check) {
            for (Book b : bookList) {
                if (deleteId == b.getCategoryId()) {
                    check = false;
                    break;
                }
            }
            if (check) {
                categoryList.remove(checkcategoryId(deleteId));
                System.out.println("Xóa thành công category có ID là " + deleteId);
            } else {
                System.out.println("Category đang được tham chiếu tới không được xóa");
            }
        } else {
            System.err.println("Id thể loại sách không tồn tại ");
        }
    }

    public static int checkcategoryId(int categoryId) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryId == categoryList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }
public static void addBook(){
        Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập số sách cần thêm ");
    int bookNum = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < bookNum; i++) {
        Book book = new Book();
        book.input(scanner);
        bookList.add(book);
        System.out.println("Thêm thành công sách thứ " + (i + 1));
    }
    for (Book b : bookList){
        b.output();
    }
}
public static void updateBook(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập id sách cần cập nhật");
    String updateBookId = scanner.nextLine();
    if (checkBook(updateBookId)>=0){
        bookList.get(checkBook(updateBookId)).inputTitle();
        bookList.get(checkBook(updateBookId)).inputAuthor();
        bookList.get(checkBook(updateBookId)).inputDescription();
        bookList.get(checkBook(updateBookId)).inputCategoryId();
        bookList.get(checkBook(updateBookId)).inputPublisher();
        System.out.println("Cập nhật thành công");
    }else {
        System.out.println("Id sách không tồn tại ");
    }
}
public static void deleteBook(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập id sách cần  xóa: ");
    String deleteBook = scanner.nextLine();
    if (checkBook(deleteBook)>=0){
        bookList.remove(checkBook(deleteBook));
        System.out.println("Sách xóa thành công");
    }else {
        System.err.println("Id sách không tồn tại");
    }
}
    public static int checkBook(String bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookId.equalsIgnoreCase(bookList.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}
