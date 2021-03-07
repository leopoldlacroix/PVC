import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        // Carte carte = Carte.imporCarte();
        Carte carte = new Carte(20, Carte.MIN, Carte.MAX);
        carte.save();

        Frame frame = new Frame(carte);
        
        ResNaive naive = new ResNaive(carte, null);
        frame.panel.addChemin(naive.resultat());

        
        ResBFS bfs = new ResBFS(carte, null);
        frame.panel.text = "" + bfs.statesChacked;
        frame.panel.addChemin(bfs.resultat());

    }

    public static void p(Chemin chemin) {
        p(chemin.chemin);
    }
    public static void p(String text) {
        System.out.println(text);
    }
    public static void p(boolean b) {
        System.out.println(b);
    }
    public static void p(int num){
        System.out.println(num);
    }
    public static void p(double num){
        System.out.println(num);
    }
    public static void p(float num){
        System.out.println(num);
    }
    public static void p(Ville ville){
        System.out.println(ville.index + ") x: " + ville.x + " y: " + ville.y);
    }
    public static void p(Carte carte){
        p(carte.villes);
    }
    public static void p(ArrayList<Ville> chemin){
        for(Integer i = 0; i < chemin.size() - 1; i++){
            Ville ville = chemin.get(i);
            System.out.print(ville.index + ", ");
        }
        Ville ville = chemin.get(chemin.size()-1);
        System.out.println(ville.index);
    }

    public static void pCoors(ArrayList<Ville> chemin){
        for(Integer i = 0; i < chemin.size(); i++){
            Ville ville = chemin.get(i);
            p(ville);
        }
    }

    public static int fact(int n){
        for (int i = 1; i < n; i++) {
            n*= i;
        }
        return n;
    }
}
