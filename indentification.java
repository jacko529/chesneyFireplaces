
/**
 * Write a description of class indentification here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class indentification implements Identification
{
    // instance variables - replace the example below with your own
    private int SupplierId;

    /**
     * Constructor for objects of class indentification
     */
    public indentification()
    {
        SupplierId = 0;
    }

     /**
     * 
     * Set {@see #SupplierId}. @param {@link #SupplierIdIn}. 
     */    
    public void setSupplierId(int SupplierIdIn)
    {
        this.SupplierId = SupplierIdIn;
    }
    /**
     * 
     * Get {@see #SupplierId}. @return {@link #SupplierId}.  
     */
    public int getSupplierId()
    {
        return(this.SupplierId);
    }
}
