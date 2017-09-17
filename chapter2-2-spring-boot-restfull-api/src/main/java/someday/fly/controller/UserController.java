package someday.fly.controller;


import org.springframework.web.bind.annotation.*;
import someday.fly.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final String SUCCESS = "success";

    static Map<Long, User> users = new  HashMap<Long, User>();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers(){
        return new ArrayList<User>(users.values());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id){
        return users.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user){
        users.put(user.getId(), user);
        return SUCCESS;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String updateUser(
            @PathVariable long id, @ModelAttribute User user
    ){
        User user1 = users.get(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        return SUCCESS;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteUser(
            @PathVariable long id
    ){
         users.remove(id);
         return SUCCESS;
    }
}
