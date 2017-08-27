package hello.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler, UserDetailsService userDetailsService, AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

            auth.userDetailsService(userDetailsService);

    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**","/registration","/registrationForm", "/vet/**","/login-error").permitAll()
                .antMatchers("/owner/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/vet/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login-error")
                .loginProcessingUrl("/")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }

}
