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
         
           String displayFormat="%-15s%-15s%-15s%-15s\n";
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
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void getAllPublishers(){
        
    }
    
    public static void getPublisher(String publisher){
        
    }
    
    public static void getAllBooks(){
        
    }
    
    public static void getBook(String book){
        
    }
    
    public static void addBook(String bookTitle, int yearPublished, int numPages, String groupName, String publisherName){
        
    }
    
    public static void addPublisher(String publisherName, String publisherAddress, String publisherPhone, String publisherEmail){
        
    }
    
    public static void removeBook(String bookTitle, String groupName){
        
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

            getWritingGroup("Write On");
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

