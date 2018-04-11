package com.vinodh.booking.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "Admin")
public class Admin {

    @Id
    private String _id;

    @Field("employee name")
    private String empName;
}
