package com.vinodh.booking.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.vinodh.booking.api.utils.UserRole.ADMIN;
import static com.vinodh.booking.api.utils.UserRole.USER;
import static java.lang.String.valueOf;


/**
 * UserNamePwd Before Authentication --> Authentication Manager --> Authenticated Success with Roles and User details
 * <p>
 * <p>
 * REQUEST STARTS e.g /getDetails/{123}
 * <p>
 * 1. Authentication Filter
 * 2. Authentication Manager ( can have >1 authentication providers)
 * 3. Authentication Provider
 * 4. UserDetailService
 * 5. UserDetails
 * 6. Authentication
 * 7. Authentication Filter sets Principal(User Details) into SecurityContext
 * <p>
 * 1. FilterSecurityInterceptor
 * 2. Security Metadata from authenticated Principle set in SecurityContext holder
 * 3. Current Authentication Object
 * 4. Access Decision Manager
 * 5. Access Decision Voters
 * <p>
 * IF SUCCESS
 * <p>
 * 1. Hits RestController
 * 2. Validate the Role @PreAuthorize etc.
 * 3. Loads Response
 * <p>
 * IF FAILS
 * <p>
 * 1. Access Decision Manager throws Access Denied Exception (403)
 * 2. ExceptionTranslation Filter catches above exception Access Denied Handler
 * OR
 * 2. Authentication Entry point(401) <-- NO Authentication and trying to access Protected resource
 * <p>
 * REQUEST ENDS --> May be JSON Response
 * <p>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // AuthenticationManager using one of the Authentication Providers which in turn uses UserDetails Service and
        // returns UserDetails(it has Granted Authorities) and set the user principle in Security context filter

        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    /**
     * Filter Security Interceptor
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .mvcMatchers("/admin/**").hasRole(valueOf(ADMIN))
                .mvcMatchers("/users/**").hasRole(valueOf(USER))
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("custom-login-page").permitAll()
//                .and()
//                .httpBasic()
                .and()
//                .oauth2Login()
//                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


    // Access Decision Manager
    // Access Decision Voters ( GRANT or DENY or ABSTAIN ) e.g. Role Voter

}
