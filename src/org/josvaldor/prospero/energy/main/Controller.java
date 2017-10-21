/* Controller.java
 * Date 201710
 * Author Joaquin Rodriguez
 */
package org.josvaldor.prospero.energy.main;

import org.josvaldor.prospero.energy.system.Solar;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Controller {
    private Solar solar = new Solar();
    
//    @RequestMapping("/object")
//    public JMXBean object(@RequestParam(value="query", defaultValue="null") String query, @RequestParam(value="attribute", defaultValue="null") String attribute ) {
//    	Object value = null;
//    	if(query != null && !query.equals("null") && attribute != null && !attribute.equals("null")){
//    		value = this.getValue(query+"", attribute+"");
//    	}
//        return new JMXBean(query+"",attribute+"",value+"",System.currentTimeMillis()+"");
//    }
}