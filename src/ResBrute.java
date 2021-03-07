import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResBrute {
    
    private Chemin chemin;

    public ResBrute(Carte carte) {

        Ville villeDepart = carte.villeDepart;
        ArrayList<Ville> autresVilles = new ArrayList<Ville>(carte.villes);

        //touts les arrangement des autre villes
        ArrayList<ArrayList<Ville>> tous = getAllPermutations(autresVilles);

        // transformation des arrangements en chemins en ajoutanat la ville de depart
        ArrayList<Chemin> chemins = new ArrayList<Chemin>();
        for (ArrayList<Ville> villes : tous) {
            villes.add(0, villeDepart);
            Chemin chemin = new Chemin(villes).end();
            chemins.add(chemin);
        }

        //tri en fonction de la distance totale des chemins
        chemins.sort(new sortByDistance());

        //chemin vainqueur: celui dont la distance est la plus petite
        this.chemin = chemins.get(0);
    }

    public Chemin resultat(){
        return chemin;
    }

    //donne tous les chemins possible parmis les villes
    public static ArrayList<ArrayList<Ville>> getAllPermutations(ArrayList<Ville> villes) {
        ArrayList<ArrayList<Ville>> allPermutations = new ArrayList<ArrayList<Ville>>();

        //si qu'une seule ville on renvoie une seule liste contenant la ville
        if (villes.size() == 1) {
            
            allPermutations.add(new ArrayList<Ville>(villes));
            return allPermutations;
        }

        // si 2 villes on renvoie deux listes avec les deux arrangements possibles
        if (villes.size() == 2) {
            allPermutations.add(new ArrayList<Ville>(villes));

            ArrayList<Ville> villesCopy = new ArrayList<Ville>(villes);
            Collections.reverse(villesCopy);
            allPermutations.add(villesCopy);
            return allPermutations;
        }

        //on vient recuperer chaque ville pour la mettre en premier et construire le reste de la liste
        for (int i = 0; i < villes.size(); i++) {

            Ville ville = villes.get(i);

            //liste des autres villes
            ArrayList<Ville> others = new ArrayList<Ville>();
            others.addAll(villes.subList(0, i));
            others.addAll(villes.subList(i+1, villes.size()));

            //les arrangements possibles des autres villes
            ArrayList<ArrayList<Ville>> permutationsOthers = new ArrayList<ArrayList<Ville>>(getAllPermutations(others));

            //on ajoute le ville que l'on avait retenue à chaque arrangement
            for (ArrayList<Ville> permutation : permutationsOthers) {
                permutation.add(0, ville);
            }
            
            
            // on ajoute toutes permutations construites avec la ville i en 1er
            allPermutations.addAll(permutationsOthers);
        }

        return allPermutations;

    }

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
