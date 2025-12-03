
package lab5;
import java.time.LocalDate;
/**
 *
 * @author
 */
public class FictionBook extends TextBook 
{ 
    private String genre;
    private String author;public FictionBook(String title, LocalDate releaseDate, double price, int numberOfPages, String genre, String author) 
    {
        super(title, releaseDate, price, numberOfPages);
        this.genre = genre;
        this.author = author;
    }
    @Override
    public void displayInfo() 
    {
        System.out.println("MethodID: DI21");
        super.displayInfo();
        System.out.println("Subtype: FictionBook");
        System.out.println("Genre: " + genre);
        System.out.println("Author: " + author);
        System.out.println("========================");
    }
    public String getGenre() {
    return genre;
    }
}
