package someday.fly.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import someday.fly.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    static Map<Long, User> users = new HashMap<Long, User>();

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户详细信息", notes = "根据用户id获取用户详细信息")
    public User getUserById(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细信息实体", required = true, dataType = "User")
    public String saveUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息", notes = "根据用户id来指定更新对象，并根据传过来的user对象更新用户信息")
    @ApiImplicitParam(name = "user", value = "用户详细信息实体", required = true, dataType = "User")
    public String updateUser(
            @PathVariable long id, @RequestBody User user
    ) {
        User user1 = users.get(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        return "success";
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户信息", notes = "根据用户id删除用户信息")
    public String deleteUser(
            @PathVariable long id
    ) {
        users.remove(id);
        return "success";
    }
}
