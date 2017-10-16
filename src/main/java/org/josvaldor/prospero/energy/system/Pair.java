package org.josvaldor.prospero.energy.system;

import java.util.LinkedList;
import java.util.List;

public class Pair {

	List<String> list = new LinkedList<String>();
	public String a;
	public String b;
	
	public boolean equals(Pair p){
		boolean flag = false;
		if(p.a.equals(a) && p.b.equals(b)){
			flag = true;
		}
		if(p.a.equals(b)&&p.b.equals(a)){
			flag = true;
		}
		if(a.equals(p.b)&&b.equals(p.a)){
			flag = true;
		}
		return flag;
	}
	
	public String toString(){
		return a+" "+b;
//		return list.toString();
	}
}
