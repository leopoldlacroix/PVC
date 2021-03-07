import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    int tailleVillex = 5;
    int tailleVilley = 5;
    
    public long lastRepaintTime;
    public double fps;

    public Carte carte;
    public ArrayList<Chemin> chemins = new ArrayList<>();

    public int color = 0; 
    public String text = "0";

    Panel(Carte carte) {

        this.fps = 5/ (double) carte.nombre_de_villes;
        
        this.setPreferredSize(new Dimension(650,650));
        // this.setBackground(Color.black);

        this.carte = carte;
        this.lastRepaintTime = System.nanoTime();
        
    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);
        this.setBackground(Color.lightGray);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawString(this.text, this.carte.villeDepart.x, this.carte.villeDepart.y);

        g2D.setColor(Color.black);
        for (Ville ville : this.carte.villes) {
            g2D.drawString(""+ville.index, ville.x, ville.y);
            g2D.fillOval(ville.x - (int) (tailleVillex/2), ville.y - (int) (tailleVillex/2), tailleVillex, tailleVilley);
        }
        
        if(this.chemins != null){
            for (int i = 0; i< this.chemins.size(); i++){

                Chemin chemin = this.chemins.get(i);
                this.color += 100000;
                g2D.setColor(new Color(this.color));
                g2D.setStroke(new BasicStroke(this.chemins.size() - i));

                for (int j = 0; j < chemin.chemin.size()-1; j++) {
                    Ville ville_j1 = chemin.chemin.get(j);
                    Ville ville_j2 = chemin.chemin.get(j + 1);
                    
                    g2D.drawLine(ville_j1.x, ville_j1.y, ville_j2.x, ville_j2.y);
                }
            }
        }

        g2D.setColor(Color.red);
        g2D.drawOval(this.carte.villeDepart.x - (int) (tailleVillex/2), this.carte.villeDepart.y - (int) (tailleVillex/2), tailleVillex, tailleVilley);
        g2D.fillOval(this.carte.villeDepart.x - (int) (tailleVillex/2), this.carte.villeDepart.y - (int) (tailleVillex/2), tailleVillex, tailleVilley);
    }

    public void addChemin(Chemin chemin) {

        this.chemins.add(chemin);
        majPanel();
        
    }
    public void majPanel() {

        //intervalle de fps sec pour repeindre
        while(((System.nanoTime() - lastRepaintTime) / 1E9) < this.fps){
        }
        this.lastRepaintTime = System.nanoTime();
        this.repaint();
    }
}
