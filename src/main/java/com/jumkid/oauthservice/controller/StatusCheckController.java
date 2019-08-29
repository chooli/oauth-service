package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.controller.response.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusCheckController {

    public enum ServiceStatus {
        OK("OK"), ERROR("ERROR"), WARNING("WARNING");

        private String value;

        ServiceStatus(String value) { this.value = value; }

        public String value() { return value; }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/status", produces = "application/json")
    @ResponseBody
    public CommonResponse<String> status() {
        return new CommonResponse<>(ServiceStatus.OK.value());
    }

}
