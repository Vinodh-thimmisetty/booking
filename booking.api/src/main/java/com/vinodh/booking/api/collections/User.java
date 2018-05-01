package com.vinodh.booking.api.collections;

import com.vinodh.booking.api.util.AuthorityName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "USER_INFORMATION")
public class User {
    @Id
    private String _id;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("userEmail")
    private String userEmail;

    @Field("userAge")
    private int userAge;

    @Field("userPhone")
    private String userPhone;

    @Field("isUserActive")
    private boolean isUserActive;

    @Field("userRole")
    private String userRole;

    @Field("lastPasswordResetDate")
    private Date lastPasswordResetDate;

}
