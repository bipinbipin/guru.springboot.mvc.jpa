package guru.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bipin on 9/29/2015.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

}
