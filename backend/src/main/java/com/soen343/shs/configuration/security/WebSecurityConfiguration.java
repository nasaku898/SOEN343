package com.soen343.shs.configuration.security;

import com.soen343.shs.converters.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.soen343.shs.dal.model.UserRole.CHILD;
import static com.soen343.shs.dal.model.UserRole.GUEST;
import static com.soen343.shs.dal.model.UserRole.PARENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    // Dependencies
    @Autowired
    private final SHSUserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/parent").hasRole(PARENT.name())
                .antMatchers("/user").hasAnyRole(PARENT.name(), CHILD.name(), GUEST.name())
                .antMatchers("/all", "/register").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/").loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();

        http.csrf().disable(); // will use this for now...

        // TODO: Add OAuth 2.0 Authorization 8)
        // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    // Beans
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new RegistrationDTOToUserConverter());
        registry.addConverter(new RegistrationDTOToUserDTOConverter());
        registry.addConverter(new UserDTOtoUserConverter());
        registry.addConverter(new UserToUserDTOConverter());
        registry.addConverter(new RoomToRoomDTOConverter());
        registry.addConverter(new ExteriorDoorToDoorDTOConverter());
        registry.addConverter(new InteriorDoorToDoorDTOConverter());
        registry.addConverter(new LightToLightDTOConverter());
        registry.addConverter(new WindowToWindowDTOConverter());
        registry.addConverter(new HouseToHouseDTOConverter());
    }

}
