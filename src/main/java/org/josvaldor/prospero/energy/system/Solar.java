/*
 * 
 */
package org.josvaldor.prospero.energy.system;

import org.josvaldor.prospero.energy.Energy;
import org.josvaldor.prospero.energy.Triangle;
import org.josvaldor.prospero.energy.system.moon.luna.Luna;
import org.josvaldor.prospero.energy.system.planet.earth.Earth;
import org.josvaldor.prospero.energy.system.planet.jupiter.Jupiter;
import org.josvaldor.prospero.energy.system.planet.mars.Mars;
import org.josvaldor.prospero.energy.system.planet.mercury.Mercury;
import org.josvaldor.prospero.energy.system.planet.neptune.Neptune;
import org.josvaldor.prospero.energy.system.planet.saturn.Saturn;
import org.josvaldor.prospero.energy.system.planet.uranus.Uranus;
import org.josvaldor.prospero.energy.system.planet.venus.Venus;
import org.josvaldor.prospero.energy.system.star.sun.Sun;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Solar extends Energy {

	public ArrayList<Energy> energyList;
	public Map<String, Energy> energyMap = new HashMap<String, Energy>();
	public Earth earth;
	public Jupiter jupiter;
	public Mars mars;
	public Mercury mercury;
	public Neptune neptune;
	public Saturn saturn;
	public Venus venus;
	public Uranus uranus;
	public Sun sun;

	public Solar(Calendar time) {
		this.time = time;
		this.earth = new Earth(time);
		this.jupiter = new Jupiter(time);
		this.mars = new Mars(time);
		this.mercury = new Mercury(time);
		this.neptune = new Neptune(time);
		this.saturn = new Saturn(time);
		this.venus = new Venus(time);
		this.uranus = new Uranus(time);
		this.sun = new Sun(time);
		this.energyList = new ArrayList<Energy>();
		this.energyList.add(earth);
		this.energyList.add(jupiter);
		this.energyList.add(mars);
		this.energyList.add(mercury);
		this.energyList.add(neptune);
		this.energyList.add(saturn);
		this.energyList.add(venus);
		this.energyList.add(uranus);
		this.energyList.add(sun);
		this.energyMap.put("earth", earth);
		this.energyMap.put("jupiter", jupiter);
		this.energyMap.put("mars", mars);
		this.energyMap.put("mercury", mercury);
		this.energyMap.put("neptune", neptune);
		this.energyMap.put("saturn", saturn);
		this.energyMap.put("venus", venus);
		this.energyMap.put("uranus", uranus);
		this.energyMap.put("sun", sun);
		this.getGravity();
		this.setScale(0.00000005);
		try {
			this.compareSpace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void compareSpace() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
		Date start = sdf.parse("1950.01");
		Date end = sdf.parse("2017.04");

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(start);
		double value = 0;
		while (!gcal.getTime().after(end)) {
			value = this.compareSpace(gcal, 0.9);
			 Date d = gcal.getTime();
			if(value > .3)
				System.out.println(value + " "+d);
		    gcal.add(Calendar.DATE, 1);
		}
	}

	public double compareSpace(Calendar time, double threshold) {
		Earth earth = new Earth(time);
		Jupiter jupiter = new Jupiter(time);
		Mars mars = new Mars(time);
		Mercury mercury = new Mercury(time);
		Neptune neptune = new Neptune(time);
		Saturn saturn = new Saturn(time);
		Venus venus = new Venus(time);
		Uranus uranus = new Uranus(time);
		Sun sun = new Sun(time);
		List<Energy> energyList = new ArrayList<Energy>();
		energyList.add(earth);
		energyList.add(jupiter);
		energyList.add(mars);
		energyList.add(mercury);
		energyList.add(neptune);
		energyList.add(saturn);
		energyList.add(venus);
		energyList.add(uranus);
		energyList.add(sun);
		List<Triangle> triangleListA = new LinkedList<Triangle>();
		Triangle i = null;
		for (Energy a : energyList) {
			if (!(a instanceof Sun)){
				for (Energy b : energyList) {
					if (!(b instanceof Sun) && a != b) {
						i = new Triangle(sun, a, b);
						triangleListA.add(i);
					}
				}
			}
		}
		List<Triangle> triangleListB = new LinkedList<Triangle>();
		Triangle j = null;
		for (Energy a : this.energyList) {
			if (!(a instanceof Sun)){
			for (Energy b : this.energyList) {
				if (!(b instanceof Sun) && a != b) {
					j = new Triangle(this.sun, a, b);
					triangleListB.add(j);
				}
			}
			}
		}
		double count = 0;
		double total = triangleListA.size();
		for (Triangle a : triangleListA) {
			for (Triangle b : triangleListB) {
				if (a.j.name.equals(b.j.name) && a.k.name.equals(b.k.name)) {
					if (b.thresholdA(a.A, threshold)) {
						count++;
					}
				}
			}
		}
		return count / total;
	}

	public double getKineticEnergy() {
		double kineticEnergy = 0;
		for (Energy e : this.energyList) {
			kineticEnergy += 0.5 * e.mass * Math.pow(e.angularVelocity, 2) * Math.pow(e.radius, 2);
		}
		return kineticEnergy;
	}

	public Calendar getTime() {
		return this.time;
	}

	public void setTime(Calendar time) {
		this.time = time;
		for (Energy energy : energyList) {
			energy.setTime(time);
		}
	}

	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public double calculateMean(List<Double> doubleList) {
		double sum = 0;
		for (Double d : doubleList) {
			sum += d;
		}
		return sum / doubleList.size();
	}

	public double calculateSum(List<Double> doubleList) {
		double sum = 0;
		for (Double d : doubleList) {
			sum += d;
		}
		return sum;
	}

	public void setScale(double scale) {
		this.scale = scale;
		for (Energy e : this.energyList) {
			e.scale = scale;
		}
	}

	public void getGravity() {
		for (Energy energy : energyList) {
			for (Energy e : energyList) {
				if (energy != e) {
					if (energy.mass != 1 && e.mass != 1) {
						Vector3D force = e.getGravity(energy);
						energy.force = energy.force.subtract(force);
						e.force = e.force.add(force);
					}
				}
			}
		}

	}

	public void drawDate(Graphics2D g, Calendar c) {
		g.setColor(Color.WHITE);
		g.drawString(c.getTime() + "", -100, -150);
	}

	public void draw(Graphics2D g) {
		drawDate(g, this.time);
		for (Energy e : this.energyList) {
			if (e instanceof Earth) {
				Earth earth = (Earth) e;
				earth.draw(g);
			} else if (e instanceof Luna) {
				Luna luna = (Luna) e;
				luna.draw(g);
			} else if (e instanceof Jupiter) {
				Jupiter jupiter = (Jupiter) e;
				jupiter.draw(g);
			} else if (e instanceof Venus) {
				Venus venus = (Venus) e;
				venus.draw(g);
			} else if (e instanceof Mars) {
				Mars mars = (Mars) e;
				mars.draw(g);
			} else if (e instanceof Mercury) {
				Mercury mercury = (Mercury) e;
				mercury.draw(g);
			} else if (e instanceof Neptune) {
				Neptune neptune = (Neptune) e;
				neptune.draw(g);
			} else if (e instanceof Saturn) {
				Saturn saturn = (Saturn) e;
				saturn.draw(g);
			} else if (e instanceof Uranus) {
				Uranus uranus = (Uranus) e;
				uranus.draw(g);
			} else if (e instanceof Sun) {
				Sun sun = (Sun) e;
				sun.draw(g);
			}
		}
	}
}
