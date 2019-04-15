
/**
 * Write a description of class AddItemPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;              // ResultSet Class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class AddItemPanel extends JPanel implements ActionListener
{
    private JLabel description;
    private JLabel supplierCodeLabel, SupplierCodeLabelExplain,
    ItemCodeLabel,  DescriptionLabel, UnitPriceLabel,
    QuantityInStockLabel, StyleLabel, FinishLabel, mainTitleLabel;
    private JTextField ItemCodeBox, UnitPriceBox, QuantityInStockBox, StyleBox, FinishBox;
    private JTextArea DescriptionBox;
    private JScrollPane scroll;
    private JFrame frame = new JFrame();
    private JButton submitButton, refreshButton;
    private GridBagLayout gbl;
    private JPanel SuppliersItems;   
    private         SpringLayout layout = new SpringLayout();
    private GridBagConstraints gbc;
    private Font title = new Font("Courier", Font.BOLD,25);
    private Font text = new Font("Courier", Font.BOLD,16);
    private static AddItemPanel instance = null;
    private JTable resultTable;
    private String[] suppliersArray = {"Supplier ID", "Supplier Name"};
    public AddItemPanel()
    {
        this.setLayout(null);
        createPanels();
        setUpForm();
        submitButton.addActionListener (this);
        refreshButton.addActionListener (this);        
        finaliseScreen();
        // this.add(table);
    }

    private void createPanels()
    {
        // Create Panels 

        SuppliersItems = new JPanel();

    } // End createPanels()

    private void setUpForm()
    {
        createPanels();

        SupplierCodeLabelExplain =  new JLabel(
            "Supplier code");
        SupplierCodeLabelExplain.setFont(text);                
        SuppliersItems.add(SupplierCodeLabelExplain);
        mainTitleLabel =  new JLabel(
            "Add A New Fireplace Item");
        mainTitleLabel.setFont(title);
        SuppliersItems.add(mainTitleLabel);
        supplierCodeLabel =  new JLabel();
        supplierCodeLabel.setFont(text);                       
        SuppliersItems.add(SupplierCodeLabelExplain);
        SuppliersItems.add(supplierCodeLabel);
        // label 
        ItemCodeLabel = new JLabel(
            "Enter Fireplace code");   
        ItemCodeLabel.setFont(text);       
        SuppliersItems.add(ItemCodeLabel);
        ItemCodeBox = new JTextField(15);

        SuppliersItems.add(ItemCodeBox);

        DescriptionLabel = new JLabel(
            "Enter Description");  
        DescriptionLabel.setFont(text);    
        SuppliersItems.add(DescriptionLabel);
        DescriptionBox = new JTextArea(5, 20);

        SuppliersItems.add(DescriptionBox);

        UnitPriceLabel = new JLabel(
            "Enter Unit Price"); 
        UnitPriceLabel.setFont(text);               
        SuppliersItems.add(UnitPriceLabel);
        UnitPriceBox = new JTextField(15);
        SuppliersItems.add(UnitPriceBox);

        QuantityInStockLabel = new JLabel(
            "Enter Quantity in Stock");  
        QuantityInStockLabel.setFont(text);                          
        SuppliersItems.add(QuantityInStockLabel);
        QuantityInStockBox = new JTextField(15);
        SuppliersItems.add(QuantityInStockBox);

        StyleLabel = new JLabel(
            "Enter Style");   
        StyleLabel.setFont(text);                                      
        SuppliersItems.add( StyleLabel);

        StyleBox = new JTextField("", 15);
        SuppliersItems.add(StyleBox);

        FinishLabel = new JLabel(
            "Enter Finish"); 
        FinishLabel.setFont(text);                                                  
        SuppliersItems.add(FinishLabel);
        SuppliersItems.add("finish",FinishLabel);

        FinishBox = new JTextField(15);

        SuppliersItems.add(FinishBox);


        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(140, 80));
        SuppliersItems.add(submitButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(140, 80));
        SuppliersItems.add(refreshButton);

        SuppliersItems.setLayout(layout);
        PaintSrollPane();
        //scroll.setPreferredSize( 400, 600 );
        SuppliersItems.setBackground(new Color(142, 164, 237));
        layout.putConstraint(SpringLayout.WEST, mainTitleLabel,20, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, mainTitleLabel,0, SpringLayout.NORTH, SuppliersItems);

        layout.putConstraint(SpringLayout.WEST, submitButton,650, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, submitButton,420, SpringLayout.NORTH, SuppliersItems);
        // supplier name  

        layout.putConstraint(SpringLayout.WEST, refreshButton,650, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, refreshButton,300, SpringLayout.NORTH, SuppliersItems);
        // supplier name  

        layout.putConstraint(SpringLayout.WEST, SupplierCodeLabelExplain,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.SOUTH, SupplierCodeLabelExplain, 25, SpringLayout.NORTH, SuppliersItems);

        layout.putConstraint(SpringLayout.WEST, supplierCodeLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.SOUTH, supplierCodeLabel, 55, SpringLayout.NORTH, SuppliersItems);

        //    SuppliersItems);
        // supplier name  

        layout.putConstraint(SpringLayout.WEST, ItemCodeLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.SOUTH, ItemCodeLabel, 85, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, ItemCodeBox,0, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, ItemCodeBox,100, SpringLayout.NORTH, 
            SuppliersItems);
        // supplier name  

        layout.putConstraint(SpringLayout.WEST, DescriptionLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, DescriptionLabel,130, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, DescriptionBox,50, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, DescriptionBox,170, SpringLayout.NORTH, 
            SuppliersItems);
        // supplier name  
        layout.putConstraint(SpringLayout.WEST, QuantityInStockLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, QuantityInStockLabel,260, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, QuantityInStockBox,0, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, QuantityInStockBox,295, SpringLayout.NORTH, 
            SuppliersItems);

        layout.putConstraint(SpringLayout.WEST, UnitPriceLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, UnitPriceLabel,325, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, UnitPriceBox,0, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, UnitPriceBox,360, SpringLayout.NORTH, 
            SuppliersItems);

        layout.putConstraint(SpringLayout.WEST, StyleLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.SOUTH, StyleLabel,410, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, StyleBox,0, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, StyleBox,420, SpringLayout.NORTH, 
            SuppliersItems);

        layout.putConstraint(SpringLayout.WEST, FinishLabel,390, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.SOUTH, FinishLabel,470, SpringLayout.NORTH, SuppliersItems);
        layout.putConstraint(SpringLayout.EAST, FinishBox,0, SpringLayout.SOUTH, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, FinishBox,480, SpringLayout.NORTH, 
            SuppliersItems);

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
            // Check to see if there is a problem loading driver
            if( DatabaseHandler.loadDriver() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Unable to load driver...");
            }
            else if(supplierCodeLabel.getText() == "" ||
            UnitPriceBox.getText() == "" || ItemCodeBox.getText() == "")  // Ok to write record to database table
            {
                JOptionPane.showMessageDialog (frame, "Please fill in the item details");

            }
            else{
                writeItemRecord();  // Call method which is coded below.
            }

        }
        else if (event.getSource() == refreshButton)
        {

            JOptionPane.showMessageDialog(frame, 
                "Refreshed");
            clearForm();
            PaintSrollPane();
        }
    } // End actionPerformed()
    /**
     *  Method to write a book record
     */
    private void writeItemRecord()
    {
        // Check to see if we can connect to database table
        if ( DatabaseHandler.makeConnectionToDB() == -1)
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }
        else  // Ok, so first read data from the text fields
        {

            // Read data from form and store data                      
            String itemIds = ItemCodeBox.getText();
            String itemDescription = DescriptionBox.getText();
            String itemStyle = StyleBox.getText();
            String itemFinish = FinishBox.getText();
            String itemPrice = UnitPriceBox.getText();
            String quantityInStock = QuantityInStockBox.getText();
            String supplierIds = supplierCodeLabel.getText();

            
            // Convert priceStr to a float
            int supplierInt = Integer.valueOf( supplierIds );
            // Convert priceStr to a float
            int itemInt = 0;
            try {

                itemInt = Integer.valueOf( itemIds );
            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog (frame, "Item ID is not a number");
            }
            // Convert priceStr to a float
            int quantityInt = Integer.valueOf( quantityInStock );
            // Convert priceStr to a float
            double priceDouble = Double.valueOf( itemPrice );
            // Create a Book oject
            FirePlaces firePlaces = new FirePlaces();                      
            // Set the attributes for the Book object  
            firePlaces.setSupplierId(supplierInt);
            firePlaces.setItemCode (itemInt);
            firePlaces.setDescription (itemDescription);
            firePlaces.setUnitPrice(priceDouble);
            firePlaces.setQuantityInStock (quantityInt);
            firePlaces.setStyle (itemStyle);
            firePlaces.setFinish (itemFinish);

            // Write book record.  Method writeToBookTable() returns
            // 0 of OK writing record, -1 if there is a problem.  I store
            // the returned value in a variable called error.
            int error = DatabaseHandler.writeFirePlaceToTable(firePlaces.getItemCode(),
                    firePlaces.getDescription(),
                    firePlaces.getStyle(),
                    firePlaces.getFinish(),
                    firePlaces.getUnitPrice(),
                    firePlaces.getQuantityInStock(),
                    firePlaces.getSupplierId());

            // Check if there is a problem writing the record, in 
            // which case error will contain -1                                         
            if (error == -1)
            {
                JOptionPane.showMessageDialog (frame, "There is a problem with the item table");
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

    private JTable GetLastSupplierID()
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
            rs = DatabaseHandler.getAllSupplier();

            try
            {
                Suppliers Suppliers = new Suppliers();                      
                ResultSetMetaData rsmt = rs.getMetaData();
                int numberOfRows = rsmt.getColumnCount();
                Vector column = new Vector(numberOfRows);
                column.addAll(Arrays.asList(suppliersArray));
                Vector data = new Vector();
                Vector row = new Vector();                                
                while(rs.next()){
                    row = new Vector(numberOfRows);   
                    for(int i = 1;i<=numberOfRows;i++){
                        row.add(rs.getString(i));
                    }
                    data.add(row);

                }
                resultTable = new JTable(data, column);
                resultTable.getColumnModel().getColumn(0).setPreferredWidth(27);
                resultTable.getColumnModel().getColumn(1).setPreferredWidth(27);

                resultTable.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt){
                            int index = resultTable.getSelectedRow();

                            TableModel model = resultTable.getModel();
                            int supplierId = Integer.parseInt(model.getValueAt(index, 0).toString());
                            Suppliers supplierIDGet = new Suppliers();
                            supplierIDGet.setSupplierId(supplierId);
                            supplierCodeLabel.setText(String.valueOf(supplierIDGet.getSupplierId()));
                        }
                    });

            }
            catch (SQLException e)
            {
                // Report an error message is there is a problem with the result set
                JOptionPane.showMessageDialog (frame, "Problem displaying book data");
            }
        }
        return  resultTable;  
    }

    private void finaliseScreen()
    {

        SuppliersItems.setBounds (0,0,900,550);
        // And add to mainPanel
        this.add (SuppliersItems);

    } // End finaliseScreen()
    private void PaintSrollPane()
    {
        scroll = new JScrollPane(GetLastSupplierID());
        scroll.setPreferredSize(new Dimension(300, 420));
        SuppliersItems.add(scroll); 
        layout.putConstraint(SpringLayout.WEST, scroll,20, SpringLayout.WEST, SuppliersItems);
        layout.putConstraint(SpringLayout.NORTH, scroll,80, SpringLayout.NORTH, SuppliersItems);

        SuppliersItems.revalidate();
        SuppliersItems.repaint(); // sometimes needed
    }

    /**
     * Method to clear the text fields on the form
     */
    private void clearForm()
    {

        // Simply put an empty string into each textfield    

        DescriptionBox.setText(null);
        supplierCodeLabel.setText(null);
        ItemCodeBox.setText(null);
        UnitPriceBox.setText(null);                   
        QuantityInStockBox.setText(null);
        StyleBox.setText(null);
        FinishBox.setText(null);
        scroll.removeAll();   
        SuppliersItems.remove(scroll);
    } // End clearForm()
    static AddItemPanel getInstance() {
        if (instance == null) {
            instance = new AddItemPanel();
        }

        return instance;
    }

}
