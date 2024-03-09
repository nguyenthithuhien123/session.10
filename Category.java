import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity {
    private int id;    //Mã thể loại (số nguyên lớn hơn 0, duy nhất)
    private String name;   // Tên thể loại (không trùng nhau, từ 6-30 kí tự)
    private boolean status;  // Trạng thái thể loại (chỉ nhận true/false khi nhập)

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void input(Scanner scanner) {
        inputId();
        inputName();
        inputStatus();
    }
    @Override
    public void output() {
        System.out.printf("Id: %d - name: %s - Status: %s\n",
                id, name, (status ? "Đang hoạt động" : "Không hoạt động"));
    }

    public void inputId() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập id loại sách");
            int idInput = Integer.parseInt(scanner.nextLine());
            if (idInput > 0) {
                boolean check = true;
                for (Category c : Library.categoryList) {
                    if (c.id == idInput) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    this.id = idInput;
                    break;
                } else {
                    System.err.println("Id đã trùng yêu cầu nhập lại");
                }
            } else {
                System.err.println("Id là một số nguyên lớn hơn 0");
            }
        } while (true);

    }

    public void inputName() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập vào tên loại sách: ");
            String inputName = scanner.nextLine();
            if (inputName.length() >= 6 && inputName.length() <= 30) {
                boolean check = true;
                for (Category n : Library.categoryList) {
                    if (inputName.equalsIgnoreCase(n.name)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    name = inputName;
                    break;
                } else {
                    System.err.println("Tên loại sách bị trùng, mời nhập lại");
                }
            } else {
                System.err.println("Tên loại sách tu 6-30 ky tu");
            }
        } while (true);
    }

    public void inputStatus() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập vào trạng  thái: ");
            String check = scanner.nextLine();
            if (check.equalsIgnoreCase("true") || check.equalsIgnoreCase("false")) {
                status = Boolean.parseBoolean(check);
                break;
            } else {
                System.err.println("yêu cầu nhập true hoặc false");
            }
        } while (true);

    }
}
