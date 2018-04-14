package com.vinodh.booking.api.domain;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The database generated Admin ID")
    private String _id;

    @Field("employee name")
    @ApiModelProperty(notes = "Admin Name")
    private String adminName;

    @Field("employee email id")
    @ApiModelProperty(notes = "Admin Emai;")
    private String adminEmail;
}
