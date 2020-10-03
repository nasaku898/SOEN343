package com.soen343.shs.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final BeanConfiguration ENCODER;

    private final SHSUserDetailsService USER_DETAILS_SERVICE;

    private static final String PARENT = "PARENT";
    private static final String CHILD = "CHILD";
    private static final String GUEST = "GUEST";

    public WebSecurityConfiguration(BeanConfiguration ENCODER, SHSUserDetailsService USER_DETAILS_SERVICE) {
        this.ENCODER = ENCODER;
        this.USER_DETAILS_SERVICE = USER_DETAILS_SERVICE;
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(USER_DETAILS_SERVICE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/parent").hasRole(PARENT)
                .antMatchers("/user").hasAnyRole(PARENT, CHILD, GUEST)
                .antMatchers("/all", "/register").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();

        http.csrf().disable(); // will use this for now...

        // TODO: Add OAuth 2.0 Authorization 8)
        // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


}
