package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.controller.response.CommonResponse;
import com.jumkid.oauthservice.dao.AuthorityDao;
import com.jumkid.oauthservice.model.Authority;
import com.jumkid.oauthservice.model.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityDao authorityDao;

    private final ControllerHelper controllerHelper;

    @Autowired
    public AuthorityController(AuthorityDao authorityDao, ControllerHelper controllerHelper) {
        this.authorityDao = authorityDao;
        this.controllerHelper = controllerHelper;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Authority Add(@RequestBody Map<String, Object> payload){
        Authority newEntity = new Authority.Builder()
                .username((String)payload.get(Authority.Fields.USERNAME.value()))
                .role((String)payload.get(Authority.Fields.ROLE.value()))
                .build();

        authorityDao.add(newEntity);

        return newEntity;
    }

    @RequestMapping(value = "/allUserRoles/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserRoles getAllClientIds(@PathVariable String username) {
        return new UserRoles(authorityDao.getUserRoles(username));
    }

    @RequestMapping(value = "/{username}/{role}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public CommonResponse delete(@PathVariable String username, @PathVariable String role) {
        if(username != null) {
            int row = authorityDao.removeRole(username, role);
            return new CommonResponse((row == 1), null);
        }
        return new CommonResponse(false, "Failed to delete user role, username is invalid");
    }

}
