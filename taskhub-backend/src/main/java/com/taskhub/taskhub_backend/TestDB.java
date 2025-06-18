package com.taskhub.taskhub_backend;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/TaskHub", "TaskHub", "0000");
        System.out.println("Connected successfully!");
        conn.close();
    }
}

