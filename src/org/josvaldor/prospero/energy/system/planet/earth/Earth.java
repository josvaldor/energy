package org.josvaldor.prospero.energy.system.planet.earth;

import org.josvaldor.prospero.energy.Unit;
import org.josvaldor.prospero.energy.system.planet.Planet;
import java.awt.Color;
import java.util.Calendar;

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
        this.longitudeOfAscendingNode[0] = 0;
        this.longitudeOfAscendingNode[1] = 0;
        this.inclination[0] = 0;
        this.inclination[1] = 0;
        this.argumentOfPeriapsis[0] = -282.9404;
        this.argumentOfPeriapsis[1] = -4.70935E-5;
        this.semiMajorAxis[0] = 1.000000 * Unit.ASTRONOMICAL;
        this.semiMajorAxis[1] = 0;
        this.eccentricity[0] = -0.016709;
        this.eccentricity[1] = 1.151E-9;
        this.meanAnomaly[0] = 356.0470;
        this.meanAnomaly[1] = 0.9856002585;
        this.orbitalPeriod = 365.256363004;
        this.time = calendar;
        this.position = this.getSpace(this.time).eliptic;
        this.angularVelocity = 7.292115053925690e-05;
        this.obliquity = 23.439292;
    }
}
