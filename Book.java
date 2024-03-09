import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements IEntity {
    private String id; //Mã sách (bắt đầu bằng “B”, độ dài 4 kí tự, duy nhất)
    private String title;  //Tiêu đề sách (từ 6-50 kí tự, duy nhất)
    private String author; //Tên tác giả (không bỏ trống)
    private String publisher;//Nhà xuất bản (không bỏ trống)
    private int year;//Năm xuất bản (tối thiểu từ năm 1970 và không lớn hơn năm hiện   tại)
    private String description;//Mô tả sách (không bỏ trống)
    private int categoryId;//Mã thể loại sách (phải lấy từ danh sách Thể loại sách đã  lưu trước đó)

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void input(Scanner scanner) {
        inputId();
        inputTitle();
        inputAuthor();
        inputPublisher();
        inputYear();
        inputDescription();
    }

    @Override
    public void output() {
        System.out.printf("Mã sách: %s - Tiêu đề sách: %s - Tên tác giả: %s - Nhà xuất bản: %s - Năm xuất bản: %d\n",
                id,title,author,year);
        for (Category c : Library.categoryList){
            if (categoryId==c.getId()){
                c.output();
            }
        }
    }
//• output(): Hiển thị thông tin sách gồm mã sách, tiêu đề, tác giả, NXB, năm
//xuất bản, thể loại (hiển thị tên thể loại sách theo mã thể loại lúc nhập).
    public void inputId() {
        Scanner scanner = new Scanner(System.in);
        //bắt đầu bằng “B”, độ dài 4 kí tự, duy nhất
        System.out.println("Nhập id Sách Bắt đầu bằng chữ B và gồm 4 ký tự :");
        do {
            String inputId = scanner.nextLine();
            Pattern pattern = Pattern.compile("B(.){3}");
            Matcher check = pattern.matcher(inputId);
            if (check.matches()) {
                boolean check2 = true;
                for (Book bid : Library.bookList) {
                    if (inputId.equalsIgnoreCase(bid.id)) {
                        check2 = false;
                        break;
                    }
                }
                if (check2) {
                    this.id = inputId;
                    break;
                } else {
                    System.err.println("ID bị trùng yêu cầu nhập lại!");
                }
            } else {
                System.err.println("Sai định dạng yêu cầu nhập lại ");
            }
        } while (true);
    }

    public void inputTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tiêu đề của sách:");
        do {
            String titleInput = scanner.nextLine();
            if (titleInput.length() >= 6 && titleInput.length() <= 50) {
                boolean check = true;
                for (Book t : Library.bookList) {
                    if (titleInput.equalsIgnoreCase(t.title)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    title = titleInput;
                    break;
                } else {
                    System.err.println("Tiêu đề bị trùng, Mởi nhập lại!");
                }
            } else {
                System.err.println("Tiêu đề sách có độ dài 6-50 k tự,Mời nhập lại!");
            }
        } while (true);

    }

    public void inputAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên tác giả");
        do {
            String inputAuthorName = scanner.nextLine();
            if (!" ".equals(inputAuthorName) && !"".equals(inputAuthorName)) {
                boolean check = true;
                for (Book a : Library.bookList) {
                    if (inputAuthorName.equalsIgnoreCase(a.author)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    author = inputAuthorName;
                    break;
                } else {
                    System.err.println("Tên tác giả bị trùng, Mời nhập lại ");
                }
            } else {
                System.err.println("Không bỏ trống tên tác giả");
            }
        } while (true);
    }

    public void inputPublisher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên Nhà xuất bản");
        do {
            String inputPublisher = scanner.nextLine();
            if (!" ".equals(inputPublisher) && !"".equals(inputPublisher)) {
                boolean check = true;
                for (Book a : Library.bookList) {
                    if (inputPublisher.equalsIgnoreCase(a.publisher)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    publisher = inputPublisher;
                    break;
                } else {
                    System.err.println("Tên nhà xuất bản bị trùng, Mời nhập lại ");
                }
            } else {
                System.err.println("Không bỏ trống tên nhà xuất bản ");
            }
        } while (true);
    }

    public void inputYear() {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentYear = localDateTime.getYear();
        System.out.println("Nhập năm xuất bản  (tối thiểu từ năm 1970 và không lớn hơn năm hiện tại)");
        do {
            int inputYear = Integer.parseInt(scanner.nextLine());
            if (inputYear >= 1970 && inputYear <= currentYear) {
                year = inputYear;
                break;
            } else {
                System.err.println("hập năm xuất bản tối thiểu từ năm 1970 và không lớn hơn năm hiện tại");
            }
        } while (true);
    }

    public void inputDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập Mô tả sách ");
        do {
            String inputDescription = scanner.nextLine();
            if (!" ".equals(inputDescription) && !"".equals(inputDescription)) {
                description = inputDescription;
                break;
            } else {
                System.err.println("Không bỏ trống mô tả sách ");
            }
        } while (true);
    }
    public void inputCategoryId(){
        Scanner scanner = new Scanner(System.in);
        for (Category c: Library.categoryList){
            c.output();
        }
        System.out.println("Mời bạn nhập CategoryId:");
        do {
            int inputCategoryId = Integer.parseInt(scanner.nextLine());
          if (checkcategoryId(inputCategoryId)>=0){
              categoryId = inputCategoryId;
              break;
          }else {
              System.err.println("CategoryId không tồn tại, Mời nhập lại!");
          }
        }while (true);
    }
    public int checkcategoryId(int categoryId){
        for (int i = 0; i < Library.categoryList.size(); i++) {
            if (categoryId == Library.categoryList.get(i).getId()){
                return i;
            }
        }return -1;
    }
}
