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
            imageBasic = ImageIO.read(new File("ressources/cannon.png"));
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
        // jlabel.setSize(200, 200);
        // jlabel.setBounds((int) orbX, (int) orbY, (int) 200, (int) 200);
    }

    void radianChanged(double r, Graphics g) {
        radian = r;
        canonX = orbitX + orbitRayon * Math.cos(radian) - (3. / 4.) * orbitRayon;
        canonY = orbitY - orbitRayon * Math.sin(radian);
        drawCanon(canonX, canonY, 50, g);
        rotateCanon(r, (Graphics2D) g);
    }

    private void drawCanon(double drawX, double drawY, double sphereRadius, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval((int) drawX, (int) drawY, (int) sphereRadius, (int) sphereRadius);
    }

    public void setOrbX(double d) {
        System.out.println(d / 2 + "position orbit canon");
        orbitX = d / 2;
        jlabel.setBounds((int) (orbitX - (3. / 4.) * orbitRayon - orbitRayon / 2), (int) (orbitY - orbitRayon / 2),
                (int) orbitRayon * 2,
                (int) orbitRayon * 2);
    }

    public void rotateCanon(double radian, Graphics2D g2d) {
        final double rads = -radian - Math.PI / 2;
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
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

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle, Graphics2D g2d) {
        double rads = angle;
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        ((Graphics) g2d).drawImage(img, 100, 100, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
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

    public double getCanonX() {
        return canonX;
    }

    public double getCanonY() {
        return canonY;
    }

}
