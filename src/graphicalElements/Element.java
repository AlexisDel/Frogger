package graphicalElements;

import java.awt.*;

import util.Case;

import javax.swing.*;


public class Element extends Case {
    public Color color;
    public Image image;
    public boolean isImage;

    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
        this.isImage = false;
    }
    
    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
        this.isImage = false;
    }

    public Element(Case c, Image image){
        super(c.absc, c.ord);
        this.image = image;
        this.image = this.image.getScaledInstance(FroggerGraphic.pixelByCase, FroggerGraphic.pixelByCase, Image.SCALE_SMOOTH);
        this.isImage = true;

    }
    
}
