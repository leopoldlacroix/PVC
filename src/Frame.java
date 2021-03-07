import javax.swing.*;

public class Frame extends JFrame{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    
    public Panel panel;

    // creation de la frame
    public Frame(Carte carte){
        
        //modif du frame
        this.setTitle("PVC");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon icon = new ImageIcon("C:/Users/leopo/Desktop/pictercy_/logo.png");
        this.setIconImage(icon.getImage());

        panel = new Panel(carte);
        this.add(panel);
        this.pack();
        
        this.setVisible(true);   
    }
}
