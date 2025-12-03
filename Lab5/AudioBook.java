
package lab5;
import java.time.LocalDate;
/**
 *
 * @author 
 */
public class AudioBook extends Book 
{
    protected double lengthInMinutes;
    
    public AudioBook(String title, LocalDate releaseDate, double price, double lengthInMinutes) 
    {
        super(title, releaseDate, price);
        this.lengthInMinutes = lengthInMinutes;
    }
    
    @Override
    public String toString() 
    {
        System.out.println("MethodID: TS12");
        return super.toString() + ", Length: " + lengthInMinutes + " minutes";
    }
    
    public double getLength() 
    {
        return lengthInMinutes;
    }
}
