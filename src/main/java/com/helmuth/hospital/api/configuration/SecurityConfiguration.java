package com.helmuth.hospital.api.configuration;


import com.helmuth.hospital.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Configuration
public class SecurityConfiguration {

@Autowired
DataSource dataSource;

@Autowired
EmployeeRepository employeeRepository;

    @Bean
    public UserDetailsManager users(DataSource dataSource, PasswordEncoder passwordEncoder) {

        List<UserDetails> employees = employeeRepository.findAll().stream().map(employee -> {
            UserDetails user = User
                    .withUsername(employee.getEmail())
                    .password(passwordEncoder.encode((employee.getFirstName()+employee.getLastName()+"123").toLowerCase()))
                    .roles("USER")
                    .build();
            return user;
        }).collect(Collectors.toList());

        return new InMemoryUserDetailsManager(employees);
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated().and().httpBasic();

        //Enables h2-console
        http.headers().frameOptions().disable();


        return http.build();
    }

}
