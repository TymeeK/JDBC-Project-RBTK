package cecs.pkg323.project;

import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

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
            String stmt = "SELECT group_name, head_writer, year_formed, subject FROM WritingGroups ORDER BY group_name";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
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
    
    public static void getWritingGroup(String group){
        try{
            //This is checking if the writing group exists
            String checkStatement = "SELECT group_name FROM WritingGroups WHERE group_name = ?";
            PreparedStatement pcstmt = conn.prepareStatement(checkStatement);
            pcstmt.setString(1, group);
            ResultSet rs1 = pcstmt.executeQuery();
            if (!rs1.next()) {
                System.out.println("This writing group does not exist! \nReturning to main menu");
                return;
            }
            
            String stmt = "SELECT group_name, head_writer, year_formed, subject FROM WritingGroups WHERE group_name = ? ORDER BY group_name";
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
            String stmt = "SELECT publisher_name, publisher_address, publisher_phone, publisher_email FROM Publishers ORDER BY publisher_name";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            
            String displayFormat="%-30s%-50s%-20s%-20s\n";
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
    
    public static void getPublisher(String publisher){
        try{
            
            String stmt = "SELECT publisher_name, publisher_address, publisher_phone, publisher_email FROM Publishers WHERE publisher_name = ? ORDER BY publisher_name";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisher);
            ResultSet rs = pstmt.executeQuery();
         
            String displayFormat="%-30s%-50s%-20s%-20s\n";
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
            String stmt = "SELECT group_name, book_title, publisher_name, year_published, number_pages FROM Books ORDER BY book_title";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            
            String displayFormat="%-20s%-25s%-30s%-20s%-20s\n";
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
    
    public static void getBook(String groupName, String bookTitle){
        try{
            String stmt = "SELECT group_name, book_title, publisher_name, year_published, number_pages FROM Books WHERE group_name = ? AND book_title = ? ORDER BY book_title";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, groupName);
            pstmt.setString(2, bookTitle);
            ResultSet rs = pstmt.executeQuery();
         
            String displayFormat="%-20s%-25s%-30s%-20s%-20s\n";
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
    //We need to add checks into the addBook section
    public static void addBook(String groupName, String bookTitle, String publisherName, int yearPublished, int numPages){
        try{
            //Checking for book and group
            String stmt0 = "SELECT book_title, group_name FROM Books WHERE book_title = ? AND group_name = ?";
            PreparedStatement psmt0 = conn.prepareStatement(stmt0);
            psmt0.setString(1, bookTitle);
            psmt0.setString(2, groupName);
            ResultSet rs0 = psmt0.executeQuery();
            if (rs0.next()) {
                System.out.println("This book and group name already exists! \nGoing back to the main menu");
                return;
            }
            
           
            //Checking if the group name exists
            String checkStatement1 = "SELECT group_name FROM WritingGroups WHERE group_name = ?";
            PreparedStatement pstmt4 = conn.prepareStatement(checkStatement1);
            pstmt4.setString(1, groupName);
            ResultSet rs2 = pstmt4.executeQuery();
            if (!rs2.next()) {
                System.out.println("This group name does not exist! \nGoing back to the main menu");
                return;
            }
            
            //Checking if the publisher exists or not 
            String checkStatement = "SELECT publisher_name FROM Publishers WHERE publisher_name = ?";
            PreparedStatement pstmt3 = conn.prepareStatement(checkStatement);
            pstmt3.setString(1, publisherName);
            ResultSet rs1 = pstmt3.executeQuery();
            if (!rs1.next()) {
                System.out.println("This publisher does not exist! \nGoing back to the main menu");
                return;
            }
       
            
            String stmt = "INSERT INTO Books (group_name, book_title, publisher_name, year_published, number_pages) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, groupName);
            pstmt.setString(2, bookTitle);
            pstmt.setString(3, publisherName);
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, numPages);
            pstmt.executeUpdate();
         
            String stmt2 = "SELECT group_name, book_title, publisher_name, year_published, number_pages FROM Books WHERE group_name = ? AND book_title = ? ORDER BY book_title";
            PreparedStatement pstmt2 = conn.prepareStatement(stmt2);
            pstmt2.setString(1, groupName);
            pstmt2.setString(2, bookTitle);
            ResultSet rs = pstmt2.executeQuery();
//            if (rs.next()) {
//                System.out.println("The group and book already exists");
//                return;
//            }
           
            String displayFormat="%-20s%-25s%-30s%-20s%-20s\n";
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
            
            //Removing a publisher
            //Check if the publisher exists within the database
            String checkStatement = "SELECT publisher_name FROM Publishers WHERE publisher_name = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(checkStatement);
            pstmt1.setString(1, publisherReplace);
            ResultSet rs1 = pstmt1.executeQuery();
            if (rs1.next()) {
                System.out.println("Removing publisher from the query");
                String removeStatement = "DELETE FROM Publishers WHERE publisher_name = ?";
                PreparedStatement removepstmt = conn.prepareStatement(removeStatement);
                removepstmt.setString(1, publisherReplace);
                removepstmt.executeUpdate();
            }
            else {
                System.out.println("The publisher you are trying to remove does not exist. \nReturning to main menu");
                return;
            }
            
            
            String stmt3 = "SELECT publisher_name, publisher_address, publisher_phone, publisher_email FROM Publishers WHERE publisher_name = ? ORDER BY publisher_name";
            PreparedStatement pstmt3 = conn.prepareStatement(stmt3);
            pstmt3.setString(1, publisherName);
            ResultSet rs = pstmt3.executeQuery();
           
            String displayFormat="%-30s%-50s%-20s%-20s\n";
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
            String checkStatement = "Select group_name, book_title FROM Books WHERE group_name = ? AND book_title = ?";
            PreparedStatement psmt0 = conn.prepareStatement(checkStatement);
            psmt0.setString(1, groupName);
            psmt0.setString(2, bookTitle);
            ResultSet rs = psmt0.executeQuery();
            if (!rs.next()) {
                System.out.println("This book does not exist! Returning to main menu");
                return;
            }
            
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
    public static void menu() {
        System.out.println("What would you like to do? (Enter the number for each option)");
        System.out.println("1. List all writing groups");
        System.out.println("2. List data for a specific writing group");
        System.out.println("3. List all publishers");
        System.out.println("4. List a specific publisher");
        System.out.println("5. List all books");
        System.out.println("6. List a specific book");
        System.out.println("7. Add a book");
        System.out.println("8. Add a publisher");
        System.out.println("9. Remove a book");
        System.out.println("10. Quit");
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
        Scanner scanner = new Scanner(System.in);
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            int userChoice = 0;
            String userInput = "";
            while (userChoice != 10) {
                menu();
                userChoice = in.nextInt();
                
                switch (userChoice) {
                    case 1: //doesn't need a check
                        getAllWritingGroups();
                        break;
                    case 2: //doesn't need a check
                        System.out.println("Which writing group do you want to find?");
                        userInput = scanner.nextLine();
                        getWritingGroup(userInput);
                        break;
                    case 3: //doesn't need a check
                        getAllPublishers();
                        break;
                    case 4: //doesn't need a check
                        System.out.println("Which publisher do you want to find?");
                        userInput = scanner.nextLine();
                        getPublisher(userInput);
                        break;
                    case 5: //doesn't need a check
                        getAllBooks();
                        break;
                    case 6:
                        System.out.println("Which book do you want to find?");
                        userInput = scanner.nextLine();
                        System.out.println("Which group does this book belong to?");
                        String group = scanner.nextLine();
                        getBook(group, userInput);
                        break;
                    case 7:
                        {
                        //String groupName, String bookTitle, String publisherName, int yearPublished, int numPages
                        System.out.println("What is the book title?");
                        in.nextLine();
                        userInput = in.nextLine();
                        while (userInput.isEmpty()) {
                            System.out.println("Please enter a book title");
                            userInput = in.nextLine();
                        }
                        
                        
                        System.out.println("What is the group name?");
                        String groupName = in.nextLine();
                        
                        while (groupName.isEmpty()) {
                            System.out.println("Please enter a group name");
                            groupName = in.nextLine();
                        }
                        
                        System.out.println("What is the publisher name?");
                        String publisherName = in.nextLine();
                        while (publisherName.isEmpty()) {
                            System.out.println("Please enter a publisher name");
                            publisherName = in.nextLine();
                        }
                        
                        System.out.println("What is the year published?");
                        String yearPublished = in.nextLine();
                        while (yearPublished.isEmpty() || !yearPublished.matches("[0-9]+")) {
                            System.out.println("Please enter a year that is valid");
                            yearPublished = in.nextLine();
                        }
                        int newYear = Integer.parseInt(yearPublished);
                        
                        System.out.println("How many pages does the book have?");
                        String pages = in.nextLine();
                        while (pages.isEmpty() || !yearPublished.matches("[0-9]+")) {
                            System.out.println("Please enter a valid amount of pages");
                            pages = in.nextLine();
                        }
                        int newPages = Integer.parseInt(pages);
                        addBook(groupName, userInput, publisherName, newYear, newPages);
                        break;
                        }
                    case 8:
                        {
                        //String publisherName, String publisherAddress, String publisherPhone, String publisherEmail, String publisherReplace
                        System.out.println("What is the publisher name?");
                        in.nextLine();
                        
                        String publisherName = in.nextLine();
                        
                        System.out.println("What is the publisher address?");
                        String publisherAddress = in.nextLine();
                       
                        System.out.println("What is the publisher's phone?");
                        String publisherPhone = in.nextLine();
                        
                        System.out.println("What is the publisher's email?");
                        String publisherEmail = in.nextLine();
                        
                        System.out.println("What publisher do you want to replace?");
                        String publisherReplace = in.nextLine();
                        addPublisher(publisherName, publisherAddress, publisherPhone, publisherEmail, publisherReplace);
                        break;
                        }
                    case 9:
                        {
                        System.out.println("What is the name of the book?");
                        in.nextLine();
                        String bookTitle = in.nextLine();
                        while (bookTitle.isEmpty()) {
                            System.out.println("Please enter a book title");
                            bookTitle = in.nextLine();
                        }
                        System.out.println("What is the name of the group?");
                        
                        String groupName = in.nextLine();
                        while (groupName.isEmpty()) {
                            System.out.println("Please enter a group name");
                            groupName = in.nextLine();
                        }
                        removeBook(groupName, bookTitle);
                        break;
                        }
                    
                }
            }
            //addBook("Write On", "bt", "HarperCollins", 1999, 420);
//            addPublisher("Long Beach Publisher", "123 Seasame St. Long Beach, CA 90804", "562-210-2345", "lbpublish@gmail.com", "Hachette Livre");
//            removeBook("Science Lovers", "Hypothesis");
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
           
        
        }
        
        finally {
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

