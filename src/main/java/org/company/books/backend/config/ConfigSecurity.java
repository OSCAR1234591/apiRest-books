package org.company.books.backend.config;

import org.company.books.backend.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    //Recuperar usuarios desde la base de datos
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests( configure->{

           configure
                   .requestMatchers("/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui/index.html","/v1/categorias").permitAll()
                   .requestMatchers(HttpMethod.GET,"/v1/libros/**").hasRole("Empleado")
                   .requestMatchers(HttpMethod.GET,"/v1/libros").hasRole("Empleado")
                   .requestMatchers(HttpMethod.POST,"/v1/libros").hasRole("Jefe")
                   .requestMatchers(HttpMethod.PUT,"/v1/libros/**").hasRole("Jefe")
                   .requestMatchers(HttpMethod.DELETE,"/v1/libros/**").hasRole("Jefe")
                   .requestMatchers(HttpMethod.GET,"/v1/categorias/**").hasRole("Empleado")
                   //.requestMatchers(HttpMethod.GET,"/v1/categorias").hasRole("Empleado")
                   .requestMatchers(HttpMethod.POST,"/v1/categorias").hasRole("Jefe")
                   .requestMatchers(HttpMethod.PUT,"/v1/categorias/**").hasRole("Jefe")
                   .requestMatchers(HttpMethod.DELETE,"/v1/categorias/**").hasRole("Jefe");

        }).addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf( csrf ->csrf.disable());

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    /* Guardar usuarios en memoria
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails oscar= User.builder().username("oscar").password("{noop}1234").roles("Empleado").build();

        UserDetails agustin= User.builder().username("agustin").password("{noop}1234").roles("Empleado","Jefe").build();

        UserDetails edita= User.builder().username("edita").password("{noop}1234").roles("Empleado","Jefe").build();

        return new InMemoryUserDetailsManager(oscar,agustin,edita);

    }
 */
}
