/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.dbexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bikeshkawan
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //1-  tell what driver we are using
            Class.forName("com.mysql.jdbc.Driver");
            //2 - need connection to connect through driver mention above 
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root");
            // to fire the querry we need statement into connection
            //Statement stmt = connection.createStatement();
//             String sql = "INSERT INTO table_contacts(first_name,last_name,email,phone,mobile,status)"
//                    + " VALUES('bijes','kawan','bijesh@gmail.com','12323131231','21323123123', 1)";

            String sql = "INSERT INTO table_contacts(first_name,last_name,email,phone,mobile,status)"
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            //set values for prepared statement
            stmt.setString(1, "john");
            stmt.setString(2, "paul");
            stmt.setString(3, "john@gmail.com");
            stmt.setString(4, "12123213123");
            stmt.setString(5, "324324324");
            stmt.setBoolean(6, true);

            //fire statement
            if (stmt.executeUpdate() > 0) {
                System.out.println("Record added sucessfully with prepared statement");
            }

//            if (stmt.executeUpdate(sql) > 0) {
//                System.out.println("Record Added Sucessfully");
//            }
            //sql query for select from databse
            sql = "SELECT * FROM  table_contacts";
            //execute statement and store result as resultset
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("========= List of records=======================");
            while (rs.next()) {

                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("first_name"));
                System.out.println(rs.getString("last_name"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("phone"));
                System.out.println(rs.getString("mobile"));
                System.out.println(rs.getBoolean("status"));
                System.out.println("================================");

            }

            Scanner input = new Scanner(System.in);
            System.out.println("================================");
            System.out.println("Do you want to delete records[Y/N] ?: ");
                if (input.next().equalsIgnoreCase("y")) {
                    System.out.println("Enter id to delete record: ");

                    int id = input.nextInt();

                    sql = "DELETE FROM table_contacts where id=" + id;

                    stmt.executeUpdate(sql);

                    System.out.println("sucessully deleted ");

                }
                connection.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + " not found");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " not found");
        }
    }

}
