/* Controller.java
 * Date 201710
 * Author Joaquin Rodriguez
 */
package org.josvaldor.prospero.energy.main;

import org.josvaldor.prospero.energy.system.Solar;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Controller {
    private Solar solar = new Solar();
    
    @RequestMapping("/space")
    public Object space(@RequestParam(value="time", defaultValue="null") String time){
    	Object object  = null;
    	if(time != null && !time.equals("null"))
    		object = null;//solar.getEnergyList(time);//list does not work as return object
    	return "hello";
    }
    
    @RequestMapping("/time")
    public Object time(@RequestParam(value="index", defaultValue="null") String index,
    				   @RequestParam(value="start", defaultValue="null") String start,
    				   @RequestParam(value="end", defaultValue="null") String end,
    				   @RequestParam(value="threshold", defaultValue="null") String threshold,
    				   @RequestParam(value="percent", defaultValue="null") String percent){
    	Object object = null;//solar.searchSpace(index, start, end, Double.parseDouble(threshold), Double.parseDouble(percent));
    	return "hello";
    }
}