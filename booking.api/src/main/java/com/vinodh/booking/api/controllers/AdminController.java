package com.vinodh.booking.api.controllers;

import com.vinodh.booking.api.domain.Admin;
import com.vinodh.booking.api.repository.AdminMongoRepository;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking/admin/")
@Api(description = "Admin Dashboard to manage the Booking customers")
public class AdminController {

    private AdminMongoRepository adminMongoRepository;

    public AdminController(AdminMongoRepository adminMongoRepository) {
        this.adminMongoRepository = adminMongoRepository;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "Get All the Admin Details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<List<Admin>> findAllAdmins() {
        return ResponseEntity.ok(adminMongoRepository.findAll());
    }

    @GetMapping("/findbyAdminName/{adminName}")
    @ApiOperation(value = "Get Specifc Admin Details", response = Admin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Admin Info"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Admin> findbyAdminName(@ApiParam(value = "The Value of Admin name can be Vinodh, Thimmisetty etc .") @PathVariable String adminName) {
        return ResponseEntity.ok(adminMongoRepository.findAdminByAdminName(adminName));
    }
}
