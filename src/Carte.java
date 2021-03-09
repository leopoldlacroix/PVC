import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Carte implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String file = "Backup/Carte.bin";


    public static int count = 0;    // a remmettre a zero si plus de deux appelés

    public static int MIN = 0;      // min absisse/ordonnee des villes
    public static int MAX = 650;    // max absisse/ordonnee des villes

    public int nombre_de_villes;

    public Ville villeDepart;       // ville de depart
    public ArrayList<Ville> villes;

    // creer n villes
    public Carte(int n, int min, int max) {

        this.nombre_de_villes = n;

        //ville de depart
        this.villeDepart = new Ville(min, max);
        Ville.count = 1;

        this.villes = new ArrayList<Ville>();
        for (Integer i = 1; i < n; i++) {
            this.villes.add(new Ville(min, max));
        }
    }

    public boolean save(){

        try {
            FileOutputStream fos = new FileOutputStream(Carte.file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erreur de sauvergarde!");
            return false;
        }

        System.out.println("saved with succes");
        return true;
    }

    public static Carte importCarte(){

        try {
            FileInputStream fis = new FileInputStream(Carte.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Carte carte = (Carte) ois.readObject();
            ois.close();
            System.out.println("import succeded");
            return carte;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("erreur d'import!");
            return null;
        }

    }

}