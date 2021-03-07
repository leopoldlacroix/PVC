import java.util.ArrayList;

public class ResClub {

    private Chemin chemin;
    /**
     * 
     * @param carte
     * @param panel
     */
    public ResClub(Carte carte, Panel panel) {

        if(panel != null){
            panel.chemins.add(this.chemin);
            panel.fps = 5/ (double) (carte.nombre_de_villes - 3);
            System.out.println(panel.fps);
        }

        //definit la ville de depart et 
        ArrayList<Ville> villesRestantes = new ArrayList<Ville>(carte.villes);
        
        this.chemin = new Chemin().add(carte.villeDepart);

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
    
    public class sortByDistanceSpe implements Comparator <Ville> {

        private Ville villeRef;

        public sortByDistanceSpe(Ville ville){
            this.villeRef = ville;
        }

        public int compare(Ville ville1, Ville ville2) {

            int diff = (int) Math.ceil(ville1.distanceTo(this.villeRef) - ville2.distanceTo(this.villeRef));
            if(diff>0){
                return 1;
            }
            return -1;
        }
    }

}
