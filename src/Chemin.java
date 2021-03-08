import java.util.ArrayList;

public class Chemin {

    public ArrayList<Ville> chemin;
    public Ville lastAdded;

    /**
     * attributs: chemin, last added
     */
    public Chemin() {
        this.chemin = new ArrayList<Ville>();
    }

    public Chemin(ArrayList<Ville> chemin) {
        this.chemin = new ArrayList<Ville>(chemin);

    }

    // copy
    public Chemin(Chemin another) {
        this.chemin = new ArrayList<Ville>(another.chemin);
    }

    // distance tot d'un chemin
    public double distance() {
        double distance = 0;
        for (Integer i = 0; i < this.chemin.size() - 1; i++) {

            distance += this.chemin.get(i).distanceTo(this.chemin.get(i + 1));
        }
        return distance;
    }

    /**
     * 
     * @param index
     * @return element at index of chemin
     */
    public Ville get(int index) {
        index = (this.chemin.size() + index) % this.chemin.size();
        return this.chemin.get(index);
    }

    /**
     * add at index
     * 
     * @param index
     * @param ville
     * @return
     */
    public Chemin add(int index, Ville ville) {
        index = (this.chemin.size() + index) % Math.max(this.chemin.size(), 1);
        this.chemin.add(index, ville);
        this.lastAdded = ville;
        return this;
    }

    /**
     * boucle
     * 
     * @return
     */
    public Chemin end() {
        this.chemin.add(this.chemin.get(0));
        return this;
    }

    public int size() {
        return this.chemin.size();
    }

}
