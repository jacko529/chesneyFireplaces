
/**
 * Write a description of class DatabaseHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.sql.*;

public class DatabaseHandler
{

    // Attributes
    /**
     * Reference to a connection object.
     */
    static Connection connectionToDB;
    static PreparedStatement statement;
    static Statement statements;    
    static String url = "jdbc:mysql://localhost:3306/" ;
    static String dbName = "programmingassignment";
    static String userName = "root"; 
    static String password = "";
    /**
     *  Loads the MYSQL Driver.
     *  
     *  @return 0 if the driver loads successfully, otherwise
     *            -1 returned if there is a problem loading
     *            the driver.
     *  
     */
    static public int loadDriver()
    {

        // Attempt to load the UCANACCESS Driver
        try
        {

            Class.forName("com.mysql.cj.jdbc.Driver") ;

        }
        catch (ClassNotFoundException  e)
        {
            return -1;
        }

        return 0;

    } // end loadDriver()

    static public int makeConnectionToDB()
    {

        try
        {
            // Make connection to Database
            connectionToDB = DriverManager.getConnection(
                url+dbName,userName,password);  

        }
        catch (SQLException exception)
        {  
            return (-1);    // Return back with -1 if there is a problem 
            // making a connection
        }

        return (0);   // Return back with 0 if connection is made to database

    } // end makeConnectionToBooksDB()

    static public int closeConnection()
    {
        try
        {
            connectionToDB.close();   // You should not keep a connection to a database
            // open for any longer than is absolutely necessary. 
        }
        catch (SQLException exception)
        {
            return  (-1);               // Return back with -1 if there is a problem closing
            // the connection.
        }

        return (0); // Return back with 0 if connection closed successfully.

    } // End closeConnection()
    /**
     * 
     * 
     */ 
    static public int writeSupplierToTable(int SupplierId, String Name, String PropertyNumber, String FirstLine, String Town, 
    String PostCode, String Email, String PhoneNumber, String PointOfContact)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "insert into supplier (supplier_id, supplier_name, property_number, first_line_addrress, city, post_code, email_address, phone_number, point_of_contact)"
        + " values (?, ?, ?, ?, ?, ? ,? , ? ,?)";                                               
        try
        {

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, SupplierId);
            statement.setString (2, Name);
            statement.setString (3, PropertyNumber);
            statement.setString (4, FirstLine);
            statement.setString (5, Town);
            statement.setString (6, PostCode);
            statement.setString (7, Email);
            statement.setString (8, PhoneNumber);
            statement.setString (9, PointOfContact);
            // execute the preparedstatement
            statement.execute();

            closeConnection();

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

        return (0);   // Return with 0 if record successfully written 

    }
    
    /**
     * 
     * 
     */ 
    static public ResultSet searchItem(int itemId)
    {
         ResultSet rs=null;
        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "select item_id, description, item.supplier_id, supplier.supplier_name from item inner join supplier on item.supplier_id = supplier.supplier_id where item_id = ?;";                                               
        try
        {
       // connectionToDB = DriverManager.getConnection(
            //    url+dbName,userName,password);  

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, itemId);

            // execute the preparedstatement
            rs= statement.executeQuery();


        }
        catch (SQLException exception)
        {
          return rs;  // will actually return back null if there is a problem
          //   System.out.println(exception);
        }  
        return rs; 
      //  System.out.println(statement);       
    }
    
    /**
     * 
     * 
     */ 
    static public ResultSet searchSupplier(String supplierName)
    {
         ResultSet rs=null;
        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "select * from supplier where supplier_name = ?;";                                               
        try
        {
       // connectionToDB = DriverManager.getConnection(
            //    url+dbName,userName,password);  

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setString (1, supplierName);

            // execute the preparedstatement
            rs= statement.executeQuery();


        }
        catch (SQLException exception)
        {
          return rs;  // will actually return back null if there is a problem
          //   System.out.println(exception);
        }  
        return rs; 
      //  System.out.println(statement);       
    }
    
      /**
     * 
     * 
     */ 
    static public ResultSet searchItemComplete (int itemId)
    {
         ResultSet rs=null;
        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "select * from item where item_id = ?;";                                               
        try
        {
       // connectionToDB = DriverManager.getConnection(
            //    url+dbName,userName,password);  

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, itemId);

            // execute the preparedstatement
            rs= statement.executeQuery();


        }
        catch (SQLException exception)
        {
          return rs;  // will actually return back null if there is a problem
          //   System.out.println(exception);
        }  
        return rs; 
      //  System.out.println(statement);       
    }
      /**
     * 
     * 
     */ 
    static public int updateInventory(int itemId, int itemQuantity)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "update item set quantityInStock = ? where item_id = ?";                                               
        try
        {

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, itemQuantity);
            statement.setInt (2, itemId);

            // execute the preparedstatement
            statement.executeUpdate();

            closeConnection();

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

        return (0);   // Return with 0 if record successfully written 

    }
          /**
     * 
     * 
     */ 
    static public int deleteSupplier(int supplierID)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "Delete from supplier where supplier_id = ?";                                               
        try
        {

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
 

            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, supplierID);
            
            // execute the preparedstatement
            statement.executeUpdate();

           

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

        return(0);   // Return with 0 if record successfully written 

    }
          /**
     * 
     * 
     */ 
    static public int deleteItem(int itemId)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "Delete from item where item_id = ?";                                               
        try
        {

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
         // Make connection to Database
        

            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, itemId);


            // execute the preparedstatement
            statement.executeUpdate();

           

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

          return (0);     // Return -1 if problem writing record to file

    }
          /**
     * 
     * 
     */ 
    static public int updateItemSupplier(int supplierID, int itemId)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "update item set supplier_id = ? where item_id = ?";                                               
        try
        {

            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
 

            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, supplierID);
            statement.setInt (2, itemId);

            // execute the preparedstatement
            statement.executeUpdate();

           

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

        return(0);   // Return with 0 if record successfully written 

    }
 /**
     * 
     * 
     */ 
    static public int writeFirePlaceToTable(int itemId, String Description, String style, String finish, 
    double UnitPrice, int QuantityInStock, int SupplierId)
    {

        // Variable to hold the SQL query
        String SQLString;

        // Build a string containing the SQL INSERT instruction to be used later                                                       
        SQLString = "insert into item (item_id, description, style, finish, unit_price, quantityInStock, supplier_id)"
        + " values (?, ?, ?, ?, ?, ? ,?  )";                                               
        try
        {
   // Make connection to Database
 
            // create the mysql insert preparedstatement
            // Connection myConnection2 = connectionToDB.getConnection();
            // statement = connectionToBooksDB.createprepareStatement();
            statement = connectionToDB.prepareStatement(SQLString);
            statement.setInt (1, itemId);
            statement.setString (2, Description);
            statement.setString (3, style);
            statement.setString (4, finish);
            statement.setDouble (5, UnitPrice);
            statement.setInt (6, QuantityInStock);
            statement.setInt (7, SupplierId);

            // execute the preparedstatement
            statement.execute();

            closeConnection();

        }
        catch (SQLException exception)
        {
            return (-1);     // Return -1 if problem writing record to file

        }

        return (0);   // Return with 0 if record successfully written 

    }
    static public ResultSet getTheLatestSupplierId()
    {
        // Create a reference to a ResultSet object
        ResultSet rs=null;
        // String to hold an SQL command
        String SQLString = null;

        // SQL string to search all records in the BOOK Table
        SQLString = "SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1";

        try
        {
            statement = connectionToDB.prepareStatement(SQLString);
            rs = statement.executeQuery();
        }
        catch (SQLException exception)
        {
            return rs;  // will actually return back null if there is a problem
        }  
        return rs;       
    }
   static public ResultSet getAllSuppliers()
    {
        // Create a reference to a ResultSet object
        ResultSet rs=null;
        // String to hold an SQL command
        String SQLString = null;

        // SQL string to search all records in the BOOK Table
        SQLString = "SELECT * FROM supplier;";

        try
        {
            statement = connectionToDB.prepareStatement(SQLString);
            rs = statement.executeQuery();
        }
        catch (SQLException exception)
        {
            return rs;  // will actually return back null if there is a problem
        }  
        return rs;       
    }    
    
       static public ResultSet getAllFirePlaces()
    {
        // Create a reference to a ResultSet object
        ResultSet rs=null;
        // String to hold an SQL command
        String SQLString = null;    

        // SQL string to search all records in the BOOK Table
        SQLString = "SELECT * FROM item;";

        try
        {
               
            statement = connectionToDB.prepareStatement(SQLString);
            rs = statement.executeQuery();
        }
        catch (SQLException exception)
        {
            return rs;  // will actually return back null if there is a problem
        }  
        return rs;       
    }
    
    
        static public ResultSet getAllFirePlacesForUpdate()
    {
        // Create a reference to a ResultSet object
        ResultSet rs=null;
        // String to hold an SQL command
        String SQLString = null;

        // SQL string to search all records in the BOOK Table
        SQLString = "SELECT item_id, description, unit_price, quantityInStock FROM item";

        try
        {
            statement = connectionToDB.prepareStatement(SQLString);
            rs = statement.executeQuery();
        }
        catch (SQLException exception)
        {
            return rs;  // will actually return back null if there is a problem
        }  
        return rs;       
    }

     static public ResultSet getAllSupplier()
    {
           
        // Create a reference to a ResultSet object
        ResultSet rs=null;
        // String to hold an SQL command
        String SQLString = null;

        // SQL string to search all records in the BOOK Table
        SQLString = "SELECT * FROM supplier ORDER BY supplier_id";

        try
        {
          
            statement = connectionToDB.prepareStatement(SQLString);
            rs = statement.executeQuery();
        }
        catch (SQLException exception)
        {
            return rs;  // will actually return back null if there is a problem
        }  
        return rs;       
    }
}
