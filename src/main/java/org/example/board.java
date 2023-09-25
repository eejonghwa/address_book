package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class board {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("add")) {
                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    //3. Statement 생성
                    stmt = conn.createStatement();

//            System.out.println("3번까지 문제 없이 실행");

                    System.out.println("이름 : ");
                    String name = scan.nextLine();
                    System.out.println("주소 : ");
                    String adrs = scan.nextLine();
                    System.out.println("전화번호 : ");
                    String cnum = scan.nextLine();

                    //4. SQL 처리하고 결과 ResultSet에 받아오기
                    String sql = "INSERT INTO address SET name = '"+ name + "', adrs = '"+adrs +"', cnum = '"+cnum+ "'";
                    stmt.executeUpdate(sql);
                    System.out.println("주소록 등록이 완료 되었습니다.");


                } catch (Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }

            } else if(cmd.equals("list")){
                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    //3. Statement 생성
                    stmt = conn.createStatement();

//            System.out.println("3번까지 문제 없이 실행");

                    //4. SQL 처리하고 결과 ResultSet에 받아오기
                    String sql = "SELECT * FROM address";
                    rs = stmt.executeQuery(sql);

                    while(rs.next()) {
                        System.out.println(rs.getInt("id"));
                        System.out.println(rs.getString("name"));
                        System.out.println(rs.getString("adrs")); // 문자열로 리턴
                        System.out.println(rs.getString("cnum")); //
                        System.out.println("========================");
                    }

                } catch(Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            }else if (cmd.equals("update")){
                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    //3. Statement 생성
                    stmt = conn.createStatement();

//            System.out.println("3번까지 문제 없이 실행");

                    System.out.print("주소록을 수정할 번호를 입력하세요 : ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.print("새로운 이름 : ");
                    String name = scan.nextLine();
                    System.out.print("새로운 주소 : ");
                    String adrs = scan.nextLine();
                    System.out.print("새로운 전화번호 : ");
                    String cnum = scan.nextLine();

                    //4. SQL 처리하고 결과 ResultSet에 받아오기
                    String sql = "UPDATE address SET name = '"+ name + "', adrs = '"+adrs +"', cnum = '"+cnum+ "' WHERE id = "+ id;
                    stmt.executeUpdate(sql);
                    System.out.println("주소록 수정이 완료 되었습니다.");


                } catch (Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            }else if (cmd.equals("delete")){

                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    stmt = conn.createStatement();


                    System.out.print("주소록을 삭제할 번호를 입력하세요 : ");
                    int id = Integer.parseInt(scan.nextLine());
                    String sql = "DELETE FROM address WHERE id = " + id;
                    int targetId = stmt.executeUpdate(sql);

                    if (targetId > 0) {
                        System.out.println("삭제가 완료되었습니다.");
                    } else {
                        System.out.println("삭제할 항목이 없거나 삭제 중 오류가 발생했습니다.");
                    }



                } catch(Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            }
        }
    }
}
