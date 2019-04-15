
/**
 * Write a description of class AddItemPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;              // ResultSet Class
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableModel;
import java.util.Vector;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class updateInventoryPanel extends JPanel implements ActionListener
{
    private JLabel itemIdLabel, titleLable, ItemDisplay;
    private JLabel itemIdResultLabel;
    private JPanel updatePanel;
    private JScrollPane scroll;
    private JFrame frame = new JFrame();
    private Font title = new Font("Courier", Font.BOLD,26);
    private Font text = new Font("Courier", Font.BOLD,20);
    private JTable resultTable;
    private JSpinner quantityInStockSpinner;
    private JButton submitButton, refreshButton;
    private static updateInventoryPanel instance = null;
    private SpringLayout layout = new SpringLayout();
    String[] itemInfo = {"Item Id", "Description", "Unit Price", "Quantity In Stock"};
    public updateInventoryPanel()
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

        updatePanel = new JPanel();

    } // End createPanels()

    private void setUpForm()
    {
        createPanels();
        scroll = new JScrollPane(GetTableForFiretables());

        updatePanel.setLayout(layout);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(140, 80));    
        // label

        refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(140, 80));
        updatePanel.add(refreshButton);
        itemIdLabel = new JLabel(
            "Supplier ID: ");      
        itemIdLabel.setFont(text);
        updatePanel.add(itemIdLabel);

        titleLable = new JLabel(
            "Chesney Fireplaces Update Inventory");    
        titleLable.setFont(title);
        updatePanel.add(titleLable);

        ItemDisplay = new JLabel(
            "Current Fireplaces");    
        ItemDisplay.setFont(text);
        updatePanel.add(ItemDisplay);

        itemIdResultLabel = new JLabel();    
        itemIdResultLabel.setFont(text);
        updatePanel.add(itemIdResultLabel);
        updatePanel.add(submitButton);

        //scroll.setPreferredSize( 400, 600 );
        PaintSrollPane();
        //spinner

        quantityInStockSpinner = new JSpinner();
        quantityInStockSpinner.setPreferredSize(new Dimension(90, 50));
        updatePanel.add(quantityInStockSpinner);
        updatePanel.setBackground(new Color(142, 164, 237));

        
        layout.putConstraint(SpringLayout.WEST, itemIdLabel,590, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, itemIdLabel, 100, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, titleLable,220, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, titleLable, 30, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, ItemDisplay,210, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, ItemDisplay, 100, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, itemIdResultLabel,700, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, itemIdResultLabel, 100, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, submitButton,565, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, submitButton, 300, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, refreshButton,565, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, refreshButton, 400, SpringLayout.NORTH, updatePanel);

        layout.putConstraint(SpringLayout.WEST, quantityInStockSpinner,590, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.SOUTH, quantityInStockSpinner, 200, SpringLayout.NORTH, updatePanel);
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
            else if(itemIdResultLabel.getText() == "")  // Ok to write record to database table
            {
                JOptionPane.showMessageDialog (frame, "Nothing to submit...");
            }
            else{
                UpdateRecord();  // Call method which is coded below.
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
    private void UpdateRecord()
    {
        // Check to see if we can connect to database table
        if ( DatabaseHandler.makeConnectionToDB() == -1)
        {
            JOptionPane.showMessageDialog (frame, "Unable to connect to database table (BOOKS)");
        }
        else  // Ok, so first read data from the text fields
        {

            // Read data from form and store data                      
            String itemIds = itemIdResultLabel.getText();

            int quantityValue = (Integer)  quantityInStockSpinner.getValue();

            // Convert priceStr to a float
            int itemInt = Integer.valueOf( itemIds );

            // Create a Book oject
            FirePlaces firePlaces = new FirePlaces();                      
            // Set the attributes for the Book object  
            firePlaces.setItemCode (itemInt);
            firePlaces.setQuantityInStock (quantityValue);

            // Write book record.  Method writeToBookTable() returns
            // 0 of OK writing record, -1 if there is a problem.  I store
            // the returned value in a variable called error.
            int error = DatabaseHandler.updateInventory(firePlaces.getItemCode(),
                    firePlaces.getQuantityInStock());

            // Check if there is a problem writing the record, in 
            // which case error will contain -1                                         
            if (error == -1)
            {
                JOptionPane.showMessageDialog (frame, "There is a problem with the item table");
            }

            // Clear the form - actual method is coded below
            clearForm();
            PaintSrollPane();

            // Close database connection.  Report an error message
            // if there is a problem.
            if ( DatabaseHandler.closeConnection() == -1 )
            {
                JOptionPane.showMessageDialog (frame, "Problem closing data base conection");
            }

        }
    }  // End writeBookRecord()    

    private JTable GetTableForFiretables()
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
            rs = DatabaseHandler.getAllFirePlacesForUpdate();

            try
            {
                FirePlaces firePlaces = new FirePlaces();                      
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
                resultTable = new JTable(data, column);
                // resultTable.getColumnModel().getColumn(0).setPreferredWidth(27);
                // resultTable.getColumnModel().getColumn(1).setPreferredWidth(27);

                resultTable.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt){
                            int index = resultTable.getSelectedRow();
                            TableModel model = resultTable.getModel();

                            int firePlaceId = Integer.parseInt(model.getValueAt(index, 0).toString());
                            int firePlaceQuantity = Integer.parseInt(model.getValueAt(index, 3).toString());
                            FirePlaces fireplaceUpdateQuantity = new FirePlaces();
                            fireplaceUpdateQuantity.setItemCode(firePlaceId);
                            fireplaceUpdateQuantity.setQuantityInStock(firePlaceQuantity);     
                            itemIdResultLabel.setText(String.valueOf(fireplaceUpdateQuantity.getItemCode()));
                            quantityInStockSpinner.setValue(fireplaceUpdateQuantity.getQuantityInStock());
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

        updatePanel.setBounds (0,0,900,550);
        // And add to mainPanel
        this.add (updatePanel);

    } // End finaliseScreen()
    private void PaintSrollPane()
    {
        scroll = new JScrollPane(GetTableForFiretables()); 
        scroll.setPreferredSize(new Dimension(350, 350));  
        updatePanel.add(scroll); 
        layout.putConstraint(SpringLayout.WEST, scroll,100, SpringLayout.WEST, updatePanel);
        layout.putConstraint(SpringLayout.NORTH, scroll,150, SpringLayout.NORTH, updatePanel);
        updatePanel.revalidate();
        updatePanel.repaint(); // sometimes needed
    }

    /**
     * Method to clear the text fields on the form
     */
    private void clearForm()
    {

        // Simply put an empty string into each textfield    
        quantityInStockSpinner.setValue(0);
        itemIdResultLabel.setText(null);
        scroll.removeAll();   
        updatePanel.remove(scroll);

    } // End clearForm()
    static updateInventoryPanel getInstances() {
        if (instance == null) {
            instance = new updateInventoryPanel();
        }

        return instance;
    }
}
