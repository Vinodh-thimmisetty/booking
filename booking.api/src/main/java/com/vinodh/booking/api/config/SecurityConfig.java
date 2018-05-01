package com.vinodh.booking.api.config;

import com.vinodh.booking.api.jwt.JWTUserDetailsService;
import com.vinodh.booking.api.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private JWTUserDetailsService jwtUserDetailsService;
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(JWTUserDetailsService jwtUserDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       /* auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());*/
        auth
                .inMemoryAuthentication()
                .withUser("user")  // #1
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin") // #2
                .password("admin")
                .roles("ADMIN","USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        // AuthenticationManager using one of the Authentication Providers which in turn uses UserDetails Service and
        // returns UserDetails(it has Granted Authorities) and set the user principle in Security context filter
        return new ProviderManager(Stream.of(authenticationProviderBean()).collect(Collectors.toList()));
    }

    @Bean
    public AuthenticationProvider authenticationProviderBean() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsWrapper = new
                UserDetailsByNameServiceWrapper<>();
        userDetailsWrapper.setUserDetailsService(jwtUserDetailsService);
        return userDetailsWrapper;
    }*/

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Filter Security Interceptor
     *
     * @param httpSecurity Security Interceptor
     * @throws Exception Generic Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.antMatcher("/**")
                .authorizeRequests()
                // Static resource paths
                .antMatchers("/resources/**").permitAll()
                // End points urls
                .mvcMatchers("/admin/**").hasRole(valueOf(ADMIN))
                .mvcMatchers("/users/**").hasRole(valueOf(USER))
                .mvcMatchers("/*/**").hasRole(valueOf(USER))
                .anyRequest().authenticated();

        httpSecurity.formLogin().permitAll();
        httpSecurity.httpBasic();
//
//        httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
//
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        // httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


    // Access Decision Manager
    // Access Decision Voters ( GRANT or DENY or ABSTAIN ) e.g. Role Voter

}
