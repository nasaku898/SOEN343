package com.soen343.shs.configuration.security;

import com.soen343.shs.converters.doors.ExteriorDoorToDoorDTOConverter;
import com.soen343.shs.converters.doors.InteriorDoorToDoorDTOConverter;
import com.soen343.shs.converters.doors.LoadDoorDTOToDoorConverter;
import com.soen343.shs.converters.doors.LoadExteriorDoorDTOToExteriorDoorConverter;
import com.soen343.shs.converters.doors.LoadInteriorDoorDTOToInteriorDoorConverter;
import com.soen343.shs.converters.houseWindows.HouseWindowToHouseWindowDTOConverter;
import com.soen343.shs.converters.houseWindows.LoadHouseWindowDTOToHouseWindowConverter;
import com.soen343.shs.converters.houses.HouseToHouseDTOConverter;
import com.soen343.shs.converters.lights.LightToLightDTOConverter;
import com.soen343.shs.converters.lights.LoadLightDTOToLightConverter;
import com.soen343.shs.converters.rooms.RoomToRoomDTOConverter;
import com.soen343.shs.converters.users.HouseMemberToHouseMemberDTOConverter;
import com.soen343.shs.converters.users.RegistrationDTOToUserConverter;
import com.soen343.shs.converters.users.RegistrationDTOToUserDTOConverter;
import com.soen343.shs.converters.users.UserDTOtoUserConverter;
import com.soen343.shs.converters.users.UserToUserDTOConverter;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

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
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                .formLogin().disable()
                .logout().permitAll()
                .and()
                .csrf().disable();

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
        registry.addConverter(new HouseWindowToHouseWindowDTOConverter());
        registry.addConverter(new HouseMemberToHouseMemberDTOConverter());
        registry.addConverter(new LoadDoorDTOToDoorConverter());
        registry.addConverter(new LoadLightDTOToLightConverter());
        registry.addConverter(new LoadHouseWindowDTOToHouseWindowConverter());
        registry.addConverter(new HouseToHouseDTOConverter());
        registry.addConverter(new LoadExteriorDoorDTOToExteriorDoorConverter());
        registry.addConverter(new LoadInteriorDoorDTOToInteriorDoorConverter());
    }

    @Bean
    static CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
