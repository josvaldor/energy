package org.josvaldor.prospero.energy.system;

import java.util.List;

public class Result {

	public String time;
	public double value;
	public List<Pair> pairList;
	
	public String toString(){
		return time+" "+value+" "+pairList;
	}
 }
