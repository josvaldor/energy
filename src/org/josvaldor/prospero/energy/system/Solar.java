/*
 * 
 */
package org.josvaldor.prospero.energy.system;

import org.josvaldor.prospero.energy.Coordinate;
import org.josvaldor.prospero.energy.Energy;
import org.josvaldor.prospero.energy.Space;
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
import java.text.DateFormat;
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
	private static int defaultRate = Calendar.MONTH;
	
	public Solar(){
		super();
		this.energyList = this.getEnergyList(this.time);
		this.setScale(0.00000005);
	}
	
	public Solar(String time) {
		this.time = this.getCalendar(null, time);
		this.energyList = this.getEnergyList(this.time);
		this.setScale(0.00000005);
	}

	public Solar(Calendar time) {
		this.time = time;
		this.energyList = this.getEnergyList(this.time);
		this.setScale(0.00000005);
	}

	public Solar(String time, List<Triangle> triangleList) {
		this.time = this.getCalendar(null, time);
		this.energyList = this.getEnergyList(triangleList);
		this.setScale(0.00000005);
	}

	public Solar(Calendar time, List<Triangle> triangleList) {
		this.time = time;
		this.energyList = this.getEnergyList(triangleList);
		this.setScale(0.00000005);
	}
	
    public List<Coordinate> getCoordinateList(Energy energy, List<Energy> energyList){
    	List<Coordinate> coordinateList = new LinkedList<Coordinate>();
    	Coordinate coordinate = null;
    	Vector3D prime = null;
    	Vector3D out = null;
    	double alpha;
    	double delta;
    	for(Energy e: energyList){
    		if(!e.name.equals(energy.name)){
    			prime = e.position.subtract(energy.position);
    			out = new Vector3D(prime.getX(),
    							  ((prime.getY()*Math.cos(energy.obliquity))-(prime.getZ()*Math.sin(energy.obliquity))),
    							  ((prime.getY()*Math.sin(energy.obliquity))+(prime.getZ()*Math.cos(energy.obliquity))));
    			
    			alpha = Math.atan(out.getY()/out.getX());
    			if(out.getX()<0){
    				alpha+=180;
    			}
    			if(out.getX()>0 && out.getY()<0){
    				alpha+=360;
    			}
    			alpha=alpha/15;
    			delta = Math.atan(out.getZ()/Math.sqrt(Math.pow(out.getX(),2)+Math.pow(out.getY(), 2))); 
    			coordinate = new Coordinate();
    			coordinate.latitude = alpha;
    			coordinate.longitude = delta;
    			coordinate.label = e.name;
    			coordinateList.add(coordinate);
    		}
    	}
    	return coordinateList;
    }

	public ArrayList<Energy> getEnergyList(Calendar time) {
		Earth earth = new Earth(time);
		Jupiter jupiter = new Jupiter(time);
		Mars mars = new Mars(time);
		Mercury mercury = new Mercury(time);
		Neptune neptune = new Neptune(time);
		Saturn saturn = new Saturn(time);
		Venus venus = new Venus(time);
		Uranus uranus = new Uranus(time);
		Sun sun = new Sun(time);
		ArrayList<Energy> energyList = new ArrayList<Energy>();
		energyList.add(earth);
		energyList.add(jupiter);
		energyList.add(mars);
		energyList.add(mercury);
		energyList.add(neptune);
		energyList.add(saturn);
		energyList.add(venus);
		energyList.add(uranus);
		energyList.add(sun);
		return energyList;
	}

	public ArrayList<Energy> getEnergyList(List<Triangle> triangleList) {
		ArrayList<Energy> energyList = new ArrayList<Energy>();
		for (Triangle t : triangleList) {
			if (!energyList.contains(t.i)) {
				energyList.add(t.i);
			}
			if (!energyList.contains(t.j)) {
				energyList.add(t.j);
			}
			if (!energyList.contains(t.k)) {
				energyList.add(t.k);
			}
		}
		return energyList;
	}

	public void setEnergyList(ArrayList<Energy> energyList) {
		this.energyList = energyList;
	}

 	public Map<String, List<Space>> searchSpace(String startDate, String endDate, double threshold, double match)
			throws ParseException {
		Map<String, List<Space>> timeMap = new HashMap<String, List<Space>>();
		Date start = this.getDate(null, startDate);
		Date end = this.getDate(null, endDate);
		GregorianCalendar timeline = this.getCalendar(start);
		List<Space> spaceList = null;
		while (!timeline.getTime().after(end)) {
			String timelineDate = this.getCalendarString(null, timeline);
			spaceList = this.searchSpace(timelineDate, startDate, endDate, threshold, match);
			timeMap.put(timelineDate, spaceList);
			timeline.add(defaultRate, 1);
		}
		return timeMap;
	}

	public List<Space> searchSpace(String indexDate, String startDate, String endDate, double threshold, double match) {
		GregorianCalendar array = this.getCalendar(null, startDate);
		GregorianCalendar index = this.getCalendar(null, indexDate);
		Date end = this.getDate(null, endDate);
		List<Space> spaceList = new LinkedList<Space>();
		while (!array.getTime().after(end)) {
			Space space = this.compareSpace(index, array, threshold);
			if (space.match >= match)
				spaceList.add(space);
			array.add(defaultRate, 1);
		}
		return spaceList;
	}

	public List<Triangle> getTriangleList(List<Energy> energyList) {
		List<Triangle> triangleList = new LinkedList<Triangle>();
		Sun sun = new Sun();
		Triangle triangle = null;
		List<Energy> energyStack = new LinkedList<Energy>();
		for (Energy a : energyList) {
			if (!(a instanceof Sun)) {
				for (Energy b : energyList) {
					if (!(b instanceof Sun) && a != b && !energyStack.contains(b)) {
						triangle = new Triangle(sun, a, b);
						triangleList.add(triangle);
					}
				}
				energyStack.add(a);
			}
		}
		return triangleList;
	}

	public List<Triangle> matchTriangleList(List<Triangle> aList, List<Triangle> bList, double threshold) {
		List<Triangle> triangleList = new LinkedList<Triangle>();
		for (Triangle a : aList) {
			for (Triangle b : bList) {
				if (a.j.name.equals(b.j.name) && a.k.name.equals(b.k.name)) {
					if (b.thresholdA(a.A, threshold)) {
						triangleList.add(a);
					}
				}
			}
		}
		return triangleList;
	}

	public Space compareSpace(Calendar time, double threshold) {
		List<Energy> energyList = this.getEnergyList(time);
		List<Triangle> triangleListA = this.getTriangleList(energyList);
		List<Triangle> triangleListB = this.getTriangleList(this.energyList);
		return this.compareSpace(triangleListA, triangleListB, threshold);
	}

	public Space compareSpace(Calendar timeA, Calendar timeB, double threshold) {
		List<Triangle> triangleListA = this.getTriangleList(this.getEnergyList(timeA));
		List<Triangle> triangleListB = this.getTriangleList(this.getEnergyList(timeB));
		return this.compareSpace(triangleListA, triangleListB, threshold);
	}

	public Space compareSpace(List<Triangle> aList, List<Triangle> bList, double threshold) {
		List<Triangle> triangleList = this.matchTriangleList(aList, bList, threshold);
		double total = aList.size();
		double count = triangleList.size();
		Space space = new Space();
		space.triangleList = triangleList;
		space.match = count / total;
		return space;
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

	public void setTime(String time) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = formatter.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			this.setTime(calendar);
		} catch (ParseException e) {
			e.printStackTrace();
		}

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
		if (this.time != null) {
			drawDate(g, this.time);
		}
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
// public Map<String,List<Result>> searchSpace(String startDate, String endDate,
// double threshold,double match) throws ParseException{
// Map<String,List<Result>> timeMap = new HashMap<String,List<Result>>();
// Date start = this.getDate(null, startDate);
// Date end = this.getDate(null, endDate);
// GregorianCalendar pointer = new GregorianCalendar();
// pointer.setTime(start);
// GregorianCalendar timeline = new GregorianCalendar();
// timeline.setTime(start);
// while(!timeline.getTime().after(end)){
//
//
// this.setTime(timeline);
// Date date = timeline.getTime();
// String fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
// timeMap.put(fDate, new LinkedList<Result>());
// System.out.println("+"+fDate);
// while (!pointer.getTime().after(end)) {
// Result result = this.compareSpace(pointer, threshold);
// Date d = pointer.getTime();
// String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
// result.time = formattedDate;
// if(result.match != 1 && result.match > match){
// timeMap.get(fDate).add(result);
// System.out.println("-"+formattedDate+" "+result.match+" "+result.pairList);
// }
// pointer.add(Calendar.MONTH, 1);
// }
// pointer.setTime(start);
// timeline.add(Calendar.MONTH, 1);
// }
// return timeMap;
// }