package dev.daonq.app;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import dev.daonq.BusinessLogic.BookBL;
import dev.daonq.BusinessLogic.CustomerBL;
import dev.daonq.BusinessLogic.OrderBL;
import dev.daonq.BusinessLogic.OrderDetailBL;
import dev.daonq.Entity.Book;
import dev.daonq.Entity.Customer;
import dev.daonq.Entity.Order;
import dev.daonq.Entity.OrderDetail;

public class App {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        System.setProperty("file.encoding", "UTF-8");
        java.lang.reflect.Field charset = null;
        charset = java.nio.charset.Charset.class.getDeclaredField("defaultCharset");
        charset.setAccessible(true);
        charset.set(null, null);
        try {
            Scanner sc = new Scanner(System.in);

            BookBL bookBL = new BookBL();

            ArrayList<OrderDetail> listOrderDetails = new ArrayList<>();

            String choice;

            int from = 0, to = 10;

            Boolean login = false;

            Customer customer = null;

            do {
                cls();
                nextPage(from, to, listOrderDetails);
                choice = sc.nextLine();
                switch (choice) {

                    case "1":
                        from = 0;
                        to = 10;
                        break;

                    case "2":
                        from = 10;
                        to = 20;
                        break;

                    case "3":
                        from = 20;
                        to = 30;
                        break;

                    case "4":
                        from = 30;
                        to = 40;
                        break;

                    case "5":
                        from = 40;
                        to = 50;
                        break;

                    case "6":
                        from = 50;
                        to = 60;
                        break;

                    case "7":
                        from = 60;
                        to = 70;
                        break;

                    case "8":
                        from = 70;
                        to = 80;
                        break;

                    case "9":
                        from = 80;
                        to = 90;
                        break;

                    case "0":
                        from = 90;
                        to = 100;
                        break;

                    case "C":
                    case "c":
                        try {
                            System.out.printf("Nhập ID: ");
                            int ID = Integer.parseInt(sc.nextLine());
                            Book book = bookBL.getBookByID(ID);
                            if (book != null) {
                                String choiceC;
                                do {
                                    cls();
                                    viewBookDetail(book);
                                    choiceC = sc.nextLine();
                                    switch (choiceC) {
                                        case "1":
                                            int amount = 0;
                                            try {
                                                System.out.printf("Nhập số lượng (1..20): ");
                                                amount = Integer.parseInt(sc.nextLine());
                                                if (amount >= 1 && amount <= 20) {
                                                    if (listOrderDetails == null) {
                                                        listOrderDetails = new ArrayList<OrderDetail>();
                                                    }
                                                    int check = 0;
                                                    for (int i = 0; i < listOrderDetails.size(); i++) {
                                                        if (ID == listOrderDetails.get(i).getBookID()) {
                                                            int newAmount = listOrderDetails.get(i).getAmount()
                                                                    + amount;
                                                            if (newAmount >= 1 && newAmount <= 20) {
                                                                listOrderDetails.get(i).setAmount(
                                                                        listOrderDetails.get(i).getAmount() + amount);
                                                                check = 1;
                                                                System.out.println("Thêm vào giỏ hàng thành công.");
                                                            } else {
                                                                System.out.println("Bạn chỉ được mua tối đa 20 quyển.");
                                                                check = 2;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    if (check == 0) {
                                                        OrderDetail oDetail = new OrderDetail();
                                                        oDetail.setAmount(amount);
                                                        oDetail.setBookID(ID);
                                                        listOrderDetails.add(oDetail);
                                                        System.out.println("Thêm vào giỏ hàng thành công.");
                                                    }
                                                } else {
                                                    System.out.println("Số lượng tối thiểu là 1 và tối đa là 20.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Đã xảy ra lỗi trong quá trình nhập số lượng.");
                                            }
                                            break;
                                        case "0":

                                            break;
                                        default:
                                            System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                                            break;
                                    }
                                } while (!choiceC.equals("0"));

                            } else {
                                System.out.println("Rất tiếc! Chúng tôi không tìm thấy ID bạn vừa nhập.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi trong quá trình chọn sách.");
                        }
                        break;

                    case "G":
                    case "g":
                        cls();
                        try {
                            if (listOrderDetails.size() > 0) {
                                String choiceG;
                                do {
                                    viewOrderDetail(listOrderDetails);
                                    choiceG = sc.nextLine();
                                    switch (choiceG) {
                                        case "1":
                                            try {
                                                byte check = 0;
                                                System.out.printf("Nhập ID: ");
                                                int ID = Integer.parseInt(sc.nextLine());
                                                for (int i = 0; i < listOrderDetails.size(); i++) {
                                                    if (ID == listOrderDetails.get(i).getBookID()) {
                                                        listOrderDetails.remove(i);
                                                        System.out.println("Xóa khỏi giỏ hàng thành công.");
                                                        pressEnter(sc);
                                                        check = 1;
                                                        break;
                                                    }
                                                }
                                                if (check == 0) {
                                                    System.out.println(
                                                            "Rất tiếc! Chúng tôi không tìm thấy ID bạn vừa nhập.");
                                                } else if (check == 1 && listOrderDetails.size() == 0) {
                                                    choiceG = "0";
                                                }
                                            } catch (Exception e) {
                                                System.out
                                                        .println("Đã có lỗi xảy ra trong quá trình xóa khỏi giỏ hàng.");
                                            }
                                            break;
                                        case "2":
                                            try {
                                                byte check = 0;
                                                System.out.printf("Nhập ID: ");
                                                int ID = Integer.parseInt(sc.nextLine());
                                                for (int i = 0; i < listOrderDetails.size(); i++) {
                                                    if (ID == listOrderDetails.get(i).getBookID()) {
                                                        System.out.printf("Nhập số lượng mới (1..20): ");
                                                        int slm = Integer.parseInt(sc.nextLine());
                                                        if (slm >= 1 && slm <= 20) {
                                                            listOrderDetails.get(i).setAmount(slm);
                                                            System.out.println("Cập nhật số lượng thành công.");
                                                        } else {
                                                            System.out.println("Cập nhật số lượng thất bại.");
                                                        }
                                                        check = 1;
                                                        break;
                                                    }
                                                }
                                                if (check == 0) {
                                                    System.out.println(
                                                            "Rất tiếc! Chúng tôi không tìm thấy ID bạn vừa nhập.");
                                                }
                                            } catch (Exception e) {
                                                System.out
                                                        .println("Đã có lỗi xảy ra trong quá trình cập nhật số lượng.");
                                            }
                                            break;
                                        case "0":

                                            break;
                                        default:
                                            System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                                            break;
                                    }
                                } while (!choiceG.equals("0"));
                            } else {
                                System.out.println("Giỏ hàng hiện tại trống.");
                                pressEnter(sc);
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi trong quá trình chỉnh sửa giỏ hàng.");
                        }
                        break;

                    case "L":
                    case "l":
                        if (login) {
                            viewHistories(customer, sc);
                        } else {
                            String yon;
                            do {
                                System.out.printf(
                                        "Bạn cần đăng nhập để thực hiện chức năng này? [1: Đăng nhập] [0: Trở về] => ");
                                yon = sc.nextLine();
                                switch (yon) {
                                    case "1":
                                        customer = Login(sc);
                                        if (customer != null) {
                                            System.out.println("Đăng nhập thành công.");
                                            login = true;
                                            viewHistories(customer, sc);
                                            yon = "0";
                                        } else {
                                            System.out.println("Đăng nhập thất bại.");
                                            pressEnter(sc);
                                            yon = "0";
                                        }
                                        break;
                                    case "0":

                                        break;
                                    default:
                                        System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                                        break;
                                }
                            } while (!yon.equals("0"));
                        }
                        break;

                    case "D":
                        cls();
                        if (listOrderDetails.size() > 0) {
                            if (login) {
                                datHang(listOrderDetails, sc, customer);
                            } else {
                                String yon;
                                do {
                                    System.out.printf(
                                            "Bạn cần đăng nhập để thực hiện chức năng này? [1: Đăng nhập], [0: Trở về] => ");
                                    yon = sc.nextLine();
                                    switch (yon) {
                                        case "1":
                                            customer = Login(sc);
                                            if (customer != null) {
                                                System.out.println("Đăng nhập thành công.");
                                                login = true;
                                                datHang(listOrderDetails, sc, customer);
                                                pressEnter(sc);
                                                yon = "0";
                                            } else {
                                                System.out.println("Đăng nhập thất bại.");
                                                pressEnter(sc);
                                                yon = "0";
                                            }
                                            break;
                                        case "0":

                                            break;
                                        default:
                                            System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                                            break;
                                    }
                                } while (!yon.equals("0"));
                            }
                        } else {
                            System.out.println("Không thể đặt hàng vì giỏ hàng trống.");
                            pressEnter(sc);
                        }
                        break;

                    case "T":
                        System.out.printf("Cảm ơn bạn vì đã sử dụng phần mềm của chúng tôi.");
                        break;

                    default:
                        System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                        pressEnter(sc);
                        break;
                }
            } while (!choice.equals("T"));
            sc.close();
        } catch (Exception e) {
            System.out.printf("Máy chủ bị lỗi hoặc mất sóng!");
        }
    }

    private static void pressEnter(Scanner sc) {
        System.out.printf("\nNhấn Enter để tiếp tục...");
        sc.nextLine();
    }

    private static void cls() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void datHang(ArrayList<OrderDetail> listOrderDetails, Scanner sc, Customer customer) {
        try {
            BookBL bookBL = new BookBL();
            Double thanhtien = 0.0;
            for (int i = 0; i < listOrderDetails.size(); i++) {
                Book book = bookBL.getBookByID(listOrderDetails.get(i).getBookID());
                thanhtien += listOrderDetails.get(i).getAmount() * book.getPrice();
            }
            OrderBL orderBL = new OrderBL();
            orderBL.insertOrder(customer.getID(), thanhtien);
            OrderDetailBL orderDetailBL = new OrderDetailBL();
            Order order = orderBL.getOrderByCustomerID(customer.getID());
            for (int i = 0; i < listOrderDetails.size(); i++) {
                listOrderDetails.get(i).setOrderID(order.getID());
                orderDetailBL.insertOrderDetail(listOrderDetails.get(i));
            }
            viewInvoice(listOrderDetails, customer, order);
            listOrderDetails.clear();
        } catch (Exception e) {
            System.out.println("Đã có lỗi xảy ra trong quá trình đặt hàng.");
        }
    }

    private static void viewHistories(Customer customer, Scanner sc) {
        try {
            OrderBL bl = new OrderBL();
            OrderDetailBL orderDetailBL = new OrderDetailBL();
            BookBL bookBL = new BookBL();
            ArrayList<Order> listOrders = bl.getListOrderByCustomerID(customer.getID());
            if (listOrders.size() > 0) {
                cls();
                ArrayList<OrderDetail> orderDetails = null;
                System.out.printf("\nĐƠN HÀNG CỦA BẠN");
                System.out.printf(
                        "\no----------------------------------------------------------------------------------------------------------------------------------------o");
                System.out.printf("\n| %-5s | %-21s | %-84s | %-15s |", "ID", "Ngày đặt hàng", "Sản phẩm", "Tổng tiền");

                for (int i = 0; i < listOrders.size(); i++) {
                    System.out.printf(
                            "\n|----------------------------------------------------------------------------------------------------------------------------------------|");
                    orderDetails = orderDetailBL.getListOrderDetailByOrderID(listOrders.get(i).getID());
                    String sanpham = "";
                    if (orderDetails.size() > 1) {
                        Book book = bookBL.getBookByID(orderDetails.get(0).getBookID());
                        if (book != null) {
                            sanpham = book.getTitle() + " ... và " + (orderDetails.size() - 1) + " sản phẩm khác.";
                        }
                    } else if (orderDetails.size() == 1) {
                        Book book = bookBL.getBookByID(orderDetails.get(0).getBookID());
                        if (book != null) {
                            sanpham = book.getTitle();
                        }
                    }
                    System.out.printf("\n| %-5d | %-21s | %-84s | %-15s |", listOrders.get(i).getID(),
                            listOrders.get(i).getDate(), sanpham, listOrders.get(i).getTotalDue());

                }
                System.out.printf(
                        "\no----------------------------------------------------------------------------------------------------------------------------------------o");

                byte check = 0;
                System.out.printf("\nNhập ID: ");
                int ID = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < listOrders.size(); i++) {
                    if (ID == listOrders.get(i).getID()) {
                        orderDetails = orderDetailBL.getListOrderDetailByOrderID(ID);
                        viewInvoice(orderDetails, customer, listOrders.get(i));
                        check = 1;
                        pressEnter(sc);
                        break;
                    }
                }
                if (check == 0) {
                    System.out.println("Rất tiếc! Chúng tôi không tìm thấy ID bạn vừa nhập.");
                    pressEnter(sc);
                }
            } else {
                System.out.println("Lịch sử mua hàng trống.");
                pressEnter(sc);
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi trong quá trình xem lịch sử đơn hàng.");
        }
    }

    private static void viewInvoice(ArrayList<OrderDetail> listOrderDetails, Customer customer, Order order) {
        try {
            cls();
            System.out.printf("\nTHÔNG TIN HÓA ĐƠN");
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf(
                    "\n| Đơn vị bán hàng  : CÔNG TY TNHH MTV THƯƠNG MẠI AKALI                                                            |");
            System.out.printf(
                    "\n| Mã số thuế       : 0 3 1 2 3 8 8 3 6 3                                                                          |");
            System.out.printf(
                    "\n| Địa chỉ          : 18 Tam Trinh, Hai Bà Trưng, Hà Nội                                                           |");
            System.out.printf(
                    "\n| Điện thoại       : (+84) 987 65 432 1                                                                           |");
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf("\n| Họ tên khách hàng: %-92s |", customer.getName().toUpperCase());
            System.out.printf("\n| Email            : %-92s |", customer.getEmail());
            System.out.printf("\n| Số điện thoại    : %-92s |", customer.getPhone());
            System.out.printf("\n| Địa chỉ          : %-92s |", customer.getAddress());
            System.out.printf("\n| Ngày in          : %-92s |", order.getDate());
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf("\n| %-4s | %-60s | %-10s | %-10s | %-15s |", "STT", "Sản phẩm", "Giá(VNĐ)", "Số lượng",
                    "Thành tiền(VNĐ)");
            System.out.printf(
                    "\n|-----------------------------------------------------------------------------------------------------------------|");
            Double thanhtien = 0.0;
            Book l = new Book();
            BookBL bookBL = new BookBL();
            for (int i = 0; i < listOrderDetails.size(); i++) {
                l = bookBL.getBookByID(listOrderDetails.get(i).getBookID());
                System.out.printf("\n| %-4d | %-60s | %-10.3f | %-10d | %-15.3f |", i, l.getTitle(), l.getPrice(),
                        listOrderDetails.get(i).getAmount(), l.getPrice() * listOrderDetails.get(i).getAmount());
                thanhtien = thanhtien + (l.getPrice() * listOrderDetails.get(i).getAmount());
                System.out.printf(
                        "\n|-----------------------------------------------------------------------------------------------------------------|");
            }
            System.out.printf(
                    "\n|                                                                                 Tổng cộng(VNĐ): %-15.3f |",
                    order.getTotalDue());
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o\n");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi xem hóa đơn.");
        }
    }

    private static Customer Login(Scanner sc) {
        System.out.printf("\nĐĂNG NHẬP\n");
        System.out.printf("Email:     ");
        String Email = sc.nextLine();
        java.io.Console console = System.console();
        char[] passwordArray = console.readPassword("Mật khẩu: ");
        String Password = new String(passwordArray);
        Password = MD5(Password);
        CustomerBL bl = new CustomerBL();
        Customer customer = bl.getCustomerByEmail(Email);
        if (customer != null && customer.getPassword().equals(Password)) {
            return customer;
        }
        return null;
    }

    private static String MD5(String Password) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(Password.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void viewOrderDetail(ArrayList<OrderDetail> listOrderDetails) {
        if (listOrderDetails.size() > 0) {
            BookBL bookBL = new BookBL();

            System.out.printf("\nCHI TIẾT GIỎ HÀNG");
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf("\n| %-4s | %-60s | %-10s | %-10s | %-15s |", "ID", "Tên sách", "Giá(VNĐ)", "Số lượng",
                    "Thành tiền(VNĐ)");
            Book book = null;
            Double thanhtien = 0.0;
            for (int i = 0; i < listOrderDetails.size(); i++) {
                book = bookBL.getBookByID(listOrderDetails.get(i).getBookID());
                System.out.printf(
                        "\n|-----------------------------------------------------------------------------------------------------------------|");
                System.out.printf("\n| %-4d | %-60s | %-10.3f | %-10d | %-15.3f |", book.getID(), book.getTitle(),
                        book.getPrice(), listOrderDetails.get(i).getAmount(),
                        book.getPrice() * listOrderDetails.get(i).getAmount());
                thanhtien += book.getPrice() * listOrderDetails.get(i).getAmount();
            }
            System.out.printf(
                    "\n|-----------------------------------------------------------------------------------------------------------------|");
            System.out.printf(
                    "\n|                                                                                 Tổng cộng(VNĐ): %-15.3f |",
                    thanhtien);
            System.out.printf(
                    "\no-----------------------------------------------------------------------------------------------------------------o");
            System.out.printf("\n[1: Xóa khỏi giỏ hàng], [2: Cập nhật số lượng], [0: Trở về] => ");
        } else {
            System.out.println("Giỏ hàng của bạn hiện tại trống.");
        }
    }

    private static void viewBookDetail(Book book) {

        System.out.println(" THÔNG TIN CHI TIẾT");
        System.out.println(
                "o--------------------------------o--------------------------------------------------------------o");
        System.out.printf("| %-30s | %-60s |\n", "Tiêu đề", book.getTitle());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Tác giả", book.getAuthor());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60.3f |\n", "Giá bán (VNĐ)", book.getPrice());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Công ty phát hành", book.getIssuingCompany());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Ngày xuất bản", book.getDateofPublication());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Kích thước", book.getDimensions());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Loại bìa", book.getCoverType());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60d |\n", "Số trang", book.getNumberofPages());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "SKU", book.getSKU());
        System.out.println(
                "|--------------------------------|--------------------------------------------------------------|");
        System.out.printf("| %-30s | %-60s |\n", "Nhà xuất bản", book.getPublishingCompany());
        System.out.println(
                "o--------------------------------o--------------------------------------------------------------o");
        System.out.printf("[1: Chọn mua], [0: Trở về] => ");
    }

    private static void nextPage(int from, int to, ArrayList<OrderDetail> listOrderDetail) {
        BookBL bookBL = new BookBL();
        ArrayList<Book> books = bookBL.getListBook();
        if (from == 0 && to == 10) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     [1] - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 0 ");
        } else if (from == 10 && to == 20) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - [2] - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 0 ");
        } else if (from == 20 && to == 30) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - [3] - 4 - 5 - 6 - 7 - 8 - 9 - 0 ");
        } else if (from == 30 && to == 40) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - [4] - 5 - 6 - 7 - 8 - 9 - 0 ");
        } else if (from == 40 && to == 50) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - [5] - 6 - 7 - 8 - 9 - 0 ");
        } else if (from == 50 && to == 60) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - 5 - [6] - 7 - 8 - 9 - 0 ");
        } else if (from == 60 && to == 70) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - 5 - 6 - [7] - 8 - 9 - 0 ");
        } else if (from == 70 && to == 80) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - 5 - 6 - 7 - [8] - 9 - 0 ");
        } else if (from == 80 && to == 90) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - [9] - 0 ");
        } else if (from == 90 && to == 100) {
            System.out.println(
                    " TOP 100 QUYỂN SÁCH BÁN CHẠY THÁNG NÀY                                     1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - [0] ");
        }
        System.out.printf(
                "o------o--------------------------------------------------------------o--------------------------------o------------o");
        System.out.printf("\n| %-4s | %-60s | %-30s | %-10s |\n", "ID", "TÊN SÁCH", "TÁC GIẢ", "GIÁ (VNĐ)");

        for (int i = from; i < to; i++) {
            System.out.printf(
                    "|------|--------------------------------------------------------------|--------------------------------|------------|");
            System.out.printf("\n| %-4d | %-60s | %-30s | %-10.3f |\n", books.get(i).getID(), books.get(i).getTitle(),
                    books.get(i).getAuthor(), books.get(i).getPrice());

        }
        System.out.printf(
                "o------o--------------------------------------------------------------o--------------------------------o------------o");
        System.out.println();

        System.out.printf(
                "[0..9: Chuyển trang], [C: Chọn sách], [G: Giỏ hàng (%d)], [L: Lịch sử], [D: Đặt hàng], [T: Thoát] => ",
                listOrderDetail.size());
    }
}
