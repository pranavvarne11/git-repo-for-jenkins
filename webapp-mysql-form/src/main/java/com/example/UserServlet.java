package com.example;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");

        try {
            // DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demo_db", "root", "your_password");

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(name, mobile) VALUES(?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, mobile);

            int i = stmt.executeUpdate();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (i > 0) {
                out.println("<h3>Record inserted successfully</h3>");
            } else {
                out.println("<h3>Insertion failed</h3>");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

