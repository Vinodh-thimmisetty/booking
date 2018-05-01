package com.vinodh.booking.api.collections;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Document(collection = "Admin")
@ApiModel
public class Admin {

    @Id
    @ApiModelProperty(notes = "The database generated Admin ID")
    private String _id;

    @NotBlank
    @Field("employee name")
    //@ApiModelProperty(notes = "${admin.name}")
    private String adminName;

    @NotBlank
    @Field("employee email id")
    @ApiModelProperty(notes = "Admin Email")
    private String adminEmail;

    @Min(16)
    @Field("employee age")
   // @ApiModelProperty(notes = "${admin.age}")
    private int adminAge;

}
