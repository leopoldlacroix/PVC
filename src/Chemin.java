import java.util.ArrayList;

public class Chemin {
    
    public ArrayList<Ville> chemin;
    
    public Chemin(){
        this.chemin = new ArrayList<Ville>();    
    }

    public Chemin(ArrayList<Ville> chemin){
        this.chemin = new ArrayList<Ville>(chemin);

    }
    
    //copy
    public Chemin(Chemin another) {
        this.chemin = new ArrayList<Ville>(another.chemin); 
    }

    // distance tot d'un chemin
    public double distance() {
        double distance = 0;
        for (Integer i = 0; i < this.chemin.size() - 1; i++) {

            distance += this.chemin.get(i).distanceTo(this.chemin.get(i+1));
        }
        return distance;
    }

    //ajoute ville au chemin
    public Chemin add(Ville ville){
        this.chemin.add(ville);
        return this;
    }

    //dernier element
    public Ville getLast(){
        return this.chemin.get(this.chemin.size() - 1);
    }

	public Chemin end() {
		this.chemin.add(this.chemin.get(0));
        return this;
	}


    public int size() {
        return this.chemin.size();
	}

}
