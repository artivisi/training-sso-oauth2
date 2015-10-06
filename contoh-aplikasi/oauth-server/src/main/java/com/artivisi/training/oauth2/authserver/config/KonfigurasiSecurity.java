package com.artivisi.training.oauth2.authserver.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class KonfigurasiSecurity  extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*    
        auth.inMemoryAuthentication()
                .withUser("endy")
                    .password("123")
                    .roles("USER"); 
                */
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, "
                        + "active as enabled from s_users where username=?")
                .authoritiesByUsernameQuery("select u.username, p.user_role  "
                        + "from s_users u  "
                        + "inner join s_permissions p on u.id = p.id_user "
                        + "where u.username=?");
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/halo")
                .and()
                .logout();
    }
}
