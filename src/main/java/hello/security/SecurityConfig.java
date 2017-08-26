package hello.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler, UserDetailsService userDetailsService) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery("select mail, password from owner where mail=?")
//                .authoritiesByUsernameQuery("SELECT mail, ‘USER’ FROM owner WHERE mail=?")
//                .dataSource(dataSource);
//
//        System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("select mail, password from owner where mail=?"));
//        System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("SELECT mail, ‘USER’ FROM owner WHERE mail=?"));

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
//                .antMatchers("/owner/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/vet/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/owner")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login-error")
                .loginProcessingUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/");
//                .successHandler(authenticationSuccessHandler);
    }

}
