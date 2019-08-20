package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.controller.response.CommonResponse;
import com.jumkid.oauthservice.dao.AuthorityDao;
import com.jumkid.oauthservice.dao.UserDao;
import com.jumkid.oauthservice.model.User;
import com.jumkid.oauthservice.model.Usernames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDao userDao;

    private final AuthorityDao authorityDao;

    private final ControllerHelper controllerHelper;

    @Autowired
    public UserController(UserDao userDao, AuthorityDao authorityDao, ControllerHelper controllerHelper) {
        this.userDao = userDao;
        this.authorityDao = authorityDao;
        this.controllerHelper = controllerHelper;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public User Add(@RequestBody Map<String, Object> payload){
        User newEntity = new User.Builder()
                .username((String)payload.get(User.Fields.USERNAME.value()))
                .password((String)payload.get(User.Fields.PASSWORD.value()))
                .email((String)payload.get(User.Fields.EMAIL.value()))
                .active(controllerHelper.getPayloadValue(payload, User.Fields.ACTIVE.value(), Boolean.class))
                .build();

        userDao.add(newEntity);

        return newEntity;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User get(@PathVariable String username) {
        return userDao.get(username);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public CommonResponse updateProperties(@PathVariable String username,
                                           @RequestBody Map<String, Object> properties) {
        if(username == null) return new CommonResponse(false, "username is empty");
        if(properties == null || properties.isEmpty()) return new CommonResponse(false, "no property to be updated");

        int row = userDao.updateFields(username, properties);
        return new CommonResponse((row == 1), null);
    }

    @RequestMapping(value = "/allUsernames", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Usernames getAllClientIds() {
        return new Usernames(userDao.getAllUsernames());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    @Transactional
    public CommonResponse delete(@PathVariable String username) {
        if(username != null) {
            int row = userDao.remove(username);
            logger.info("remove user {}", row);
            int row1 = authorityDao.removeRolesForUser(username);
            logger.info("remove user roles {}", row1);
            return new CommonResponse((row == 1), null);
        }
        return new CommonResponse(false, "Failed to delete user, username is invalid");
    }

}
