package com.vinodh.booking.api.controllers;

import com.vinodh.booking.api.domain.Admin;
import com.vinodh.booking.api.repository.AdminMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking/admin/")
public class AdminContoller {

    private AdminMongoRepository adminMongoRepository;

    public AdminContoller(AdminMongoRepository adminMongoRepository) {
        this.adminMongoRepository = adminMongoRepository;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Admin>> findAllAdmins() {
        return ResponseEntity.ok(adminMongoRepository.findAll());
    }
}
