/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josvaldor.prospero.energy.system.moon;

import org.josvaldor.prospero.energy.Spheroid;

import java.awt.Graphics2D;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author jorodriguez
 */
public class Moon extends Spheroid {
    public String name;
    public Moon(){
        this.name = "moon";
    }
    
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		double x = this.position.getX() * this.scale;
		double y = this.position.getY() * this.scale;
		double radius = 5;
		x = x - (radius / 2);
		y = y - (radius / 2);
		g.fillOval((int)x, (int)y, (int) radius, (int) radius);
	}
}
