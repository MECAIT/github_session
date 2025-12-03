
package lab5;
import java.time.LocalDate;
/**
 *
 * @author 
 */
public class Book {
    private static int idCounter = 1; 
    protected int id;
    protected String title;
    protected LocalDate releaseDate;
    protected double price;
    
    public Book(String title, LocalDate releaseDate, double price) {
        this.id = idCounter++;
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        }
    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Release Date: " + releaseDate + ", Price: $" + price;
    }
    
    public void displayInfo() {
        System.out.println("Book Information:");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Release Date: " + releaseDate); System.out.println("Price: $" + price);
        System.out.println("------------------------"); }
}
