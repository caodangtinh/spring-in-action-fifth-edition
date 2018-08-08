package com.tinhcao.spring.springinactionfifthedition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         // In-Memory authentication
         auth
         .inMemoryAuthentication()
         .withUser("buzz")
         .password("abc")
         .authorities("ROLE_USER")
         .and()
         .withUser("woody")
         .password("bulleye")
         .authorities("ROLE_USER")
         .and()
         .passwordEncoder(NoOpPasswordEncoder.getInstance());
         */


        /**
         // JDBC authentication
         auth
         .jdbcAuthentication()
         .dataSource(dataSource)
         .usersByUsernameQuery("")
         .authoritiesByUsernameQuery("")
         .passwordEncoder(new BCryptPasswordEncoder(10));
         */

        /**
         * // LDAP authentication
         auth.ldapAuthentication();
         */

        /**
         * User Details Service authentication
         */
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/design", "/order")
//                .access("hasRole('ROLE_USER')")
//                .antMatchers("/**")
//                .permitAll();
        http
                .authorizeRequests()
                    .antMatchers("/design", "/order")
                        .hasRole("USER")
                    .antMatchers("/**")
                        .permitAll()
                    .and()
                        .csrf()
                            .disable()
                    .formLogin()
                        .loginPage("/login")
                        .successForwardUrl("/home")
                        .failureForwardUrl("/403")
                        .usernameParameter("user_name")
                        .passwordParameter("password")
                    .and()
                        .logout()
                            .logoutUrl("/logout");
        ;

    }
}
