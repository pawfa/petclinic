package hello.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
            auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //lines below is for h2 database console in browser
                .cors()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**","/registration","/registrationForm","/login-error", "/h2/*").permitAll()
                .antMatchers("/owner/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/vet/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/?error")
                .loginProcessingUrl("/")
                .successHandler(authenticationSuccessHandler);
    }

}
