
/**
 * Write a description of class AddItemPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.util.ArrayList; // import the ArrayList class
import java.util.Enumeration;
import java.awt.*;
import java.util.Vector;
import java.sql.*;              // ResultSet Class
import java.awt.event.*;
public class UpdateItemSupplier extends JPanel implements ActionListener
{
    static Vector<AbstractButton> allButtons = new Vector<AbstractButton>();
    AbstractButton selectedButton = null;

    private JLabel resultsLabelDescription, resultLabel,resultsDescriptionTitle, resultDescription, resultsSupplierDescription, resultSupplier,
        resultSupplierName, supplierNamePopUp,selectSupplierLabel, mainTitleLabel, SupplierOutputLabel, enterItemIdLabel;   
    private JFrame frame = new JFrame();   
    private JScrollPane scroll;
    private JPanel UpdateItemSupplierPanel;
    private GridBagLayout gbl;
    private ArrayList<String> supplierNames;
    private ArrayList<JRadioButton> arrayListOfButtons;
    private GridBagConstraints gbc;
    private JTextField searchBox;
    private static UpdateItemSupplier instance = null;
    private SpringLayout layout = new SpringLayout();
    private JRadioButton[] jrbColor = new JRadioButton[20];
    private  String supplierName;
    private ButtonGroup group;
    private int recordCount, itemID, newSupplierIdFromDB,  SupplierIdInt ;
    private boolean  recordsFound;
    private JButton submitButton, updateSupplierButton;
    private ButtonModel model;
    private Font title = new Font("Courier", Font.BOLD,32);
    private Font text = new Font("Courier", Font.BOLD,16);    
    private ResultSet rs, secondRs, thirdResultSet;
    private String fireplaceIdFromDb, descriptionFromDb, supplierIDFromDb, itemIdString, supplierNameFromDb, supplierNameFromButton;
    public UpdateItemSupplier()
    {
        this.setLayout(null);
        createPanels();
        setUpForm();
        submitButton.addActionListener (this);
        updateSupplierButton.addActionListener (this);
        finaliseScreen();
    }

    private void createPanels()
    {
        // Create Panels 
       
        UpdateItemSupplierPanel = new JPanel();
    } // End createPanels()

    private void setUpForm()
    {
        createPanels();
        
        selectSupplierLabel = new JLabel("Select Supplier");
        selectSupplierLabel.setFont(text);
        
        supplierNamePopUp = new JLabel();
        supplierNamePopUp.setFont(text);
        
        enterItemIdLabel = new JLabel("Enter Item ID");
                       enterItemIdLabel.setFont(text);
        mainTitleLabel = new JLabel("Update Item Supplier");
        
        mainTitleLabel.setFont(title);
        UpdateItemSupplierPanel.setLayout(layout);
        SupplierOutputLabel = new JLabel("Suppliers");
        scrollButtonOfSuppliers();
        resultsLabelDescription = new JLabel();
        resultsLabelDescription.setFont(text);
        resultsSupplierDescription = new JLabel();
        resultsSupplierDescription.setFont(text);
        resultsDescriptionTitle = new JLabel();
        resultsDescriptionTitle.setFont(text);
        resultLabel = new JLabel();
               resultLabel.setFont(text);
        resultDescription = new JLabel();
                resultDescription.setFont(text);
        resultSupplier = new JLabel();
                resultSupplier.setFont(text);
        resultSupplierName = new JLabel();
                resultSupplierName.setFont(text);
        searchBox = new JTextField(20);
        submitButton = new JButton("submit");
        submitButton.setPreferredSize(new Dimension(140, 80));        
                       resultsLabelDescription.setText("Fireplace ID: ");

                resultsDescriptionTitle.setText("Item Description: ");

                resultsSupplierDescription.setText("Supplier ID: ");

                supplierNamePopUp.setText("Supplier Name: ");
        updateSupplierButton= new JButton("Update Supplier");
        updateSupplierButton.setEnabled(false);
        updateSupplierButton.setPreferredSize(new Dimension(140, 80));
                UpdateItemSupplierPanel.add(mainTitleLabel);
        UpdateItemSupplierPanel.add(resultSupplierName);
        UpdateItemSupplierPanel.add(resultLabel);
                               UpdateItemSupplierPanel.add(resultsLabelDescription);
                               UpdateItemSupplierPanel.add(resultsDescriptionTitle);
                               UpdateItemSupplierPanel.add(resultsSupplierDescription);
        UpdateItemSupplierPanel.add(resultDescription);
UpdateItemSupplierPanel.add(resultSupplier);        
        UpdateItemSupplierPanel.add(searchBox);
               UpdateItemSupplierPanel.add( enterItemIdLabel);
        UpdateItemSupplierPanel.add(submitButton);
        UpdateItemSupplierPanel.add(updateSupplierButton);
UpdateItemSupplierPanel.add(supplierNamePopUp);
        UpdateItemSupplierPanel.add(selectSupplierLabel);
        UpdateItemSupplierPanel.setBackground(new Color(142, 164, 237));
        
                layout.putConstraint(SpringLayout.WEST, mainTitleLabel,350, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.NORTH, mainTitleLabel,0, SpringLayout.NORTH, UpdateItemSupplierPanel);
        
                        layout.putConstraint(SpringLayout.WEST, selectSupplierLabel,100, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.NORTH, selectSupplierLabel,30, SpringLayout.NORTH, UpdateItemSupplierPanel);
        
        layout.putConstraint(SpringLayout.WEST, searchBox,350, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, searchBox,50, SpringLayout.NORTH, scroll);
        
        
         layout.putConstraint(SpringLayout.WEST, enterItemIdLabel,410, SpringLayout.WEST, scroll);
        layout.putConstraint(SpringLayout.NORTH, enterItemIdLabel,10, SpringLayout.NORTH, scroll);

        layout.putConstraint(SpringLayout.WEST, submitButton,500, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, submitButton,350, SpringLayout.NORTH, UpdateItemSupplierPanel);

        layout.putConstraint(SpringLayout.WEST, resultLabel,400, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultLabel,190, SpringLayout.NORTH, UpdateItemSupplierPanel);
       layout.putConstraint(SpringLayout.WEST, resultsLabelDescription,250, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultsLabelDescription,190, SpringLayout.NORTH, UpdateItemSupplierPanel);
        
               layout.putConstraint(SpringLayout.WEST, resultsDescriptionTitle,250, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultsDescriptionTitle,220, SpringLayout.NORTH, UpdateItemSupplierPanel);
        
                     layout.putConstraint(SpringLayout.WEST, resultsSupplierDescription,250, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultsSupplierDescription,250, SpringLayout.NORTH, UpdateItemSupplierPanel);

        layout.putConstraint(SpringLayout.WEST, resultDescription,400, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultDescription,220, SpringLayout.NORTH, UpdateItemSupplierPanel);

        layout.putConstraint(SpringLayout.WEST, resultSupplier,400, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultSupplier,250, SpringLayout.NORTH, UpdateItemSupplierPanel);

        layout.putConstraint(SpringLayout.WEST, resultSupplierName,400, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, resultSupplierName,350, SpringLayout.NORTH, UpdateItemSupplierPanel);

        
                layout.putConstraint(SpringLayout.WEST, supplierNamePopUp,250, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, supplierNamePopUp,350, SpringLayout.NORTH, UpdateItemSupplierPanel);

        
        layout.putConstraint(SpringLayout.WEST, updateSupplierButton,500, SpringLayout.WEST, UpdateItemSupplierPanel);
        layout.putConstraint(SpringLayout.SOUTH, updateSupplierButton,450, SpringLayout.NORTH, UpdateItemSupplierPanel);
    }

    private JScrollPane scrollButtonOfSuppliers(){
        ResultSet rs;
        if ( DatabaseHandler.loadDriver() == -1 )
        {
            JOptionPane.showMessageDialog (frame, "Problem loading the JDBC/ODBC driver.");
        }
        else 
        // Check to see if we can connect to the database table
        if ( DatabaseHandler.makeConnectionToDB() == -1 )
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (normal)");
        }
        else  // Search for all the books
        {
            rs = DatabaseHandler.getAllSuppliers();

            try
            {
                //scroll.setLayout(new GridLayout(4,5));

                supplierNames = new ArrayList<String>();
                while (rs.next()) {
                    supplierNames.add(rs.getString("supplier_name"));
                }

                group = new ButtonGroup();
                scroll = new JScrollPane();
                JPanel panelFullOfButtons = new JPanel(new GridLayout(0,1,1,2));

                for (int i = 0; i < supplierNames.size(); i++) {
                    jrbColor[i] = new JRadioButton(supplierNames.get(i));
                    group.add(jrbColor[i]);
                    panelFullOfButtons.add(jrbColor[i]) ;  
                } 

                panelFullOfButtons.setPreferredSize(new Dimension(200, 500));

                scroll.add(panelFullOfButtons);
                scroll.setViewportView(panelFullOfButtons);
                scroll.setPreferredSize(new Dimension(150, 400));
                UpdateItemSupplierPanel.add(scroll);
                layout.putConstraint(SpringLayout.WEST, scroll,80, SpringLayout.WEST, UpdateItemSupplierPanel);
                layout.putConstraint(SpringLayout.NORTH, scroll,80, SpringLayout.NORTH, UpdateItemSupplierPanel);

            }
            catch (SQLException e)
            {
                // Report an error message is there is a problem with the result set
                //System.out.println(supplierName);
                JOptionPane.showMessageDialog (frame, "You have an error here you see");
            }
        }
        return  scroll;  

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
        if (event.getSource() == submitButton)
        {
            readItemID();
            // Display an error message if there is no data in
            // title text field
            if (itemIdString.equals(""))  
            {
                JOptionPane.showMessageDialog(frame, 
                    "Error - you need to enter a Book Title");
            }
            else{

                itemID = Integer.valueOf(itemIdString);
                searchForSupplier();  // Call method which is coded below.

                displaySupplerToRadioButton();
            }
        }
        else if (event.getSource() == updateSupplierButton)
        {     
            //  model = jrbColor[SupplierIdInt].getModel();

            supplierNameFromButton = getSelectedButtonText(group);
   

            findSupplierID();

            updateSupplierItemID();

        }

    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private void updateSupplierItemID()
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

                // Try to advance the ResultSet pointer
                if ( thirdResultSet.next()== true )   
                {

                    // There must be at least one record in ResultSet if
                    // we are in here, so set recordsFound to true
                    recordsFound = true;

                    newSupplierIdFromDB = thirdResultSet.getInt("supplier_id");

                }
                else  // If we reach this point, there must have been
                // no records which satisfied the search criteria
                {
                    // Display an error message
                    JOptionPane.showMessageDialog (frame, 
                        "No record(s) which satisfy search criteria.");

                    System.out.println("There are no results punl");
                    // Set recordsFound to false
                    recordsFound = false;

                }

            }

            // Display the data on the form only if recordsFound
            // contains true
            if ( recordsFound ) 
            {
                if ( DatabaseHandler.loadDriver() == -1 )
                {
                    JOptionPane.showMessageDialog (frame, "Problem loading the JDBC/ODBC driver.");
                }
                else 
                // Check to see if we can connect to the database table
                if ( DatabaseHandler.makeConnectionToDB() == -1 )
                {
                    JOptionPane.showMessageDialog (frame, "Unable to connect to database table (normal)");
                }
                else{

                    int findOutIfCorrect = DatabaseHandler.updateItemSupplier(newSupplierIdFromDB, itemID );
                    if(findOutIfCorrect == 0){
                        JOptionPane.showMessageDialog (frame, "Successfully updated");
                        this.clearForm();
                                updateSupplierButton.setEnabled(false);
                                submitButton.setEnabled(true);
                    }
                    
                    else{
                        JOptionPane.showMessageDialog (frame, "Somthing is wrong");
                    }

                }
            }}
        catch (SQLException e)  // Problem ....
        {
            //JOptionPane.showMessageDialog (frame, 
            //   "Problem reading record(s)..." e);
            System.out.println(e);
        }

    }

    private void findSupplierID()
    {
        thirdResultSet = DatabaseHandler.searchSupplier(supplierNameFromButton);

        recordCount = 0;
    }
    // End actionPerformed(
    /**
     * Method to read book title from text field
     */
    private void readItemID()
    {
        itemIdString = searchBox.getText();
    }

    /**
     * Search for books.  Search for books based
     * upon book title.
     */
    private void searchForSupplier()
    {

        // Call the bookSearch() method in DataBaseHandler Class
        // This method returns a reference to a ResultSet object.
        secondRs = DatabaseHandler.searchItem(itemID);

        // Set recordCount to 0
        recordCount = 0;

    }  // End searchForBooks()  
    /**
     * Display the book details in the result set on the form.
     */
    private void displaySupplerToRadioButton()
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

                // Try to advance the ResultSet pointer
                if ( secondRs.next()== true )   
                {
                    // There must be at least one record in ResultSet if
                    // we are in here, so set recordsFound to true
                    recordsFound = true;

                    fireplaceIdFromDb =  secondRs.getString("item_id");
                    descriptionFromDb = secondRs.getString("description");                              
                    supplierIDFromDb = secondRs.getString("supplier_id");
                    supplierNameFromDb = secondRs.getString("supplier_name");

                    // Add 1 to recordCount
                    recordCount++;

                }
                else  // If we reach this point, there must have been
                // no records which satisfied the search criteria
                {
                    // Display an error message
                    JOptionPane.showMessageDialog (frame, 
                        "No record(s) which satisfy search criteria.");

                    System.out.println("There are no results punl");
                    // Set recordsFound to false
                    recordsFound = false;

                }

            }

            // Display the data on the form only if recordsFound
            // contains true
            if ( recordsFound ) 
            {
                //scroll.setLayout(new GridLayout(4,5));
                // Read the contents of each item in the ResultSet 

                int SupplierIdInt = Integer.valueOf(supplierIDFromDb) - 1;
                resultsLabelDescription.setText("Fireplace ID: ");
                resultLabel.setText(fireplaceIdFromDb);           
                resultsDescriptionTitle.setText("Item Description: ");
                resultDescription.setText(descriptionFromDb);
                resultsSupplierDescription.setText("Supplier ID: ");
                resultSupplier.setText(supplierIDFromDb);   
                supplierNamePopUp.setText("Supplier Name: ");
                resultSupplierName.setText(supplierNameFromDb);   

                    model = jrbColor[SupplierIdInt].getModel();
                    group.setSelected(model, true);
                                updateSupplierButton.setEnabled(true);
                                submitButton.setEnabled(false);
            }
            // Enable the following textfields only if 
            // record count is greater than or equal to 1
            if (recordCount >= 1)
            {

                System.out.println("more than one value you see");
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
     * Method to clear the text fields on the form
     */
    private void clearForm()
    {
        
        // Simply put an empty string into each textfield    

        resultLabel.setText(null);
        resultDescription.setText(null); 
        resultSupplier.setText(null) ;
        resultSupplierName.setText(null);  
        searchBox.setText(null);
        group.clearSelection();

    } // End clearForm()
    private void finaliseScreen()
    {
        UpdateItemSupplierPanel.setBounds (0,0,900,650);
        // And add to mainPanel
       
        this.add (UpdateItemSupplierPanel);
    } // End finaliseScreen()
    static UpdateItemSupplier getInstance() {
        if (instance == null) {
            instance = new UpdateItemSupplier();
        }

        return instance;
    }
}
