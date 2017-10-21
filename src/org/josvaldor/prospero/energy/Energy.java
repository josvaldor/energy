package org.josvaldor.prospero.energy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Energy {

	public String name;
	public double mass;
	public Calendar startTime = (new GregorianCalendar(2000,0,1,0,0,0));
	public Calendar time;
	public Vector3D position = new Vector3D(0, 0, 0);
	public Vector3D force = new Vector3D(0, 0, 0);
	public double radius;

	public double scale = 0.0001;
	public double orbitalPeriod;
	public double obliquity;
	public double[] semiMajorAxis = new double[3];// a
	public double[] eccentricity = new double[3];// e
	public double[] inclination = new double[3];// i
	public double[] meanLongitude = new double[3];// l
	public double[] longitudeOfPeriapsis = new double[3];
	public double[] longitudeOfAscendingNode = new double[3];// N
	public double[] argumentOfPeriapsis = new double[3];
	public double[] meanAnomaly = new double[3];
    public double angularVelocity;
	private Graphics2D g;
	protected Color color;
	private static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	
	public Energy() {
		this.time = new GregorianCalendar();
	}

	public void scale(double percentage) {
		this.position.scalarMultiply(percentage);
	}

	public void setTime(Calendar time){
		this.time = time;
		this.position = this.getSpace(time).eliptic;
	}
	
//	public Vector3D getSpace(Calendar c) {
////		double d = toDateNumber(c);
//		double T = this.dateToJulian(this.startTime);
//		double t = this.dateToJulian(c);
//		double d = t - T;
//		double N = this.longitudeOfAscendingNode[0] + this.longitudeOfAscendingNode[1] * d;
//		double i = this.inclination[0] + this.inclination[1] * d;
//		double w = this.argumentOfPeriapsis[0] + this.argumentOfPeriapsis[1] * d;
//		double a = this.semiMajorAxis[0]+this.semiMajorAxis[1]*d;
//		double e = this.eccentricity[0] + this.eccentricity[1] * d;
//		double M = this.meanAnomaly[0] + this.meanAnomaly[1] * d;
//		N = this.rev(N);
//		i = this.rev(i);
//		w = this.rev(w);
//		M = this.rev(M);
//		double E = M + (180 / Math.PI) * e * Math.sin(toRadians(M)) * (1.0 + e * Math.cos(toRadians(M)));
//		double error = 1;
//		while (error > 0.005) {
//			double E1 = E - (E - (180 / Math.PI) * e * Math.sin(toRadians(E)) - M) / (1 - e * Math.cos(toRadians(E)));
//			error = Math.abs(E - E1);
//			E = E1;
//		}
//		double x = a * (Math.cos(toRadians(E)) - e);
//		double y = a * (Math.sqrt(1.0 - e * e) * Math.sin(toRadians(E)));
//		double r = Math.sqrt(x * x + y * y);
//		double v = toDegrees(Math.atan2(y, x));
//		double n_rad = toRadians(N);
//		double xw_rad = toRadians(v + w);
//		double i_rad = toRadians(i);
//		// Now we know the Moon's position in the plane of the lunar orbit. To
//		// compute the Moon's position in ecliptic coordinates, we apply these
//		// formulae:
//		double xeclip = r * (Math.cos(n_rad) * Math.cos(xw_rad) - Math.sin(n_rad) * Math.sin(xw_rad) * Math.cos(i_rad));
//		double yeclip = r * (Math.sin(n_rad) * Math.cos(xw_rad) + Math.cos(n_rad) * Math.sin(xw_rad) * Math.cos(i_rad));
//		double zeclip = r * Math.sin(xw_rad) * Math.sin(i_rad);
//
//		double RA = toDegrees(Math.atan2(yeclip, xeclip));
//		double Decl = toDegrees(Math.asin(zeclip / r));
//		Vector3D vector = new Vector3D(xeclip, yeclip, zeclip);
//		return vector;
//	}
	
	public Space getSpace(Calendar c) {
//		double d = toDateNumber(c);
		double T = this.dateToJulian(this.startTime);
		double t = this.dateToJulian(c);
		double d = t - T;
		double N = this.longitudeOfAscendingNode[0] + this.longitudeOfAscendingNode[1] * d;
		double i = this.inclination[0] + this.inclination[1] * d;
		double w = this.argumentOfPeriapsis[0] + this.argumentOfPeriapsis[1] * d;
		double a = this.semiMajorAxis[0]+this.semiMajorAxis[1]*d;
		double e = this.eccentricity[0] + this.eccentricity[1] * d;
		double M = this.meanAnomaly[0] + this.meanAnomaly[1] * d;
		N = this.rev(N);
		i = this.rev(i);
		w = this.rev(w);
		M = this.rev(M);
		double E = M + (180 / Math.PI) * e * Math.sin(toRadians(M)) * (1.0 + e * Math.cos(toRadians(M)));
		double error = 1;
		while (error > 0.005) {
			double E1 = E - (E - (180 / Math.PI) * e * Math.sin(toRadians(E)) - M) / (1 - e * Math.cos(toRadians(E)));
			error = Math.abs(E - E1);
			E = E1;
		}
		double x = a * (Math.cos(toRadians(E)) - e);
		double y = a * (Math.sqrt(1.0 - e * e) * Math.sin(toRadians(E)));
		double r = Math.sqrt(x * x + y * y);
		double v = toDegrees(Math.atan2(y, x));
		double n_rad = toRadians(N);
		double xw_rad = toRadians(v + w);
		double i_rad = toRadians(i);
		// Now we know the Moon's position in the plane of the lunar orbit. To
		// compute the Moon's position in ecliptic coordinates, we apply these
		// formulae:
		double xeclip = r * (Math.cos(n_rad) * Math.cos(xw_rad) - Math.sin(n_rad) * Math.sin(xw_rad) * Math.cos(i_rad));
		double yeclip = r * (Math.sin(n_rad) * Math.cos(xw_rad) + Math.cos(n_rad) * Math.sin(xw_rad) * Math.cos(i_rad));
		double zeclip = r * Math.sin(xw_rad) * Math.sin(i_rad);

		Space space = new Space();
		double RA = toDegrees(Math.atan2(yeclip, xeclip));
		double Decl = toDegrees(Math.asin(zeclip / r));
		Vector3D eliptic = new Vector3D(xeclip, yeclip, zeclip);
		Vector3D spherical = new Vector3D(r,RA,Decl);
		space.eliptic = eliptic;
		space.spherical = spherical;
		space.time = this.getCalendarString(null, c);
		return space;
	}

	public double toDateNumber(Calendar date) {
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int minute = date.get(Calendar.MINUTE);
		int second = date.get(Calendar.SECOND);
		return Math.round(367 * year - 7 * (year + (month + 9) / 12) / 4 + 275 * month / 9 + day - 730530);
	}

	public double rev(double x) {
		double rv = x - Math.round(x / 360.0) * 360;
		if (rv < 0.0) {
			rv = rv + 360;
		}
		return rv;
	}

	public double toRadians(double d) {
		return Math.PI * d / 180;
	}

	public double toDegrees(double rad) {
		return rev(180.0 * rad / Math.PI);
	}

	public void setPosition(Vector3D position) {
		this.position = position;
	}

	public Vector3D getGravity(Energy energy) {
		Vector3D distance = energy.position.subtract(this.position);
		double magnitude = Math.sqrt(Math.pow((double) distance.getX(), 2) + Math.pow((double) distance.getY(), 2)
				+ Math.pow((double) distance.getZ(), 2));
		double gravityForce = (Unit.GRAVITATIONAL_CONSTANT * energy.mass * this.mass) / (Math.pow(magnitude, 2));
		Vector3D force = null;
		if (magnitude != 0) {
			force = distance.normalize();
			force.scalarMultiply(gravityForce);
		}
		return force;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double dateToJulian(Calendar date) {
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int minute = date.get(Calendar.MINUTE);
		int second = date.get(Calendar.SECOND);

		double extra = (100.0 * year) + month - 190002.5;
		return (367.0 * year) - (Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12.0)) / 4.0))
				+ Math.floor((275.0 * month) / 9.0) + day + ((hour + ((minute + (second / 60.0)) / 60.0)) / 24.0)
				+ 1721013.5 - ((0.5 * extra) / Math.abs(extra)) + 0.5;
	}
	
	public GregorianCalendar getCalendar(String format, String time) {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = this.getDate(format, time);
		if (date != null)
			calendar.setTime(date);
		return calendar;
	}

	public GregorianCalendar getCalendar(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	public Date getDate(String format, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat((format == null) ? defaultFormat : format);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getDateString(String format, Date date) {
		String string = new SimpleDateFormat((format == null) ? defaultFormat : format).format(date);
		return string;
	}

	public String getCalendarString(String format, Calendar calendar) {
		return this.getDateString(format, calendar.getTime());
	}
}
