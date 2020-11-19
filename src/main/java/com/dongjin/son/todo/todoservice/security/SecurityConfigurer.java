package com.dongjin.son.todo.todoservice.security;

import com.dongjin.son.todo.todoservice.security.filters.CustomFilter;
import com.dongjin.son.todo.todoservice.security.filters.JwtRequestFilter;
import com.dongjin.son.todo.todoservice.security.services.TodoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    private TodoUserDetailsService todoUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    /*
    //--------------------------------------------
    // (Option 1) to use in memory Authentication
    //--------------------------------------------
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dongjin").password(passwordEncoder().encode("password"))
                .authorities("ROLE_USER");
    }
    */

    //------------------------------------------------------------------
    // (Option 2) to use a custom userDetailService for authentication
    //------------------------------------------------------------------

    // if you include below, userDetailService will be used for verification (not inMemoryAuthentication)

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(todoUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //-----------------------------------------------------------------------------
        // Basic Authentication
        // - requires Authorization header with Basic base64encoded username:password
        //-----------------------------------------------------------------------------
/*
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/basicauth").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
*/

        //------------------------------------------------
        // JWT based Authentication
        //------------------------------------------------

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()

                .and()
                // (a) set stateless session management
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // (b) add jwtRequestFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
