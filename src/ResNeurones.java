import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Jama.Matrix;
public class ResNeurones implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static String file = "Backup/Neurones.bin";
    private Matrix O0;
    private Matrix O1;

    
    public ResNeurones(Carte carte){
        this.O0 = Matrix.random(3,4);//2(3) (entry 1,x,y) => 4
        this.compute(carte);

        this.save();

    }

    public int compute(Carte carte){
        
        //contruction de la matrice d'entrée
        double[][] entry = new double[carte.nombre_de_villes][3];
        entry[0][0] = 1;
        entry[0][1] = carte.villeDepart.x/(double) Carte.MAX; //val entre 0 et 1
        entry[0][2] = carte.villeDepart.y/(double) Carte.MAX;
        for (Ville ville : carte.villes) {
            entry[ville.index][0] = 1;
            entry[ville.index][1] = ville.x/(double) Carte.MAX;
            entry[ville.index][2] = ville.y/(double) Carte.MAX;
        }

        Matrix a0 = new Matrix(entry);

        System.out.println("O0: ");
        this.O0.print(3,4);
        System.out.println("--");

        System.out.println("a0: ");
        a0.print(carte.nombre_de_villes, 3);
        System.out.println("--");

        Matrix x1 = a0.times(this.O0);

        System.out.println("x1");
        x1.print(x1.getRowDimension(), x1.getColumnDimension());
        System.out.println("--");

        return 1;

    }

    public boolean save(){

        try {
            FileOutputStream fos = new FileOutputStream(ResNeurones.file);
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

    public static ResNeurones importNeurones(){

        try {
            FileInputStream fis = new FileInputStream(ResNeurones.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ResNeurones neurones = (ResNeurones) ois.readObject();
            ois.close();
            System.out.println("import succeded");
            return neurones;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("erreur d'import!");
            return null;
        }

    }

    private static Matrix sigmoid(Matrix x){
        double[][] copy = x.getArrayCopy();
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j] = 1 / (1 + Math.exp(-copy[i][j]));
            }
        }

        Matrix y = new Matrix(copy);
        return y;
    }

}
