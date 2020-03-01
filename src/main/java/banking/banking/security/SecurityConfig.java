package banking.banking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/api/transaction/retrait**").permitAll()
         .antMatchers("/api/transaction/depot**").permitAll()
         .antMatchers("/api/transaction/historique/**").permitAll()
         .antMatchers("/api/transaction**").permitAll()
         .antMatchers("/api/compte**").permitAll()
         .antMatchers("/transaction").access("hasRole('ROLE_ADMIN')")
         .anyRequest().authenticated()
         ;
;         

        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("admin")
            .roles("USER");
    }
}
