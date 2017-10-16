/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josvaldor.prospero.energy.system.planet;

import org.josvaldor.prospero.energy.Spheroid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author jorodriguez
 */
public class Planet extends Spheroid {

    public Planet() {
        super();
    }

    public List<Vector3D> getOrbit() {
        LinkedList<Vector3D> vertexList = new LinkedList<Vector3D>();
        if (this.orbitalPeriod != 0) {
            int resolution = 100;
            double increment = this.orbitalPeriod / resolution;
            Calendar c = (Calendar) this.time.clone();
            double count = 0;
            while (count <= this.orbitalPeriod) {
                Vector3D position = this.getSpace(c);
                c.add(Calendar.DATE, (int) (Math.round(increment))); // number of days to ad
                count += increment;
                vertexList.add(position);
            }
        }
        return vertexList;
    }

    public void draw(Graphics2D g) {
        
        double x = this.position.getX() * scale;
        double y = this.position.getY() * scale;
        g.setColor(Color.yellow);
        g.drawLine((int) x, (int) y, (int)(this.force.getX()* scale), (int)(this.force.getY()* scale));
        g.setColor(this.color);
        double radius = 10;
        g.drawLine((int) x, (int) y, 0, 0);
        x = x - (radius / 2);
        y = y - (radius / 2);
        g.fillOval((int) x, (int) y, (int) radius, (int) radius);
        g.setColor(Color.WHITE);
        g.drawString(this.name.substring(0, 1).toUpperCase()+this.name.substring(1) + "", (int)x,(int)y);
        List<Vector3D> vertexList = this.getOrbit();
        g.setColor(Color.white);
        radius = 5;
        for (int i = 1; i < vertexList.size(); i++) {
            g.drawLine((int) (vertexList.get(i - 1).getX() * scale), (int) (vertexList.get(i - 1).getY() * scale),
                    (int) (vertexList.get(i).getX() * scale), (int) (vertexList.get(i).getY() * scale));
//			g.drawLine((int) (vertexList.get(i-1).getX() * scale), (int) (vertexList.get(i-1).getY() * scale),
//				(int) (vertexList.get(1).getX() * scale), (int) (vertexList.get(1).getY() * scale));
        }
        
        

    }
}
