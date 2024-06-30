package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookstore")
public class BookStoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Book> books = new ArrayList<>();
        try (Connection connection = DBconnection.getConnection()) {
            String query = "SELECT * FROM books";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    double price = resultSet.getDouble("price");
                    books.add(new Book(id, title, author, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("<html><head><title>Bookstore</title><link rel='stylesheet' type='text/css' href='style.css'></head><body>");
        out.println("<h1>Welcome to our Online Bookstore</h1>");
        out.println("<ul>");

        for (Book book : books) {
            out.println("<li>" + book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice() + "</li>");
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}
