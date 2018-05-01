package com.vinodh.booking.api.controllers;

import com.vinodh.booking.api.domain.JWTUser;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/hello")
    public String home(AuthenticatedPrincipal authenticatedPrincipal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser jwtUser = (JWTUser) authentication.getPrincipal();
        return authenticatedPrincipal.getName();
    }
}
