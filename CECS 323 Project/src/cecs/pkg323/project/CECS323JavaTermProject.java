package cecs.pkg323.project;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Rachel Bright
 * @author Tymee Kong
 */
public class CECS323JavaTermProject {
    //  Database credentials
    static String DBNAME;
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";
    static Connection conn;
/**
 * Takes the input string and outputs "N/A" if the string is empty or null.
 * @param input The string to be mapped.
 * @return  Either the input string or "N/A" as appropriate.
 */
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    
    public static void getAllWritingGroups(){
        try{
            String stmt = "SELECT group_name FROM WritingGroups";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            
            String displayFormat="%-5s\n";
            System.out.printf(displayFormat, "Group Name");
            while (rs.next()) {
                //Retrieve by column name
                String groupName = rs.getString("group_name");

                //Display values
                System.out.printf(displayFormat, dispNull(groupName));
            }
            rs.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void getWritingGroup(String group){
        try{
            String stmt = "SELECT group_name, head_writer, year_formed, subject FROM WritingGroups WHERE group_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, group);
            ResultSet rs = pstmt.executeQuery();
         
           String displayFormat="%-20s%-20s%-20s%-20s\n";
            System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
            while (rs.next()) {
                //Retrieve by column name
                String groupName = rs.getString("group_name");
                String headWriter = rs.getString("head_writer");
                String yearFormed = rs.getString("year_formed");
                String subject = rs.getString("subject");

                //Display values
                System.out.printf(displayFormat, dispNull(groupName), dispNull(headWriter), dispNull(yearFormed), dispNull(subject));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void getAllPublishers(){
         try{
            String stmt = "SELECT publisher_name FROM Publishers";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            
            String displayFormat="%-5s\n";
            System.out.printf(displayFormat, "Publisher Name");
            while (rs.next()) {
                //Retrieve by column name
                String publisherName = rs.getString("publisher_name");

                //Display values
                System.out.printf(displayFormat, dispNull(publisherName));
            }
            rs.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void getPublisher(String publisher){
        try{
            String stmt = "SELECT publisher_name, publisher_address, publisher_phone, publisher_email FROM Publishers WHERE publisher_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisher);
            ResultSet rs = pstmt.executeQuery();
         
            String displayFormat="%-20s%-40s%-20s%-20s\n";
            System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("publisher_name");
                String addr = rs.getString("publisher_address");
                String phone = rs.getString("publisher_phone");
                String email = rs.getString("publisher_email");

                //Display values
                System.out.printf(displayFormat, dispNull(name), dispNull(addr), dispNull(phone), dispNull(email));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void getAllBooks(){
        try{
            String stmt = "SELECT book_title FROM Books";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            
            String displayFormat="%-5s\n";
            System.out.printf(displayFormat, "Book Title");
            while (rs.next()) {
                //Retrieve by column name
                String bookTitle = rs.getString("book_title");

                //Display values
                System.out.printf(displayFormat, dispNull(bookTitle));
            }
            rs.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void getBook(String groupName, String bookTitle){
        try{
            String stmt = "SELECT group_name, book_title, publisher_name, year_published, number_pages FROM Books WHERE group_name = ? AND book_title = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, groupName);
            pstmt.setString(2, bookTitle);
            ResultSet rs = pstmt.executeQuery();
         
           String displayFormat="%-20s%-30s%-20s%-20s%-20s\n";
            System.out.printf(displayFormat, "Group Name", "Book Title", "Publisher Name", "Year Published", "Number of Pages");
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("group_name");
                String title = rs.getString("book_title");
                String publisher = rs.getString("publisher_name");
                String year = rs.getString("year_published");
                String pages = rs.getString("number_pages");

                //Display values
                System.out.printf(displayFormat, dispNull(name), dispNull(title), dispNull(publisher), dispNull(year), dispNull(pages));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void addBook(String groupName, String bookTitle, String publisherName, int yearPublished, int numPages){
        try{
            String stmt = "INSERT INTO Books (group_name, book_title, publisher_name, year_published, number_pages) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, groupName);
            pstmt.setString(2, bookTitle);
            pstmt.setString(3, publisherName);
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, numPages);
            pstmt.executeUpdate();
         
            String stmt2 = "SELECT group_name, book_title, publisher_name, year_published, number_pages FROM Books WHERE group_name = ? AND book_title = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(stmt2);
            pstmt2.setString(1, groupName);
            pstmt2.setString(2, bookTitle);
            ResultSet rs = pstmt2.executeQuery();
           
            String displayFormat="%-20s%-30s%-20s%-20s%-20s\n";
            System.out.printf(displayFormat, "Group Name", "Book Title", "Publisher Name", "Year Published", "Number of Pages");
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("group_name");
                String title = rs.getString("book_title");
                String publisher = rs.getString("publisher_name");
                String year = rs.getString("year_published");
                String pages = rs.getString("number_pages");

                //Display values
                System.out.printf(displayFormat, dispNull(name), dispNull(title), dispNull(publisher), dispNull(year), dispNull(pages));
            }
            rs.close();
            System.out.println("This book has been added successfully! :)");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void addPublisher(String publisherName, String publisherAddress, String publisherPhone, String publisherEmail, String publisherReplace){
        try{
            String stmt = "INSERT INTO Publishers (publisher_name, publisher_address, publisher_phone, publisher_email) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisherName);
            pstmt.setString(2, publisherAddress);
            pstmt.setString(3, publisherPhone);
            pstmt.setString(4, publisherEmail);
            pstmt.executeUpdate();
            
            String stmt2 = "UPDATE Books SET publisher_name = ? WHERE publisher_name = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(stmt2);
            pstmt2.setString(1, publisherName);
            pstmt2.setString(2, publisherReplace);
            pstmt2.executeUpdate();
            
            String stmt3 = "SELECT publisher_name, publisher_address, publisher_phone, publisher_email FROM Publishers WHERE publisher_name = ?";
            PreparedStatement pstmt3 = conn.prepareStatement(stmt3);
            pstmt3.setString(1, publisherName);
            ResultSet rs = pstmt3.executeQuery();
           
            String displayFormat="%-20s%-40s%-20s%-20s\n";
            System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("publisher_name");
                String addr = rs.getString("publisher_address");
                String phone = rs.getString("publisher_phone");
                String email = rs.getString("publisher_email");

                //Display values
                System.out.printf(displayFormat, dispNull(name), dispNull(addr), dispNull(phone), dispNull(email));
            }
            rs.close();
            System.out.println("This publisher has been added and replaced " + publisherReplace + " successfully! :)");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void removeBook(String groupName, String bookTitle){
         try{
            String stmt = "DELETE FROM Books WHERE group_name = ? AND book_title = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, groupName);
            pstmt.setString(2, bookTitle);
            pstmt.executeUpdate();
         
     
            System.out.println("This book has been removed successfully! :)");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to 
        //remove that from the connection string.
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        //Constructing the database URL connection string
        DB_URL = "jdbc:derby://localhost:1527/" + DBNAME;
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);

//            addBook("Write On", "bt", "HarperCollins", 1999, 420);
//            addPublisher("Long Beach Publisher", "123 Seasame St. Long Beach, CA 90804", "562-210-2345", "lbpublish@gmail.com", "Hachette Livre");
//            removeBook("Science Lovers", "Hypothesis");
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample}

