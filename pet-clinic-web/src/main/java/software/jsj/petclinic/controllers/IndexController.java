/**
 * 
 */
package software.jsj.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jsanyang
 *
 */
@Controller
public class IndexController {

  @RequestMapping({"/", "", "index", "index.html"})
  public String index() {
    
    return "index";
  }
  
  @RequestMapping("/oups")
  public String oupsHandler() {
    
    return "notimplemented";
    
  }
  
}
