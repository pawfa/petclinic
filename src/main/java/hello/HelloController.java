package hello;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pawel on 2017-07-21.
 */

@RestController
@RequestMapping("/")
public class HelloController {


    public String index() {
        return "index";
    }

}
