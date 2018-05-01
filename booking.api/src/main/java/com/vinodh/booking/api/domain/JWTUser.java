package com.vinodh.booking.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinodh.booking.api.collections.User;
import com.vinodh.booking.api.util.AuthorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class JWTUser implements UserDetails {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String userEmail;

    private int userAge;

    private String userPhone;

    private boolean isUserActive;

    private String userRole;

    private Date lastPasswordResetDate;

    private AuthorityName authorityName;


    /**
     * Copy Constructor
     *
     * @param user
     */
    public JWTUser(User user) {
        if (user != null) {
            this.username = user.getUsername();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.userEmail = user.getUserEmail();
            this.password = user.getPassword();
            this.userAge = user.getUserAge();
            this.userPhone = user.getUserPhone();
            this.userRole = user.getUserRole();
            this.isUserActive = user.isUserActive();
            this.lastPasswordResetDate = user.getLastPasswordResetDate();
            this.authorityName = getAuthorityName(user.getUserRole());
        }
    }


    public void setAuthorityName(String userRole) {
        if (EnumUtils.isValidEnum(AuthorityName.class, userRole)) {
            this.authorityName = AuthorityName.valueOf(userRole);
        } else {
            this.authorityName = AuthorityName.UNSUPPORTED;
        }
    }

    private AuthorityName getAuthorityName(String userRole) {
        if (EnumUtils.isValidEnum(AuthorityName.class, userRole)) {
            return AuthorityName.valueOf(userRole);
        } else {
            return AuthorityName.UNSUPPORTED;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (this.authorityName) {
            case ROLE_SUPER_ADMIN:
                authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case ROLE_ADMIN:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case ROLE_USER:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            default:
                authorities.add(new SimpleGrantedAuthority("UNSUPPORTED"));
                break;
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
