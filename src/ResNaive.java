import java.util.ArrayList;
import java.util.Comparator;

public class ResNaive {

    private Chemin chemin;

    public ResNaive(Carte carte, Panel panel) {
        
        this.chemin = new Chemin();
        if(panel != null){
            panel.chemins.add(this.chemin);
        }
        
        Ville villeDepart = carte.villeDepart;
        this.chemin.add(villeDepart);
        
        ArrayList<Ville> villesRestantes = new ArrayList<Ville>(carte.villes);

        while (villesRestantes.size() != 0) {

            //la ville ou on s'est arreté
            Ville villeActuelle = this.chemin.getLast();

            //tri la liste des villes restantes en fonction de la distance a la ville actuelle
            try {
                villesRestantes.sort(new sortByDistance(villeActuelle));
            } catch (Exception e) {
                
                villesRestantes.sort(new sortByDistanceSpe(villeActuelle));
                Main.pCoors(villesRestantes);
            }

            //ajoute la ville la plus proche et la supprime des villes restantes 
            this.chemin.add(villesRestantes.remove(0));

            if(panel != null){
                panel.majPanel();
            }
        }

        //finalise la construction
        this.chemin.end();
    }

    public Chemin resultat(){
        return chemin;
    }


    public class sortByDistance implements Comparator <Ville> {

        private Ville villeRef;

        public sortByDistance(Ville ville){
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
