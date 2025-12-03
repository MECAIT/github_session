
package lab5;
import java.time.LocalDate;

/**
 *
 * @author 
 */
public class TextBook extends Book
{
    protected int numberOfPages;
    
    public TextBook(String title, LocalDate releaseDate, double price, int numberOfPages) 
    { 
        super(title, releaseDate, price);
        this.numberOfPages = numberOfPages;
    }
    
    @Override
    public String toString() {
    System.out.println("MethodID: TS11");
    return super.toString() + ", Pages: " + numberOfPages; }
    
    public int getPageCount() {
    return numberOfPages;
    }
}
