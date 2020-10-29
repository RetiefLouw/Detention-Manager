package Controllers;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameController {

    private static final ImageIcon img = new ImageIcon("myIcon.png");

    public static void setLayout(JFrame toSetLayout) {
        toSetLayout.setIconImage(img.getImage());
        toSetLayout.setTitle("Detention Manager");
        toSetLayout.setLocationRelativeTo(null);
        toSetLayout.setVisible(true);
    }

}
