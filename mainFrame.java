import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class mainFrame extends JFrame {
    private JFrame container; //declare the varible for the container
    private JPanel mainPanel; //declare the varible for the panel
    private JPanel CardPanel;
    private JPanel secondPanel; //declare the varible for the panel
    private JPanel homePanelButton, addNewItemPanelButton,
    panelButtonThree, panelButtonFour, updateInventoryPanel,
    deleteItemPanelButton, updateIteventorySupplierButton;
    private AddItemPanel  addItemPanel;
    private AddSupplierPanel addSupplierPanel;
    private updateInventoryPanel updateInventoryUI;
    private UpdateItemSupplier updateItemSupplierScreen;
    private Font title = new Font("Courier", Font.BOLD,32);
    private Font text = new Font("Courier", Font.BOLD,16);
    private HomePanel homePanel;
    private JLabel mainButtonLabel, updateInventoryPanelLabel;
    private JLabel secondButtonLabel ;
    private JLabel thirdButtonLabel;
    private JLabel fourthButtonLabel, fifthButtonLabel, sixthButtonLabel;    
    public mainFrame () //constructor 
    {
        buildContainer();
    }

    public void buildContainer() //method 
    {

        container = new JFrame("Chesney Fireplace");
        addItemPanel = AddItemPanel.getInstance();
        homePanel =  new HomePanel();        
        addSupplierPanel =  AddSupplierPanel.getInstance();
       // updateInventoryUI = updateInventoryPanel.getInstance();
        updateInventoryUI = new updateInventoryPanel();
        updateItemSupplierScreen = new UpdateItemSupplier();

        container.setVisible(true);
        container.setSize(1250, 650);// set the size of the container
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// make sure the programs closes if container closes 
        container.setResizable(false);
        container.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(142, 164, 237));
        // Panel to show the menu options
        secondPanel = new JPanel();
        secondPanel.setSize(300, 650);// set the size of the container
        secondPanel.setLayout(null);
        secondPanel.setBackground(new Color(255, 255, 255));

        homePanelButton = new JPanel();
        homePanelButton.setLayout(new GridBagLayout());
        homePanelButton.setBackground(new Color(255, 255, 255));
        mainButtonLabel = new JLabel("Home");
        mainButtonLabel.setFont(text);

        homePanelButton.setBounds(0, 40,300, 80);
        homePanelButton.setPreferredSize( new Dimension(300, 80) );

                // update inventory panel
        updateInventoryPanel = new JPanel();
        updateInventoryPanel.setLayout(new GridBagLayout());        
        updateInventoryPanel.setBackground(new Color(255, 255, 255));
        
        updateInventoryPanel.setBounds(0, 120,300, 80);
        updateInventoryPanel.setPreferredSize( new Dimension(300, 80) );
        updateInventoryPanelLabel = new JLabel("Update Inventory");
                mainButtonLabel.setFont(text);
        addNewItemPanelButton = new JPanel();
        addNewItemPanelButton.setLayout(new GridBagLayout());
        addNewItemPanelButton.setBackground(new Color(255, 255, 255));


        addNewItemPanelButton.setBounds(0, 200,300, 80);
        addNewItemPanelButton.setPreferredSize( new Dimension(300, 80) );
        secondButtonLabel = new JLabel("Add new item");
                secondButtonLabel.setFont(text);
        panelButtonThree = new JPanel();
        panelButtonThree.setLayout(new GridBagLayout());
        panelButtonThree.setBackground(new Color(255, 255, 255));

        panelButtonThree.setBounds(0, 280,300, 80);
        panelButtonThree.setPreferredSize( new Dimension(300, 80) );
        thirdButtonLabel = new JLabel("Add new supplier");
                thirdButtonLabel.setFont(text);       
        
        updateIteventorySupplierButton = new JPanel();
        updateIteventorySupplierButton.setLayout(new GridBagLayout());
        updateIteventorySupplierButton.setBackground(new Color(255, 255, 255));

        updateIteventorySupplierButton.setBounds(0, 360,300, 80);
        updateIteventorySupplierButton.setPreferredSize( new Dimension(300, 80) );
        sixthButtonLabel = new JLabel("Update Item Supplier");
                sixthButtonLabel.setFont(text);               
        
        
        panelButtonFour = new JPanel();
        panelButtonFour.setLayout(new GridBagLayout());        
        panelButtonFour.setBackground(new Color(255, 255, 255));

        panelButtonFour.setBounds(0, 440,300, 80);
        panelButtonFour.setPreferredSize( new Dimension(300, 80) );
        fourthButtonLabel = new JLabel("Exit");        

      

        
        /***
         *
         *try this new panel stuff
         */

        CardPanel = new JPanel();
        CardPanel.setBackground(Color.CYAN);
        CardPanel.setSize(700,700);
        CardPanel.setBounds(350, 40,850, 550);
        CardLayout layout = new CardLayout();

        CardPanel.setLayout(layout);
        CardPanel.add("Home", homePanel);        
        CardPanel.add("Item", addItemPanel);
        CardPanel.add("Supplier", addSupplierPanel);
        CardPanel.add("Update", updateInventoryUI);
        CardPanel.add("UpdateSupplierItem", updateItemSupplierScreen);


        homePanelButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                homePanelButton.setBackground(new Color(103, 58, 183));
                mainButtonLabel.setForeground(new Color(255,255,255));
            }

            public void mouseExited(MouseEvent e) {
                homePanelButton.setBackground(new Color(255, 255, 255 ));
                mainButtonLabel.setForeground(new Color(0,0,0));
            }

            public void mouseClicked(MouseEvent e){
                CardLayout cardLayout = (CardLayout)(CardPanel.getLayout());
                cardLayout.show(CardPanel,
                        "Home");

            }

        });

        
                updateInventoryPanel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                updateInventoryPanel.setBackground(new Color(103, 58, 183));
                updateInventoryPanelLabel.setForeground(new Color(255,255,255));
            }

            public void mouseExited(MouseEvent e) {
                updateInventoryPanel.setBackground(new Color(255, 255, 255 ));
                updateInventoryPanelLabel.setForeground(new Color(0,0,0));
            }

            public void mouseClicked(MouseEvent e){
                CardLayout cardLayout = (CardLayout)(CardPanel.getLayout());
                cardLayout.show(CardPanel,
                        "Update");

            }

        });

        addNewItemPanelButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                addNewItemPanelButton.setBackground(new Color(103, 58, 183));
                secondButtonLabel.setForeground(new Color (255,255,255));
            }

            public void mouseExited(MouseEvent e) {
                addNewItemPanelButton.setBackground(new Color(255, 255, 255 ));
                secondButtonLabel.setForeground(new Color(0,0,0));
            }

            public void mouseClicked(MouseEvent e){
                CardLayout cardLayout = (CardLayout)(CardPanel.getLayout());
                cardLayout.show(CardPanel,
                        "Item");
            }
        });

        panelButtonThree.addMouseListener(new MouseAdapter(){

            public void mouseEntered(MouseEvent e) {
                panelButtonThree.setBackground(new Color(103, 58, 183));
                thirdButtonLabel.setForeground(new Color (255,255,255));                
            }

            public void mouseExited(MouseEvent e) {
                panelButtonThree.setBackground(new Color(255, 255, 255 ));
                thirdButtonLabel.setForeground(new Color(0,0,0));                
            }
            
            public void mouseClicked(MouseEvent e){
                CardLayout cardLayout = (CardLayout)(CardPanel.getLayout());
                cardLayout.show(CardPanel,
                        "Supplier");
            }

        });
        
            updateIteventorySupplierButton.addMouseListener(new MouseAdapter(){

            public void mouseEntered(MouseEvent e) {
                updateIteventorySupplierButton.setBackground(new Color(103, 58, 183));
                sixthButtonLabel.setForeground(new Color (255,255,255));                
            }

            public void mouseExited(MouseEvent e) {
                updateIteventorySupplierButton.setBackground(new Color(255, 255, 255 ));
                sixthButtonLabel.setForeground(new Color(0,0,0));                
            }
            
            public void mouseClicked(MouseEvent e){
                CardLayout cardLayout = (CardLayout)(CardPanel.getLayout());
                cardLayout.show(CardPanel,
                        "UpdateSupplierItem");
            }

        });
        
       

        panelButtonFour.addMouseListener(new MouseAdapter(){

            public void mouseEntered(MouseEvent e) {
                panelButtonFour.setBackground(new Color(103, 58, 183));
                fourthButtonLabel.setForeground(new Color (255,255,255));                                
            }

            public void mouseExited(MouseEvent e) {
                panelButtonFour.setBackground(new Color(255, 255, 255 ));
                fourthButtonLabel.setForeground(new Color(0,0,0));                
            }

           public void mouseClicked(MouseEvent e){
                System.exit (0);
            }
        });
        //Conecting each of the features together. 
        mainPanel.add(CardPanel);
        mainPanel.add(secondPanel);
        secondPanel.add(homePanelButton);
        secondPanel.add(addNewItemPanelButton);
        secondPanel.add(panelButtonThree);
        secondPanel.add(panelButtonFour);
        secondPanel.add(updateInventoryPanel);
        secondPanel.add(updateIteventorySupplierButton);
        homePanelButton.add(mainButtonLabel);
        updateInventoryPanel.add(updateInventoryPanelLabel);        
        addNewItemPanelButton.add(secondButtonLabel);
        panelButtonThree.add(thirdButtonLabel);
        panelButtonFour.add(fourthButtonLabel);                        
        updateIteventorySupplierButton.add(sixthButtonLabel);    
         
        //  secondPanel.add(panelButtonTwo);
        //  secondPanel.add(panelButtonThree);
        //secondPanel.add(panelButtonFour);        

        container.add(mainPanel);
    }

    public static void main(String [] args){
        new mainFrame();
    }
}