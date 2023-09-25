package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
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
            String sql = "SELECT * FROM article";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("title"));
                System.out.println(rs.getString("content")); // 문자열로 리턴
                System.out.println(rs.getInt("id")); //
                System.out.println("========================");
            }

        } catch(Exception e) {
            System.out.println("접속 시도중 문제 발생!!");
        }

    }
}