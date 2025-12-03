
package lab5;
import java.time.LocalDate;
/**
 *
 * @author 
 */
public class ScienceBook extends TextBook 
{
    private String field;
    private String difficultyLevel;
    
    public ScienceBook(String title, LocalDate releaseDate, double price, int numberOfPages, String field, String difficultyLevel) 
    {
        super(title, releaseDate, price, numberOfPages);
        this.field = field;
        this.difficultyLevel = difficultyLevel;
    }
    
    @Override
    public void displayInfo() 
    {
        System.out.println("MethodID: DI22");
        super.displayInfo();
        System.out.println("Subtype: ScienceBook");
        System.out.println("Field: " + field);
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("========================");
    }
    public String getField() 
    {
        return field;
    }
}
