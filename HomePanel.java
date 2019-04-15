
/**
 * Write a description of class AddItemPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;              // ResultSet Class
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class HomePanel extends JPanel implements ActionListener
{
    private JLabel TotalAmountOfItems, ItemDetails, SupplierDetails,
    titleLabel, SupplierInformation, FirePlaceInformation, informationSeeDisplay;
    private JScrollPane scrollSupplier, scrollItems;
    private JTextArea supplierInformation, itemInformation;
    private JPanel gerneralInfoPanel;
    private JFrame frame = new JFrame();  
    private JTextField searchBox;
    private JButton submitSearch, deleteButton, refreshButton;
    private Font title = new Font("Courier", Font.BOLD,25);
    private Font text = new Font("Courier", Font.BOLD,16);
    private String ItemOrSupplierString;
    private FirePlaces Fireplaces = new FirePlaces(); 
    private Suppliers Suppliers = new Suppliers();                      
    private ResultSet supplierResultSet, itemResultSet;
    private JTable supplierResultTable, fireplaceResultTable;
    private static HomePanel instance = null;
    private int recordCount,findOutIfInputIsANumber;;
    private boolean  recordsFound, ifTrueItem, ifTrueSupplier;
    private SpringLayout layout = new SpringLayout();
    String[] supplierInfo = {"Supplier Id", "Name", "Property Number", "First Line", "City",
            "Post Code", "Email Address", "Phone Number","Point of contact"};
    String[] itemInfo = {"Item Id", "Description", "Style", "Finish", "Unit Price",
            "Quantity In Stock", "Supplier ID"};
    public HomePanel()
    {
        this.setLayout(null);
        createPanels();
        setUpForm();
        submitSearch.addActionListener (this);
        deleteButton.addActionListener (this);
        refreshButton.addActionListener (this);        
        finaliseScreen();        
    }

    private void setUpForm()
    {
        createPanels();

        gerneralInfoPanel.setLayout(layout);
        searchBox = new JTextField(15);
        gerneralInfoPanel.add(searchBox);

        titleLabel = new JLabel("Chesney Fireplace Home Screen");
        titleLabel.setFont(title);
        gerneralInfoPanel.add(titleLabel);    
        submitSearch = new JButton("Submit"); 
        submitSearch.setPreferredSize(new Dimension(150, 30));
        gerneralInfoPanel.add(submitSearch);    

        deleteButton = new JButton("Delete"); 
        deleteButton.setPreferredSize(new Dimension(150, 30));       
        gerneralInfoPanel.add(deleteButton);
        
        refreshButton = new JButton("Refresh"); 
        refreshButton.setPreferredSize(new Dimension(150, 30));       
        gerneralInfoPanel.add(refreshButton);

        gerneralInfoPanel.setBackground(new Color(142, 164, 237));
        ItemDetails = new JLabel(
            "Item Details");    
        ItemDetails.setFont(text);
        gerneralInfoPanel.add(ItemDetails);

        SupplierDetails = new JLabel(
            "Supplier Details");   
        SupplierDetails.setFont(text);
        gerneralInfoPanel.add(SupplierDetails);

        FirePlaceInformation = new JLabel(
            "Fireplace Information Display");   
        FirePlaceInformation.setFont(text);
        gerneralInfoPanel.add(FirePlaceInformation);

        SupplierInformation = new JLabel(
            "Supplier Information Display");   
        SupplierInformation.setFont(text);
        gerneralInfoPanel.add(SupplierInformation);

        informationSeeDisplay = new JLabel(
            "Enter the Supplier Name or Item ID");   
        informationSeeDisplay.setFont(text);
        gerneralInfoPanel.add(informationSeeDisplay);

        supplierInformation = new JTextArea(7, 20);
        itemInformation = new JTextArea(7, 20);
        gerneralInfoPanel.add(supplierInformation);
        gerneralInfoPanel.add(itemInformation);
        PaintSrollPane();
        layout.putConstraint(SpringLayout.WEST, titleLabel,220, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel,0, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, SupplierDetails,350, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, SupplierDetails,200, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, ItemDetails,370, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, ItemDetails,50, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, searchBox,310, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, searchBox,400, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, submitSearch,320, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, submitSearch,440, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, deleteButton,320, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, deleteButton,480, SpringLayout.NORTH, gerneralInfoPanel);
        
        layout.putConstraint(SpringLayout.WEST, refreshButton,320, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, refreshButton,520, SpringLayout.NORTH, gerneralInfoPanel);
        
        layout.putConstraint(SpringLayout.WEST, informationSeeDisplay,270, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, informationSeeDisplay,360, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, SupplierInformation,20, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, SupplierInformation,360, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, FirePlaceInformation,550, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, FirePlaceInformation,360, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, supplierInformation,20, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, supplierInformation,400, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, itemInformation,550, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, itemInformation,400 , SpringLayout.NORTH, gerneralInfoPanel);

    }

    private JTable GetAllItems()
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
            rs = DatabaseHandler.getAllFirePlaces();

            try
            {

                ResultSetMetaData rsmt = rs.getMetaData();
                int numberOfRows = rsmt.getColumnCount();
                Vector column = new Vector(numberOfRows);
                column.addAll(Arrays.asList(itemInfo));
                Vector data = new Vector();
                Vector row = new Vector();                                
                while(rs.next()){
                    row = new Vector(numberOfRows);   
                    for(int i = 1;i<=numberOfRows;i++){
                        row.add(rs.getString(i));
                    }
                    data.add(row);

                }
                fireplaceResultTable = new JTable(data, column);

                fireplaceResultTable.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt){
                            clearForm();

                            int index = fireplaceResultTable.getSelectedRow();
                            TableModel model = fireplaceResultTable.getModel();

                            Fireplaces.setItemCode(Integer.parseInt(model.getValueAt(index, 0).toString()));                            
                            Fireplaces.setDescription(model.getValueAt(index, 1).toString());
                            Fireplaces.setStyle(model.getValueAt(index, 2).toString());  
                            Fireplaces.setFinish(model.getValueAt(index, 3).toString());                            
                            Fireplaces.setUnitPrice(Double.parseDouble(model.getValueAt(index, 4).toString()));                            
                            Fireplaces.setQuantityInStock(Integer.parseInt(model.getValueAt(index, 5).toString()));                            

                            itemInformation.append("Item ID: " + Fireplaces.getItemCode() + "\n" + 
                                "Description: " + Fireplaces.getDescription() + "\n"+
                                "Style: " + Fireplaces.getStyle() + "\n"+
                                "Finish: " + Fireplaces.getFinish() + "\n"+
                                "Unit Price: " + Fireplaces.getUnitPrice() + "\n"+
                                "Quantity In Stock: " + Fireplaces.getQuantityInStock() + "\n"
                            );

                        }
                    });

            }
            catch (SQLException e)
            {
                // Report an error message is there is a problem with the result set
                JOptionPane.showMessageDialog (frame, "Problem displaying book data");
            }
        }
        return  fireplaceResultTable;  
    }

    private JTable GetAllSuppliers()
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
            rs = DatabaseHandler.getAllSuppliers();

            try
            {

                ResultSetMetaData rsmt = rs.getMetaData();
                int numberOfRows = rsmt.getColumnCount();
                Vector column = new Vector(numberOfRows);
                column.addAll(Arrays.asList(supplierInfo));
                Vector data = new Vector();
                Vector row = new Vector();                                
                while(rs.next()){
                    row = new Vector(numberOfRows);   
                    for(int i = 1;i<=numberOfRows;i++){
                        row.add(rs.getString(i));
                    }
                    data.add(row);

                }
                supplierResultTable = new JTable(data, column);
                supplierResultTable.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt){
                            clearForm();
                            int index = supplierResultTable.getSelectedRow();
                            TableModel model = supplierResultTable.getModel();

                            Suppliers.setSupplierId(Integer.parseInt(model.getValueAt(index, 0).toString()));
                            Suppliers.setName(model.getValueAt(index, 1).toString());  
                            Suppliers.setPropertyNumber(model.getValueAt(index, 2).toString());  
                            Suppliers.setFirstLine(model.getValueAt(index, 3).toString());                           
                            Suppliers.setTown(model.getValueAt(index, 4).toString());                            
                            Suppliers.setPostCode(model.getValueAt(index, 5).toString());                            
                            Suppliers.setEmail( model.getValueAt(index, 6).toString());
                            Suppliers.setPhoneNumber(model.getValueAt(index, 7).toString());                            
                            Suppliers.setPointOfContact(model.getValueAt(index, 8).toString());                            

                            supplierInformation.append(
                                "Supplier Id: " + Suppliers.getSupplierId() + "\n" + 
                                "Name: " + Suppliers.getName() + "\n"+
                                "Property Number: " + Suppliers.getPropertyNumber() + "\n"+
                                "First Line: " + Suppliers.getFirstLine() + "\n"+
                                "City: " + Suppliers.getTown() + "\n"+
                                "Post Code: " + Suppliers.getPostCode() + "\n"+
                                "Email Address: " + Suppliers.getEmail() + "\n"+
                                "Phone Number: " + Suppliers.getPhoneNumber() + "\n"+
                                "Point Of Contact: " + Suppliers.getPointOfContact() + "\n"
                            );

                        }
                    });

            }
            catch (SQLException e)
            {
                // Report an error message is there is a problem with the result set
                JOptionPane.showMessageDialog (frame, "Problem displaying book data");
            }
        }
        return  supplierResultTable;  
    }

    /**
     * Count the length of the supplierInfo array for the colum count of the 
     * supplier info table
     */
    public int getColumnCount(Array arrayIn) { 
        return supplierInfo.length; 
    } 

    /**
     * Implement the actionPerformed() method
     * in the ActionListener Interface Class
     * 
     * @param event ... An ActionEvent
     */ 
    public void actionPerformed(ActionEvent event)
    {

        // Check to see it the submit button was pressed
        if (event.getSource() == submitSearch)
        {

            readItemOrSupplierID();
            clearForm();

            // Display an error message if there is no data in
            // title text field
            if (ItemOrSupplierString.equals(""))  
            {
                JOptionPane.showMessageDialog(frame, 
                    "Error - you need to enter a item number of supplier name");
            }
            else{

                try {
                    findOutIfInputIsANumber = Integer.parseInt(ItemOrSupplierString);

                } catch (NumberFormatException e) {
                    //error
                    supplierResultSet = null;
                    findOutIfInputIsANumber = 0;

                }
                if(findOutIfInputIsANumber != 0){
                    itemResultSet = null;
                    findItemInformation();
                    displayItemOrSupplierInformation(itemResultSet);
                }
                else if(findOutIfInputIsANumber == 0)
                {
                    findSupplierInformation();
                    displayItemOrSupplierInformation(supplierResultSet);

                }

            }
        }
        else if (event.getSource() == deleteButton)
        {
            if (Suppliers.getSupplierId() == 0 && Fireplaces.getItemCode() == 0 )  
            { 
                JOptionPane.showMessageDialog(frame, 
                    "Error - you need to enter a item number of supplier name");
            }
            else{
                if(Suppliers.getSupplierId() == 0)
                {

                    deleteItem();

                }
                else if(Fireplaces.getItemCode() == 0 )
                {
                    deleteSupplier();

                }
            }

        }
         else if (event.getSource() == refreshButton)
        {

                            JOptionPane.showMessageDialog(frame, 
                    "Refreshed");
    clearScroll();
            PaintSrollPane();
        }
    }
    
    private void clear(){
                  clearScroll();       
            
    }
    
    private void deleteSupplier()
    {
        // Check to see if we can connect to database table
        if ( DatabaseHandler.makeConnectionToDB() == -1)
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }else
        {
            int Results = DatabaseHandler.deleteSupplier(Suppliers.getSupplierId());

            JOptionPane.showMessageDialog (frame, 
                "Sucessfully deleted supplier.");
            //JOptionPane.showMessageDialog (frame, 
            //   "Successfully deleted .");

            // 

            if(Results == -1 )
            {
                JOptionPane.showMessageDialog (frame, 
                    "There are problems .");

            }

         


            if ( DatabaseHandler.closeConnection() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Problem closing data base conection");
            }
        }
    }

    private void  deleteItem()
    {

        if ( DatabaseHandler.makeConnectionToDB() == -1)
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }else
        {
            int Results = DatabaseHandler.deleteItem(Fireplaces.getItemCode());
             if(Results == -1 )
            {
            JOptionPane.showMessageDialog (frame, 
            "There are problems .");

            }
            JOptionPane.showMessageDialog (frame, 
                "Successfully deleted  .");
            clearScroll();       
            PaintSrollPane();


            if ( DatabaseHandler.closeConnection() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Problem closing data base conection");
            }
        }
    }

    private void findSupplierInformation()
    {
        supplierResultSet = DatabaseHandler.searchSupplier(ItemOrSupplierString);

        recordCount = 0;
        try
        {
            if (!supplierResultSet.next()== true )   
            {
                // Display an error message
                JOptionPane.showMessageDialog (frame, 
                    "No record(s) which satisfy search criteria.");
                // Set recordsFound to false
            }
        }
        catch (SQLException e)  // Problem ....
        {
            JOptionPane.showMessageDialog (frame, 
               "Problem reading record(s)..."+ e);

        }
    }

    private void findItemInformation()
    {
        int itemID = Integer.valueOf(ItemOrSupplierString);
        itemResultSet = DatabaseHandler.searchItemComplete(itemID);

        recordCount = 0;
        try
        {
            if (!itemResultSet.next()== true )   
            {
                // Display an error message
                JOptionPane.showMessageDialog (frame, 
                    "No record(s) which satisfy search criteria.");


                // Set recordsFound to false
            }

        }
        catch (SQLException e)  // Problem ....
        {
            //JOptionPane.showMessageDialog (frame, 
            //   "Problem reading record(s)..." e);
            System.out.println(e);
        }

    }

    /**
     * Display the book details in the result set on the form.
     */
    private void displayItemOrSupplierInformation(ResultSet resultSets)
    {

        // Lots of methods which follow which require exception-
        // handling code, e.g. next(), previous(), getString(), etc..
        try
        {
            // The ResultSet many have 0, 1 or more records
            // These need to be displayed on the form

            // When recordCount is 0 ...                      
            if ( recordCount == 0 ) 
            {
                // There must be at least one record in ResultSet if
                // we are in here, so set recordsFound to true
                recordsFound = true;
                if(findOutIfInputIsANumber != 0)
                {

                    Fireplaces.setItemCode(Integer.parseInt(resultSets.getString("item_id")));
                    Fireplaces.setDescription(resultSets.getString("description"));   
                    Fireplaces.setStyle(resultSets.getString("style"));   
                    Fireplaces.setFinish(resultSets.getString("finish"));                            
                    Fireplaces.setUnitPrice(resultSets.getDouble("unit_price"));                            
                    Fireplaces.setQuantityInStock(resultSets.getInt("quantityInStock"));                            

                }
                else if(findOutIfInputIsANumber == 0)
                {

                    Suppliers.setSupplierId(resultSets.getInt("supplier_id"));
                    Suppliers.setName(resultSets.getString("supplier_name"));    
                    Suppliers.setPropertyNumber(resultSets.getString("property_number"));
                    Suppliers.setFirstLine(resultSets.getString("first_line_addrress"));
                    Suppliers.setTown(resultSets.getString("city")) ;
                    Suppliers.setPostCode(resultSets.getString("post_code"));                              
                    Suppliers.setEmail(resultSets.getString("email_address"));
                    Suppliers.setPhoneNumber(resultSets.getString("phone_number"));
                    Suppliers.setPointOfContact(resultSets.getString("point_of_contact"));
                }                                      

            }
            // Display the data on the form only if recordsFound
            // contains true
            if ( recordsFound ) 
            {
                //scroll.setLayout(new GridLayout(4,5));
                // Read the contents of each item in the ResultSet 
                if(findOutIfInputIsANumber != 0)
                {

                    itemInformation.append("Item ID: " + Fireplaces.getItemCode() + "\n" + 
                        "Description: " + Fireplaces.getDescription() + "\n"+
                        "Style: " + Fireplaces.getStyle() + "\n"+
                        "Finish: " + Fireplaces.getFinish() + "\n"+
                        "Unit Price: " + Fireplaces.getUnitPrice() + "\n"+
                        "Quantity In Stock: " + Fireplaces.getQuantityInStock() + "\n"

                    );
                }
                else{

                    supplierInformation.append(
                        "Supplier Id: " + Suppliers.getSupplierId() + "\n" + 
                        "Name: " + Suppliers.getName() + "\n"+
                        "Property Number: " + Suppliers.getPropertyNumber() + "\n"+
                        "First Line: " + Suppliers.getFirstLine() + "\n"+
                        "City: " + Suppliers.getTown() + "\n"+
                        "Post Code: " + Suppliers.getPostCode() + "\n"+
                        "Email Address: " + Suppliers.getEmail() + "\n"+
                        "Phone Number: " + Suppliers.getPhoneNumber() + "\n"+
                        "Point Of Contact: " + Suppliers.getPointOfContact() + "\n"
                    );
                }
            }
        }

        catch (SQLException e)  // Problem ....
        {
            //JOptionPane.showMessageDialog (frame, 
            //   "Problem reading record(s)..." e);
            System.out.println(e);
        }

    }  // End displayBookDetails
    /**
     * Method to read book title from text field
     */
    private void readItemOrSupplierID()
    {
        ItemOrSupplierString = searchBox.getText();
    }

    /**
     * 
     * create the panels required 
     */
    private void createPanels()
    {
        // Create Panels 
        gerneralInfoPanel = new JPanel();
    } // End createPanels()
    private void finaliseScreen()
    {
        gerneralInfoPanel.setBounds (0,0,900,550);
        // And add to mainPanel
        this.add (gerneralInfoPanel);
    } // End finaliseScreen()

    private void clearScroll(){
        scrollItems.removeAll();   
        scrollSupplier.removeAll();   
        gerneralInfoPanel.remove(scrollItems);
        gerneralInfoPanel.remove(scrollSupplier);
        searchBox.setText(null);

        itemInformation.setText(null);
        supplierInformation.setText(null);
        Suppliers.setSupplierId(0);
        Fireplaces.setItemCode(0);
    }

    private void PaintSrollPane()
    {

        scrollItems = new JScrollPane(GetAllItems());
        scrollItems.setPreferredSize(new Dimension(800, 100));

        scrollSupplier = new JScrollPane(GetAllSuppliers());
        scrollSupplier.setPreferredSize(new Dimension(800, 100));

        gerneralInfoPanel.add(scrollItems); 
        gerneralInfoPanel.add(scrollSupplier);        
        layout.putConstraint(SpringLayout.WEST, scrollItems,10, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, scrollItems,85, SpringLayout.NORTH, gerneralInfoPanel);

        layout.putConstraint(SpringLayout.WEST, scrollSupplier,10, SpringLayout.WEST, gerneralInfoPanel);
        layout.putConstraint(SpringLayout.NORTH, scrollSupplier,240, SpringLayout.NORTH, gerneralInfoPanel);
        gerneralInfoPanel.revalidate();
        gerneralInfoPanel.repaint(); // sometimes needed
    }

    /**
     * Method to clear the text fields on the form
     */
    private void clearForm()
    {
        searchBox.setText(null);

        itemInformation.setText(null);
        supplierInformation.setText(null);
        Suppliers.setSupplierId(0);
        Fireplaces.setItemCode(0);
    } // End clearForm()
    static HomePanel getInstance() {
        if (instance == null) {
            instance = new HomePanel();
        }

        return instance;
    }
}
