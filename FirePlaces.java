
/**
 * Write a description of class FirePlaces here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FirePlaces extends indentification
{
    // instance variables - replace the example below with your own
    private int ItemCode;
    private String Description;
    private double UnitPrice;
    private int QuantityInStock;
    private String Style;
    private String Finish;
    

    /**
     * Constructor for objects of class FirePlaces
     */
    public FirePlaces()
    {
        // initialise instance variables
        ItemCode = 0;
        Description = null;
        UnitPrice = 0.0;
        QuantityInStock = 0;
        Style = null;
        Finish = null;
    }
     /**
     * 
     * Set {@see #ItemCode}. @param {@link #ItemCode}. 
     */      
    public void setItemCode(int ItemCodeIn)
    {
        this.ItemCode = ItemCodeIn;
    }
     /**
     * 
     * Get {@see #ItemCode}. @return {@link #ItemCode}.  
     */    
    public int getItemCode()
    {
        return(this.ItemCode);
    }
     /**
     * 
     * Set {@see #Description}. @param {@link #DescriptionIn}. 
     */      
    public void setDescription(String DescriptionIn)
    {
        this.Description = DescriptionIn;
    }
     /**
     * 
     * Get {@see #Description}. @return {@link #Description}.  
     */    
    public String getDescription()
    {
        return(this.Description);
    }
    
     /**
     * 
     * Set {@see #UnitPrice}. @param {@link #UnitPrice}. 
     */      
    public void setUnitPrice(double UnitPriceIn)
    {
        this.UnitPrice = UnitPriceIn;
    }
     /**
     * 
     * Get {@see #UnitPrice}. @return {@link #UnitPrice}.  
     */    
    public Double getUnitPrice()
    {
        return(this.UnitPrice);
    }
     /**
     * 
     * Set {@see #QuantityInStock}. @param {@link #QuantityInStock}. 
     */      
    public void setQuantityInStock(int QuantityInStockIn)
    {
        this.QuantityInStock = QuantityInStockIn;
    }
     /**
     * 
     * Get {@see #QuantityInStock}. @return {@link #QuantityInStock}.  
     */    
    public int getQuantityInStock()
    {
        return(this.QuantityInStock);
    }
     /**
     * 
     * Set {@see #Style}. @param {@link #Style}. 
     */      
    public void setStyle(String StyleIn)
    {
        this.Style = StyleIn;
    }
     /**
     * 
     * Get {@see #Style}. @return {@link #Style}.  
     */    
    public String getStyle()
    {
        return(this.Style);
    }
     /**
     * 
     * Set {@see #Finish}. @param {@link #FinishIn}. 
     */      
    public void setFinish(String FinishIn)
    {
        this.Finish = FinishIn;
    }
     /**
     * 
     * Get {@see #Finish}. @return {@link #Finish}.  
     */    
    public String getFinish()
    {
        return(this.Finish);
    }    
    
    
}
