import java.util.ArrayList;
import java.util.Comparator;

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
        
        //on demarre avec un chemin petit "opti"
        this.chemin = new Chemin().add(carte.villeDepart);
        this.chemin.add(villesRestantes.remove(0));
        this.chemin.add(villesRestantes.remove(0));
        this.chemin.end();

        //on rajoute les villes petit a petit en les inserants au meilleur endroit
        while(villesRestantes.size() != carte.nombre_de_villes + 1){

            if(panel != null){
                panel.chemins.set(0, this.chemin);
                panel.majPanel();
            }

            ArrayList<Chemin> nextChemins = new ArrayList<Chemin>();

            for (Ville ville : villesRestantes) {
                Chemin nextChemin = bestInsert(ville, this.chemin);
                nextChemins.add(nextChemin);
            }

            nextChemins.sort(new sortByDistance());
            this.chemin = nextChemins.get(0);
            Ville villeAdded = this.chemin.get(-2);
            villesRestantes.remove(villeAdded);

        }

        if(panel != null){
            panel.chemins.set(0, this.chemin);
            panel.majPanel();
        }

    }

    /**
     * finds the best place to insert villle in chemin
     * @param ville
     * @param chemin
     * @return
     */
    private Chemin bestInsert(Ville ville, Chemin chemin) {
        ArrayList<Chemin> potentialPaths = new ArrayList<Chemin>();
        for (int i = 1; i < chemin.size(); i++) {
            Chemin potentialPath = new Chemin(chemin);
            potentialPath.chemin.add(i, ville);
            potentialPaths.add(potentialPath);
        }
        potentialPaths.sort(new sortByDistance());
        return potentialPaths.get(0);
    }

    public Chemin resultat() {
        return chemin;
    }

    /**
     * sort paths by distance
     */
    public class sortByDistance implements Comparator <Chemin> {

        public int compare(Chemin chemin1, Chemin chemin2) {

            int diff = (int) Math.ceil(chemin1.distance() - chemin2.distance());

            if(diff>0){
                return 1;
            }
            return -1;
        }
    }

}
