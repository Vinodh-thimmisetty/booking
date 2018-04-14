package com.vinodh.booking.api.repository;

import com.vinodh.booking.api.domain.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminMongoRepository extends MongoRepository<Admin, String> {
    Admin findAdminByAdminName(String adminName);
    Admin findAdminByAdminEmail(String adminEmail);
}
