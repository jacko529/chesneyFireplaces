
/**
 * Write a description of class AddSupplierPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.event.MouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.sql.*;   
import javax.swing.border.*;    // Border, BorderFactory Classes
import java.awt.event.*;
public class AddSupplierPanel extends JPanel implements ActionListener
{
    private JLabel supplierIdLabel, supplierNameLabel, propertyNumberLabel, firstLineLabel, townLabel, postCodeLabel, 
    emailAddressLabel, phoneNumberLabel, pointOfContactLabel, mainTitle;
    private JTextField supplierId, supplierName, propertyNumber, firstLine, Town, postCode, emailAddress,
    phoneNumber, pointOfContact;
    private JButton submitButton;                         
    private JPanel dataEntryPanel;           
    private JSeparator sep ;  
    private Font title = new Font("Courier", Font.BOLD,32);
    private Font text = new Font("Courier", Font.BOLD,16);
    private JFrame frame = new JFrame();

    private static AddSupplierPanel instance = null;
    public AddSupplierPanel() {
        this.setLayout(null);

        createPanels();

        setUpForm();
        submitButton.addActionListener (this);

        finaliseScreen();       

    }
    private void createPanels()
    {
        // Create Panels 

        dataEntryPanel = new JPanel();

    } // End createPanels()
    /**
     *  Method to set up the data-entry form itself
     */
    private void setUpForm()
    {

        // label 
        mainTitle = new JLabel(
            "Add A Supplier"); 
        mainTitle.setFont(title);                                      
        dataEntryPanel.add(mainTitle);
        // label 
        supplierIdLabel = new JLabel(
            "Enter Supplier Id");    
        supplierIdLabel.setFont(text);                                      
        dataEntryPanel.add(supplierIdLabel);
        supplierId = new JTextField(15);


        dataEntryPanel.add(supplierId);
        GetLastSupplierID();
        //supplier name
        supplierNameLabel = new JLabel(
            "Enter Supplier Name");  
        supplierNameLabel.setFont(text);                                                  
        supplierName = new JTextField(15);
        dataEntryPanel.add(supplierNameLabel);     
        dataEntryPanel.add(supplierName);

        //first line name
        propertyNumberLabel = new JLabel(
            "Enter Property Number");  
        propertyNumberLabel.setFont(text);                                                              
        propertyNumber = new JTextField(15);
        dataEntryPanel.add(propertyNumberLabel);      
        dataEntryPanel.add(propertyNumber);      

        firstLineLabel = new JLabel(
            "Enter First Line Address");  
        firstLineLabel.setFont(text);                                                                          
        firstLine = new JTextField(15);
        dataEntryPanel.add(firstLineLabel);
        dataEntryPanel.add(firstLine);

        townLabel = new JLabel(
            "Enter Town"); 
        townLabel.setFont(text);                                                                                      
        Town = new JTextField(15);
        dataEntryPanel.add(townLabel);      
        dataEntryPanel.add(Town);      

        postCodeLabel = new JLabel(
            "Enter Post Code"); 
        postCodeLabel.setFont(text);                                                                                                  
        postCode = new JTextField(15);
        dataEntryPanel.add(postCodeLabel);      
        dataEntryPanel.add(postCode);      

        emailAddressLabel = new JLabel(
            "Enter Email Address");
        emailAddressLabel.setFont(text);                                                                                                              
        emailAddress = new JTextField(15); 
        dataEntryPanel.add(emailAddressLabel);      
        dataEntryPanel.add(emailAddress);      

        phoneNumberLabel = new JLabel(
            "Enter Phone Number"); 
        phoneNumberLabel.setFont(text);                                                                                                                          
        phoneNumber = new JTextField(15);  
        dataEntryPanel.add(phoneNumberLabel);     
        dataEntryPanel.add(phoneNumber);     

        pointOfContactLabel = new JLabel(
            "Enter Point Of Contact"); 
        pointOfContactLabel.setFont(text);                                                                                                                                      
        pointOfContact = new JTextField(15);      
        dataEntryPanel.add(pointOfContactLabel);      
        dataEntryPanel.add(pointOfContact);      

        submitButton = new JButton("Submit");
        dataEntryPanel.add(submitButton);      
        submitButton.setPreferredSize(new Dimension(140, 80));

        SpringLayout layout = new SpringLayout();
        // Specify that we want to use Absolute Positioning
        // on all the panels
        dataEntryPanel.setBackground(new Color(142, 164, 237));
        dataEntryPanel.setLayout (layout);

        layout.putConstraint(SpringLayout.WEST, mainTitle,500, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, mainTitle,40, SpringLayout.NORTH, dataEntryPanel);

        layout.putConstraint(SpringLayout.WEST, supplierIdLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, supplierIdLabel,30, SpringLayout.NORTH, dataEntryPanel);
        layout.putConstraint(SpringLayout.WEST, supplierId,75, SpringLayout.EAST, supplierIdLabel);
        layout.putConstraint(SpringLayout.NORTH, supplierId,30, SpringLayout.NORTH, 
            dataEntryPanel);
        // supplier name  

        layout.putConstraint(SpringLayout.WEST, supplierNameLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, supplierNameLabel,50, SpringLayout.NORTH, supplierIdLabel);
        layout.putConstraint(SpringLayout.EAST, supplierName,370, SpringLayout.WEST, supplierNameLabel);
        layout.putConstraint(SpringLayout.NORTH, supplierName,50, SpringLayout.NORTH, 
            supplierId);


        // property number name  
        layout.putConstraint(SpringLayout.WEST, propertyNumberLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, propertyNumberLabel,50, SpringLayout.NORTH, supplierNameLabel);
        layout.putConstraint(SpringLayout.EAST, propertyNumber,370, SpringLayout.WEST, propertyNumberLabel);
        layout.putConstraint(SpringLayout.NORTH, propertyNumber,50, SpringLayout.NORTH, 
            supplierName);

        // first line number name  
        layout.putConstraint(SpringLayout.WEST, firstLineLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, firstLineLabel,50, SpringLayout.NORTH, propertyNumberLabel);
        layout.putConstraint(SpringLayout.EAST, firstLine,370, SpringLayout.WEST, firstLineLabel);
        layout.putConstraint(SpringLayout.NORTH, firstLine,50, SpringLayout.NORTH, 
            propertyNumber);         

        // town  name   
        layout.putConstraint(SpringLayout.WEST, townLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, townLabel,50, SpringLayout.NORTH, firstLineLabel);
        layout.putConstraint(SpringLayout.EAST, Town,370, SpringLayout.WEST, townLabel);
        layout.putConstraint(SpringLayout.NORTH, Town,50, SpringLayout.NORTH, 
            firstLine);      

        // post code  name   
        layout.putConstraint(SpringLayout.WEST, postCodeLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, postCodeLabel,50, SpringLayout.NORTH, townLabel);
        layout.putConstraint(SpringLayout.EAST, postCode,370, SpringLayout.WEST, postCodeLabel);
        layout.putConstraint(SpringLayout.NORTH, postCode,50, SpringLayout.NORTH, 
            Town);   

        // phone email  name   
        layout.putConstraint(SpringLayout.WEST, emailAddressLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, emailAddressLabel,50, SpringLayout.NORTH, postCodeLabel);
        layout.putConstraint(SpringLayout.EAST, emailAddress,370, SpringLayout.WEST, emailAddressLabel);
        layout.putConstraint(SpringLayout.NORTH, emailAddress,50, SpringLayout.NORTH, 
            postCode); 

        // phone number  name   
        layout.putConstraint(SpringLayout.WEST, phoneNumberLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel,50, SpringLayout.NORTH, emailAddressLabel);
        layout.putConstraint(SpringLayout.EAST, phoneNumber,370, SpringLayout.WEST, emailAddressLabel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumber,50, SpringLayout.NORTH, 
            emailAddress);         

        // poc  number  name   
        layout.putConstraint(SpringLayout.WEST, pointOfContactLabel,20, SpringLayout.WEST, dataEntryPanel);
        layout.putConstraint(SpringLayout.NORTH, pointOfContactLabel,50, SpringLayout.NORTH, phoneNumberLabel);
        layout.putConstraint(SpringLayout.EAST, pointOfContact,370, SpringLayout.WEST, pointOfContactLabel);
        layout.putConstraint(SpringLayout.NORTH, pointOfContact,50, SpringLayout.NORTH, 
            phoneNumber);    

        //button   
        layout.putConstraint(SpringLayout.WEST, submitButton,330, SpringLayout.WEST, pointOfContact);
        layout.putConstraint(SpringLayout.NORTH, submitButton,360, SpringLayout.NORTH, dataEntryPanel);


    } // End setUpForm()
    /**
     * Implement the actionPerformed() method
     * in the ActionListener Interface Class
     * 
     * @param event ... An ActionEvent
     */ 
    public void actionPerformed(ActionEvent event)
    {
        // Check to see it the submit button was pressed
        if (event.getSource() == submitButton)
        {
            // Check to see if there is a problem loading driver
            if( DatabaseHandler.loadDriver() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Unable to load driver...");
            }
            else if(propertyNumber.getText() == "" || supplierName.getText() == "" )  // Ok to write record to database table
            {
                JOptionPane.showMessageDialog (frame, "Unable to load driver...");

            }
            else{
                writeSupplierRecord();
                GetLastSupplierID();// Call method which is coded below.
            }

        }

    } // End actionPerformed()
    private void GetLastSupplierID()
    {
        ResultSet rs;
        if ( DatabaseHandler.loadDriver() == -1 )
        {
            JOptionPane.showMessageDialog (frame, "Problem loading the JDBC/ODBC driver.");
        }
        else 
        // Check to see if we can connect to the database table
        if ( DatabaseHandler.makeConnectionToDB() == -1 )
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }
        else  // Search for all the books
        {
            rs = DatabaseHandler.getTheLatestSupplierId();
            try
            {
                Suppliers Suppliers = new Suppliers();                      
                while(rs.next()){
                    Suppliers.setSupplierId(rs.getInt("supplier_id")); //IDTable

                }
                int SupplierIdPlusOne = Suppliers.getSupplierId() + 1;
                supplierId.setText(Integer.toString(SupplierIdPlusOne));
            }
            catch (SQLException e)
            {
                // Report an error message is there is a problem with the result set
                JOptionPane.showMessageDialog (frame, "Problem displaying book data");
            }
        }
    }

    /**
     *  Method to write a book record
     */
    private void writeSupplierRecord()
    {
        // Check to see if we can connect to database table
        if ( DatabaseHandler.makeConnectionToDB() == -1)
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }
        else  // Ok, so first read data from the text fields
        {

            // Read data from form and store data                      
            String supplierIds = supplierId.getText();
            String supplierNames = supplierName.getText();
            String propertyNumbers = propertyNumber.getText();
            String firstLines = firstLine.getText();
            String Towns = Town.getText();
            String postCodes = postCode.getText();
            String Emails = emailAddress.getText();
            String phoneNumbers = phoneNumber.getText();                       
            String pointOfContacts = pointOfContact.getText();

            // Convert priceStr to a float
            int supplierInt = Integer.valueOf( supplierIds );

            // Create a Book oject
            Suppliers Suppliers = new Suppliers();                      

            // Set the attributes for the Book object  
            Suppliers.setSupplierId(supplierInt);
            Suppliers.setName (supplierNames);
            Suppliers.setPropertyNumber (propertyNumbers);
            Suppliers.setFirstLine (firstLines);
            Suppliers.setTown (Towns);
            Suppliers.setPostCode (postCodes);
            Suppliers.setEmail (Emails);
            Suppliers.setPhoneNumber (phoneNumbers);
            Suppliers.setPointOfContact (pointOfContacts);
            // Write book record.  Method writeToBookTable() returns
            // 0 of OK writing record, -1 if there is a problem.  I store
            // the returned value in a variable called error.
            int error = DatabaseHandler.writeSupplierToTable(Suppliers.getSupplierId(),
                    Suppliers.getName(),
                    Suppliers.getPropertyNumber(),
                    Suppliers.getFirstLine(),
                    Suppliers.getTown(),
                    Suppliers.getPostCode(),
                    Suppliers.getEmail(),
                    Suppliers.getPhoneNumber(),
                    Suppliers.getPointOfContact());

            // Check if there is a problem writing the record, in 
            // which case error will contain -1                                         
            if (error == -1)
            {
                JOptionPane.showMessageDialog (frame, "Problem writing record to Supplier Table");
            }

            // Clear the form - actual method is coded below
            clearForm();

            // Close database connection.  Report an error message
            // if there is a problem.
            if ( DatabaseHandler.closeConnection() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Problem closing data base conection");
            }

        }
    }  // End writeBookRecord()
    private void finaliseScreen()
    {

        dataEntryPanel.setBounds (0,0,900,580);
        // And add to mainPanel
        this.add (dataEntryPanel);

    } // End finaliseScreen()

    /**
     * Method to clear the text fields on the form
     */
    private void clearForm()
    {
        // Simply put an empty string into each textfield    
        supplierId.setText(null);
        supplierName.setText(null);
        propertyNumber.setText(null);                   
        firstLine.setText(null);
        Town.setText(null);
        postCode.setText(null);
        emailAddress.setText(null);                   
        phoneNumber.setText(null);  
        pointOfContact.setText(null);                    

    } // End clearForm()
    public static AddSupplierPanel getInstance() {
        if (instance == null) {
            instance = new AddSupplierPanel();
        }

        return instance;
    }

}
