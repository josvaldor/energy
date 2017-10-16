/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josvaldor.prospero.energy.system.star;

import org.josvaldor.prospero.energy.Spheroid;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorodriguez
 */
public class Star extends Spheroid {

 
    
    public Star() {
        this.name = "star";
    }
    

    public void draw(Graphics2D g) {
        g.setColor(this.color);
        double x = this.position.getX() * this.scale;
        double y = this.position.getY() * this.scale;
        double radius = 20;
        g.fillOval((int) (x - (radius / 2)), (int) (y - (radius / 2)), (int) radius, (int) radius);
        g.setColor(Color.WHITE);
//        g.drawString(this.getSunspot()+"", (int)x, (int) y);
        g.drawString(this.name.substring(0, 1).toUpperCase() + this.name.substring(1) + "", (int) x, (int) y);
    }
}
