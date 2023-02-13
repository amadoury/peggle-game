import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Canon {

    private double orbitX;
    private double orbitY;
    private double orbitRayon;
    private double radian = -Math.PI / 2;
    private double canonX;
    private double canonY;
    private JLabel jlabel;
    private BufferedImage imageBasic;
    private BufferedImage image;

    Canon(double orbX, double orbY, double orbR) {
        orbitX = orbX;
        orbitY = orbY;
        orbitRayon = orbR;
        canonX = orbitX + orbitRayon * Math.cos(radian);
        canonY = orbitY - (orbitRayon * Math.sin(radian));
        try {
            imageBasic = ImageIO.read(new File("src/ressources/cannon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = imageBasic;
        ImageIcon imageIcon = new ImageIcon(imageBasic);
        Image im = imageIcon.getImage(); // transform it
        Image newimg = im.getScaledInstance((int) orbR * 2, (int) orbR * 2, java.awt.Image.SCALE_SMOOTH); // scale it
        // the
        // smooth
        // way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel = new JLabel(imageIcon);
    }

    void radianChanged(double r, Graphics g) {
        radian = r;
        canonX = orbitX + orbitRayon * Math.cos(radian);// - (3. / 4.) * orbitRayon
        canonY = orbitY - orbitRayon * Math.sin(radian);
        rotateCanon(r, (Graphics2D) g);
    }

    public void setOrbX(double d) {// - (3. / 4.) * orbitRayon
        orbitX = d / 2;
        jlabel.setBounds((int) (orbitX - orbitRayon), (int) (orbitY - orbitRayon),
                (int) orbitRayon * 2,
                (int) orbitRayon * 2);
    }

    public void rotateCanon(double radian, Graphics2D g2d) {
        final double rads = -radian - Math.PI / 2;
        // final double sin = Math.abs(Math.sin(rads));
        // final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(imageBasic.getWidth());
        final int h = (int) Math.floor(imageBasic.getHeight());
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads, 0, 0);
        at.translate(-imageBasic.getWidth() / 2, -imageBasic.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(imageBasic, rotatedImage);
        image = rotatedImage;
        Image newimg = image.getScaledInstance((int) orbitRayon * 2, (int) orbitRayon * 2, java.awt.Image.SCALE_SMOOTH); // scale
        // it
        // the
        // smooth way
        ImageIcon imageIcon = new ImageIcon(newimg); // transform it back
        jlabel.setIcon(imageIcon);

    }

    public JLabel getJlabel() {
        return jlabel;
    }

    public double getCanonX() {
        return canonX;
    }

    public double getCanonY() {
        return canonY;
    }

}
