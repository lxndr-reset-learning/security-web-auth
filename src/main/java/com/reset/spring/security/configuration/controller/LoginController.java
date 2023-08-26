package com.reset.spring.security.configuration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String getInfoForAllEmpls() {
        return "view_for_all_employees";
    }

    @GetMapping("/hr_info")
    public String getInfoOnlyForHR() {
        return "view_for_hr";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers() {
        return "view_for_managers";
    }
}
