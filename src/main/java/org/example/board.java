package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static java.lang.System.exit;

public class board {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
    exit();
            }
            else if (cmd.equals("add")) {
                add();
            } else if (cmd.equals("list")) {
                list();
            } else if (cmd.equals("update")) {
                update();
            } else if (cmd.equals("delete")) {
                delete();
            } else if (cmd.equals("search")) {
                search();
            }
        }
    }
    static void exit(){
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }

    static void add() {

        System.out.print("이름 : ");
        String name = scan.nextLine();
        System.out.print("주소 : ");
        String address = scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = scan.nextLine();

        Statement stmt = null; // SQL 전송하는 객체

        try {

            Connection conn = getConnection();
            stmt = conn.createStatement();


            //4. SQL 처리하고 결과 ResultSet에 받아오기
            String sql = String.format("INSERT INTO add_book SET `name` = '%s', `address` = '%s', `phone` = '%s'", name, address, phone);
            stmt.executeUpdate(sql);
            System.out.println("주소록 등록이 완료 되었습니다.");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void list() {
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {

            Connection conn = getConnection();
            stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM add_book");
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                System.out.printf("번호 : %d\n", id);
                System.out.printf("이름 : %s\n", name);
                System.out.printf("주소 : %s\n", address);
                System.out.printf("전화번호 : %s\n", phone);
                System.out.println("===========================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void update() {
        Statement stmt = null; // SQL 전송하는 객체


        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();
            ResultSet rs = null; // 결과 받아오는 객체


            System.out.print("주소록을 수정할 번호를 입력하세요 : ");
            int id = Integer.parseInt(scan.nextLine());

            String checkSql = "SELECT * FROM add_book WHERE id = " + id;
            rs = stmt.executeQuery(checkSql);

            if (rs.next()) {
                System.out.print("새로운 이름 : ");
                String newName = scan.nextLine();
                System.out.print("새로운 주소 : ");
                String newAddress = scan.nextLine();
                System.out.print("새로운 전화번호 : ");
                String newPhone = scan.nextLine();


                String sql = String.format("UPDATE add_book SET `name` = '%s', `address` = '%s', `phone` = '%s' WHERE id = '%d'", newName, newAddress, newPhone, id);
                stmt.executeUpdate(sql);
                System.out.println("주소록 수정이 완료 되었습니다.");
            } else {
                System.out.println("지정된 ID를 가진 레코드가 존재하지 않습니다.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void delete() {
        Statement stmt = null; // SQL 전송하는 객체

        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();

            System.out.print("주소록을 삭제할 번호를 입력하세요 : ");
            int targetId = Integer.parseInt(scan.nextLine());
            String sql = "DELETE FROM add_book WHERE id = " + targetId;
            targetId = stmt.executeUpdate(sql);

            if (targetId > 0) {
                System.out.println("삭제가 완료되었습니다.");
            } else {
                System.out.println("삭제할 항목이 없거나 삭제 중 오류가 발생했습니다.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void search() {

        System.out.print("주소록을 검색할 이름을 입력하세요 : ");
        String keyword = scan.nextLine();
        String sql = String.format("SELECT * FROM add_book WHERE `name` LIKE '%%%s%%' ", keyword);

        try {
            Connection conn = getConnection(); // DB 접속하는 객체
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                System.out.printf("번호 : %d\n", id);
                System.out.printf("이름 : %s\n", name);
                System.out.printf("주소 : %s\n", address);
                System.out.printf("전화번호 : %s\n", phone);
                System.out.println("===========================");
            }
            if (!rs.isBeforeFirst()) {
                System.out.println("검색 결과가 없습니다.");
            }


        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }


    static Connection getConnection() {
        Connection conn = null; // DB 접속하는 객체

        String url = "jdbc:mysql://localhost:3306/ad?serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
