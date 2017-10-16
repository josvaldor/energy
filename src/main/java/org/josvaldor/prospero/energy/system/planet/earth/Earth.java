/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josvaldor.prospero.energy.system.planet.earth;

import org.josvaldor.prospero.energy.system.planet.Planet;
import java.awt.Color;
import java.util.Calendar;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author jorodriguez
 */
public class Earth extends Planet {
     public Earth(Calendar calendar) {
        super();
        this.name = "earth";
        this.mass = 5.9736e24;
        this.radius = 3443.9307;
        this.color = Color.BLUE;
        this.longitudeOfAscendingNode[0] = 0;//o
        this.longitudeOfAscendingNode[1] = 0;//o
        this.inclination[0] = 0;//i//0.00005
        this.inclination[1] = 0;//i//0.00005
        this.argumentOfPeriapsis[0] = -282.9404;
        this.argumentOfPeriapsis[1] = -4.70935E-5;
        this.semiMajorAxis[0] = 1.000000 * ASTRONOMICAL_UNIT;//a//1.00000011
        this.semiMajorAxis[1] = 0;
        this.eccentricity[0] = -0.016709;//e//0.01671022
        this.eccentricity[1] = 1.151E-9;//e
        this.meanAnomaly[0] = 356.0470;
        this.meanAnomaly[1] = 0.9856002585;
        this.orbitalPeriod = 365.256363004;
        this.time = calendar;
        this.position = this.getSpace(this.time);
        this.angularVelocity = 7.292115053925690e-05;
    }
     
    public String toString(){
        return "mass="+this.mass+" radius="+radius;
    }
}
