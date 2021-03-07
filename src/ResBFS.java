import java.util.ArrayList;
import java.util.Comparator;

public class ResBFS {

    private Chemin chemin;
    public int statesChacked = 0;

    /**
     * 
     * @param carte
     * @param panel
     */
    public ResBFS(Carte carte, Panel panel) {

        if(panel != null){
            panel.chemins.add(this.chemin);
            panel.fps = 5/ (double) Main.fact(carte.nombre_de_villes);
            System.out.println(panel.fps);
        }

        //depart
        Chemin startChemin = new Chemin().add(carte.villeDepart);
        ArrayList<Ville> startVillesRestantes = new ArrayList<Ville>(carte.villes);
        
        State startState = new State(startChemin,startVillesRestantes);

        //debut boucle
        ArrayList<State> nextStates = new ArrayList<State>();
        nextStates.add(startState);
        
        State state;
        do {
            this.statesChacked++;

            // le state suivant dont la distance et la plus petite
            nextStates.sort(new sortState());
            state = nextStates.remove(0);

            if(panel != null){
                panel.chemins.set(0, state.chemin);
                panel.text = ""+this.statesChacked;
                panel.majPanel();
            }

            //recupere tous les etats possibles suivants
            for (Ville ville : state.villesRestantes) {
                State nextState = getNextState(state,ville);
                nextStates.add(nextState);
            }
            
            //s'il n'y a plus de villes restante il faut tester la boucle pour la rajouter dans la liste
            if(state.villesRestantes.size() == 0){
                
                State nextState = new State(state).end();
                nextStates.add(nextState);
            }
        } while (!state.isGoal);

        this.chemin = state.chemin;

        if(panel != null){
            panel.chemins.set(0, this.chemin);
            panel.majPanel();
        }

    }

    private State getNextState(State state, Ville ville) {
        State stateCopy = new State(state);
        stateCopy.forward(ville);
        return stateCopy;
    }

    public Chemin resultat() {
        return chemin;
    }
    
    public class sortState implements Comparator <State> {

        public int compare(State state1, State state2) {

            int diff = (int) Math.ceil(state1.score - state2.score);

            if(diff>0){
                return 1;
            }
            return -1;
        }
    }

}
