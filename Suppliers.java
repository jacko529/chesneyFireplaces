
/**
 * Write a description of class Supplier here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Suppliers extends indentification
{

    // Attributes

    private String Name;
    private String PropertyNumber;
    private String FirstLine;
    private String Town;
    private String PostCode;
    private String Email;
    private String PhoneNumber;
    private String PointOfContact;

    

    /**
     * Constructor for objects of class Supplier
     */
    public Suppliers()
    {
        // initialise instance variables

        Name = null;
        PropertyNumber = null;
        FirstLine = null;
        Town = null;
        PostCode = null;
        Email = null;
        PhoneNumber = null;
        PointOfContact = null;
    }

    /**
     * 
     * Set {@see #PropertyNumber}. @param {@link #nameIn}. 
     */    
    public void setName(String nameIn)
    {
        this.Name = nameIn;
    }
    /**
     * 
     * Get {@see #Name}. @return {@link #Name}.  
     */
    public String getName()
    {
        return(this.Name);
    }
    /**
     * 
     * Set {@see #PropertyNumber}. @param {@link #propertyNumberIn}. 
     */
    public void setPropertyNumber(String propertyNumberIn)
    {
        this.PropertyNumber = propertyNumberIn;
    }
     /**
     * 
     * Get {@see #PropertyNumber}. @return {@link #PropertyNumber}.  
     */
    public String getPropertyNumber()
    {
        return(this.PropertyNumber);
    }
    /**
     * 
     * Set {@see #FirstLine}. @param {@link #firstLineIn}. 
     */
    public void setFirstLine(String firstLineIn)
    {
        this.FirstLine = firstLineIn;
    }
    /**
     * 
     * Get {@see #FirstLine}. @return {@link #FirstLine}.  
     */
    public String getFirstLine()
    {
        return(this.FirstLine);
    }
    public void setTown(String townIn)
    {
        this.Town = townIn;
    }
     /**
     * 
     * Get {@see #Town}. @return {@link #Town}.  
     */
    public String getTown()
    {
        return(this.Town);
    }
     /**
     * 
     * Set {@see #PostCode}. @param {@link #postCodeIn}. 
     */
    public void setPostCode(String postCodeIn)
    {
        this.PostCode = postCodeIn;
    }
      /**
     * 
     * Get {@see #PostCode}. @return {@link #PostCode}.  
     */
    public String getPostCode()
    {
        return(this.PostCode);
    }
     /**
     * 
     * Set {@see #Email}. @param {@link #emailIn}. 
     */    
    public void setEmail(String emailIn)
    {
        this.Email = emailIn;
    }
      /**
     * 
     * Get {@see #Email}. @return {@link #Email}.  
     */
    public String getEmail()
    {
        return(this.Email);
    }
     /**
     * 
     * Set {@see #PhoneNumber}. @param {@link #PhoneNumberIn}. 
     */      
    public void setPhoneNumber(String PhoneNumberIn)
    {
        this.PhoneNumber = PhoneNumberIn;
    }
      /**
     * 
     * Get {@see #PhoneNumber}. @return {@link #PhoneNumber}.  
     */
    public String getPhoneNumber()
    {
        return(this.PhoneNumber);
    }
     /**
     * 
     * Set {@see #PointOfContact}. @param {@link #PointOfContactIn}. 
     */      
    public void setPointOfContact(String PointOfContactIn)
    {
        this.PointOfContact = PointOfContactIn;
    }
     /**
     * 
     * Get {@see #PointOfContact}. @return {@link #PointOfContact}.  
     */    
    public String getPointOfContact()
    {
        return(this.PointOfContact);
    }
    
    
    
    
}// end of class
