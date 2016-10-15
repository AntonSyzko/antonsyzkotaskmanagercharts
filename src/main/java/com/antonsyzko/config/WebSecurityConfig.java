package com.antonsyzko.config;
/**
 * Created by Admin on 19.09.2016.
 */
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home","tasks","/error").authenticated().
                antMatchers(HttpMethod.POST, "/").authenticated()
           .antMatchers(HttpMethod.PUT, "/").authenticated()
              .antMatchers(HttpMethod.DELETE, "/").authenticated()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().deleteCookies("remember-me")
                .permitAll().and().rememberMe();
//        //limited session controle - max 3 sessions permit - switched off for open demo use 
//        http
//                .sessionManagement()
//                .maximumSessions(3)
//                .expiredUrl("/login?expired")
//                .maxSessionsPreventsLogin(true)
//                .and()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .invalidSessionUrl("/");
//        //session
    }

    @Autowired
    private DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        userDetailsService.setDataSource(datasource);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        auth.jdbcAuthentication().dataSource(datasource);

        if(!userDetailsService.userExists("task")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            User userDetails = new User("test", encoder.encode("test"), authorities);
            User userDetails2 = new User("admin", encoder.encode("admin"), authorities);

            userDetailsService.createUser(userDetails2);
        }
    }

}
