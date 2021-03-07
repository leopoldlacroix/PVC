import java.util.ArrayList;

//represente un etat pour la construction du chemin
public class State {
    Double score;
    Chemin chemin;
    ArrayList<Ville> villesRestantes;
    public boolean isGoal;
    
    /**
     * score of state = chemin.distanc()
     * @param chemin
     * @param villesRestantes
     */
    public State(Chemin chemin, ArrayList<Ville> villesRestantes){

        this.chemin = chemin;
        this.score = this.chemin.distance();
        this.villesRestantes = villesRestantes;
    }
    
    //makes copy
    public State (State another){
        this.chemin = new Chemin(another.chemin);
        this.villesRestantes = new ArrayList<Ville>(another.villesRestantes);
    }

    /**
     * ajoute la ville au chemin et la retire de celles restantes et maj score
     * @param ville
     * @return
     */
    public State forward(Ville ville){
        this.chemin.add(ville);
        this.villesRestantes.remove(ville);
        this.score = this.chemin.distance();
        return this;
    }

    public State end(){
        this.chemin.end();
        this.score = this.chemin.distance();
        this.isGoal = true;
        return this;
    }
    
}
