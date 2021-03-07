import java.io.Serializable;
import java.util.Random;

public class Ville implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public static int count = 0; //nombre de villes créées
    public int index;
    public int x;
    public int y;

    public Ville(int min, int max){

        Random random = new Random();
        this.x = random.nextInt(max + 1 - min) + min;
        this.y = random.nextInt(max + 1 - min) + min;
        this.index = Ville.count;
        Ville.count += 1;
    }

    public double distanceTo(Ville ville) {

        // ((x1 - x2)^2 + (y1 - y2)^2)^0.5
        return Math.hypot(ville.x - this.x, ville.y - this.y); 
    }
}