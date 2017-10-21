/*
f * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josvaldor.prospero.energy.system.moon.luna;

import org.josvaldor.prospero.energy.Unit;
import org.josvaldor.prospero.energy.system.moon.*;

import java.awt.Color;

/**
 *
 * @author jorodriguez
 */
public class Luna extends Moon{
    public String name;
    public Luna(){
        this.name = "Luna";
        this.mass = 7.3477e22;
        this.radius = 1738.1;
        this.color = Color.white;
        this.longitudeOfAscendingNode[0] = 125.1228;//o
        this.longitudeOfAscendingNode[1] = -0.0529538083;//o
        this.inclination[0] = 5.1454;//i//0.00005
        this.inclination[1] = 0;//i//0.00005
        this.argumentOfPeriapsis[0] = 318.0634;
        this.argumentOfPeriapsis[1] = 0.1643573223;
        this.semiMajorAxis[0] = 60.2666 * Unit.ASTRONOMICAL;//a//1.00000011
        this.semiMajorAxis[1] = 0;
        this.eccentricity[0] = 0.054900;//e//0.01671022
        this.eccentricity[1] = 0;//e
        this.meanAnomaly[0] = 115.3654;
        this.meanAnomaly[1] = 13.0649929509;
        this.position = this.getSpace(this.time).eliptic;
    }
}
