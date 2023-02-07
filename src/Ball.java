import java.awt.*;
import java.awt.Rectangle ;
import javax.swing.*;

public class Ball {
    private double xt; // position x de la balle à chaque instant
    private double yt; // position y de la balle à chaque instant
    private double x_initial; // position x initial de la balle
    private double y_initial; // position y initial de la balle
    private double thetha; // angle
    private final double vInitial = 300; // vitesse initiale
    private final double g = 9.81; // acceleration de la pesanteur
    private final String path = "ressources/ball2.png" ;
    private Rectangle rectBoard ;
    private Icon imageBall ;
    private JLabel labelImgBall ;
    private int widthBall = 45 ;
    private int radiusBall = widthBall / 2 ;
    private Board board ; 
    private boolean startBall = false ;

    public Ball(double x_initial, double y_initial, double thetha) {
        this.x_initial = x_initial;
        this.y_initial = y_initial;
        this.thetha = Math.toRadians(thetha) ;
        imageBall = new ImageIcon(this.getClass().getResource(path)) ;
        labelImgBall = new JLabel(imageBall) ;
        labelImgBall.setSize(75, 75);
        labelImgBall.setBounds((int)(x_initial + widthBall / 2) , (int)y_initial, widthBall, widthBall);
    }

    public JLabel getLabelImgBall() {
        return labelImgBall;
    }

    public void updateImgBall() {
       if (!startBall) {
            labelImgBall.setBounds((int)x_initial , (int)y_initial, widthBall, widthBall);
       }
       else {
            labelImgBall.setBounds((int)xt, (int)yt, widthBall, widthBall);
       }
      //labelImgBall.setBounds((int)xt, (int)yt, widthBall, widthBall);
    }

    public double XInitial() {
        return x_initial;
    }

    public double YInitial() {
        return y_initial;
    }

    public void setXInitial(double x_initial) {
        this.x_initial = x_initial;
    }

    public void setYInitial(double y_initial) {
        this.y_initial = y_initial;
    }

    public void setXt(double xt) {
        this.xt = xt;
    }

    public void setYt(double yt) {
        this.yt = yt;
    }

    public double getXt() {
        return xt;
    }

    public double getYt() {
        return yt;
    }

    public boolean isBallStart() {
        return this.startBall ;
    }

    public void setStartBall(boolean start){
        startBall = start ;
    }

    public void setTheta(double thetha) {
        this.thetha = thetha;
    }

    /* updating ball  */
    public void updateBall(double dt){
        updateCoordBall(dt);
    }

    // met à jour les coordonnées de la balle à l'instant dt
    public void updateCoordBall(double dt) {
        double xt_before = xt(dt) ;
        double yt_before = yt(dt) ;

        xt = xt_before ;
        yt = yt_before ;

        System.out.println("xt " + xt + " yt " + yt);

        // if ((xt_before + radiusBall > 0) && ((xt_before + radiusBall) < rectBoard.getWidth())) {
        //     xt = xt_before ;
        // }
        // else if ((xt_before + radiusBall) >= rectBoard.getWidth()) {
        //     xt = -xt_before ;
        // }
        // else if (xt_before + (widthBall / 2) <= 0){
        //     xt = -xt_before ;
        // }

        //yt = yt(dt);
    }

    // calcule la position x de la balle à l'instant dt passé en argumant
    private double xt(double dt) {
        //return vInitial * Math.sin(thetha) * dt + x_initial;
        return vInitial * dt + x_initial;
    }

    // calcule la position y de la balle à l'instant dt passé en argumant
    private double yt(double dt) {
        //return 0.5 * g * (dt * dt) + (vInitial * Math.cos(thetha) * dt) + y_initial ;
        return 0.5 * g * (dt * dt) + (vInitial * dt) + y_initial ;
    }

    public void drawBall(Graphics g){
        Graphics2D g2d = (Graphics2D)g ;
        g2d.drawOval((int)(xt + widthBall / 2) , (int)yt, 20, 20) ;
    }

    public void setRectBoard(Rectangle rect){
        this.rectBoard = rect ;
    }
}
