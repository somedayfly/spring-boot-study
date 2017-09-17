package someday.fly.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DemoController {

    @RequestMapping(value = "/hello")
    public String getHelloInfo(){
        return "Hello everyone";
    }
}
