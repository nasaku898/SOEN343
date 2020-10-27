package com.soen343.shs.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
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

    private final SHSUserDetailsService userDetailsService;

    @Lazy
    private final ConversionService mvcConversionService;

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
                .antMatchers("/api").permitAll()
                .and()
                .formLogin().disable()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
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

//    @Autowired
//    @Override
//    public void addFormatters(final FormatterRegistry mvcConversionService) {
//        mvcConversionService.addConverter(new RegistrationDTOToUserConverter());
//        mvcConversionService.addConverter(new RealUserToRealUserDTOConverter());
//        mvcConversionService.addConverter(new RoomToRoomDTOConverter());
//        mvcConversionService.addConverter(new ExteriorDoorToExteriorDoorDTOConverter());
//        mvcConversionService.addConverter(new InteriorDoorToInteriorDoorDTOConverter());
//        mvcConversionService.addConverter(new LightToLightDTOConverter());
//        mvcConversionService.addConverter(new HouseDTOToHouseConverter());
//        mvcConversionService.addConverter(new HouseWindowToHouseWindowDTOConverter());
//        mvcConversionService.addConverter(new HouseMemberToHouseMemberDTOConverter());
//        mvcConversionService.addConverter(new LoadLightDTOToLightConverter());
//        mvcConversionService.addConverter(new LoadHouseWindowDTOToHouseWindowConverter());
//        mvcConversionService.addConverter(new HouseToHouseDTOConverter());
//    }

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
