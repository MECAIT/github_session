
package lab5;
import java.time.LocalDate;
/**
 *
 * @author 
 */
public class Podcast extends AudioBook {
    private String host;
    private int episodeNumber;
    
    public Podcast(String title, LocalDate releaseDate, double price, double lengthInMinutes, String host, int episodeNumber) 
    {
        super(title, releaseDate, price, lengthInMinutes); this.host = host;
        this.episodeNumber = episodeNumber; 
    }
    
    @Override
    public void displayInfo() 
    {
        System.out.println("MethodID: DI23");super.displayInfo();        
        System.out.println("Subtype: Podcast");
        System.out.println("Host: " + host);
        System.out.println("Episode Number: " + episodeNumber); System.out.println("========================");
        }
    
    public String getHost() {
        return host;
    }
    
    public int getEpisodeNumber() { 
        return episodeNumber;
    }
}
