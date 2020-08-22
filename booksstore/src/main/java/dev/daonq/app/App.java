package dev.daonq.app;

import java.util.ArrayList;
import java.util.Scanner;

import dev.daonq.BusinessLogic.BookBL;
import dev.daonq.Entity.Book;
import dev.daonq.Entity.OrderDetail;

public class App {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            BookBL bookBL = new BookBL();

            ArrayList<OrderDetail> listOrderDetails = new ArrayList<>();

            String choice;
            int from = 0, to = 10;

            do {
                nextPage(from, to, bookBL, listOrderDetails);
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

                    case "10":
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
                                    viewBookDetail(book);
                                    choiceC = sc.nextLine();
                                    switch (choiceC) {
                                        case "1":
                                            int amount = 0;
                                            try {
                                                System.out.printf("Nhập số lượng (0..20): ");
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
                        viewOrderDetail(listOrderDetails);
                        break;

                    case "L":

                        break;

                    case "D":

                        break;

                    case "T":
                    case "t":
                        System.out.printf("Cảm ơn bạn vì đã sử dụng phần mềm của chúng tôi.");
                        break;

                    default:
                        System.out.println("Không có kết quả phù hợp với lựa chọn của bạn.");
                        break;
                }
            } while (!choice.equals("T") || !choice.equals("t"));
            sc.close();
        } catch (Exception e) {
            System.out.printf("Máy chủ bị lỗi hoặc mất sóng!");
        }
    }

    private static void viewOrderDetail(ArrayList<OrderDetail> listOrderDetails) {
        if (listOrderDetails.size() > 0) {
            BookBL bookBL = new BookBL();
            System.out.printf(
                    "\n*******************************************************************************************************************");
            System.out.printf(
                    "\n|                                             CHI TIẾT GIỎ HÀNG                                                   |");
            System.out.printf(
                    "\n*******************************************************************************************************************");
            System.out.printf("\n| %-4s | %-60s | %-10s | %-10s | %-1s |", "ID", "Tên sách", "Giá (VNĐ)", "Số lượng",
                    "Tạm tính (VNĐ)");
            Book book = null;
            for (int i = 0; i < listOrderDetails.size(); i++) {
                book = bookBL.getBookByID(listOrderDetails.get(i).getBookID());
                System.out.printf(
                        "\n-------------------------------------------------------------------------------------------------------------------");
                System.out.printf("\n| %-4d | %-60s | %-10.3f | %-10d | %-1.3f |\n", book.getID(), book.getTitle(),
                        book.getPrice(), listOrderDetails.get(i).getAmount(),
                        book.getPrice() * listOrderDetails.get(i).getAmount());

            }
            System.out.printf(
                    "*******************************************************************************************************************");
            System.out.printf("\n[1: Xóa] [2: Cập nhật số lượng] [0: Trở về] => ");
        } else{
            System.out.println("Giỏ hàng của bạn hiện tại trống.");
        }
    }

    private static void viewBookDetail(Book book) {
        System.out.println(
                "*************************************************************************************************");
        System.out.println(
                "|                                   THÔNG TIN CHI TIẾT                                          |");
        System.out.println(
                "*************************************************************************************************");
        System.out.printf("| %-30s | %-60s |\n", "Tiêu đề", book.getTitle());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Tác giả", book.getAuthor());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60.3f |\n", "Giá bán (VNĐ)", book.getPrice());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Công ty phát hành", book.getIssuingCompany());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Ngày xuất bản", book.getDateofPublication());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Kích thước", book.getDimensions());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Loại bìa", book.getCoverType());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60d |\n", "Số trang", book.getNumberofPages());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "SKU", book.getSKU());
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-60s |\n", "Nhà xuất bản", book.getPublishingCompany());
        System.out.println(
                "*************************************************************************************************");
        System.out.printf("[1: Thêm vào giỏ hàng] [0: Trở về] => ");
    }

    private static void nextPage(int from, int to, BookBL bookBL, ArrayList<OrderDetail> listOrderDetail) {
        ArrayList<Book> books = bookBL.getListBook();
        System.out.printf(
                "\n*********************************************************************************************************************");
        System.out.printf(
                "\n|                                   NHÀ SÁCH TRỰC TUYẾN AKALI                                   Hotline: 0987654321 |\n");
        System.out.printf(
                "*********************************************************************************************************************");
        System.out.printf("\n| %-4s | %-60s | %-30s | %-10s |\n", "ID", "TÊN SÁCH", "TÁC GIẢ", "GIÁ (VNĐ)");

        for (int i = from; i < to; i++) {
            System.out.printf(
                    "---------------------------------------------------------------------------------------------------------------------");
            System.out.printf("\n| %-4d | %-60s | %-30s | %-10.3f |\n", books.get(i).getID(), books.get(i).getTitle(),
                    books.get(i).getAuthor(), books.get(i).getPrice());

        }
        System.out.printf(
                "*********************************************************************************************************************");
        System.out.println();
        if (from == 0 && to == 10) {
            System.out.println(
                    "                                                                            [1] - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 10 && to == 20) {
            System.out.println(
                    "                                                                            1 - [2] - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 20 && to == 30) {
            System.out.println(
                    "                                                                            1 - 2 - [3] - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 30 && to == 40) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - [4] - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 40 && to == 50) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - [5] - 6 - 7 - 8 - 9 - 10");
        } else if (from == 50 && to == 60) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - 5 - [6] - 7 - 8 - 9 - 10");
        } else if (from == 60 && to == 70) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - 5 - 6 - [7] - 8 - 9 - 10");
        } else if (from == 70 && to == 80) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - 5 - 6 - 7 - [8] - 9 - 10");
        } else if (from == 80 && to == 90) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - [9] - 10");
        } else if (from == 90 && to == 100) {
            System.out.println(
                    "                                                                            1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - [10]");
        }
        System.out.printf(
                "[1..10: Chuyển trang] [C: Chọn sách] [G: Giỏ hàng (%d)] [L: Lịch sử] [D: Đặt hàng] [T: Thoát] => ",
                listOrderDetail.size());
    }
}
