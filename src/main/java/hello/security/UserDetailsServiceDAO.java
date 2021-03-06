package hello.security;

import hello.entity.Owner;
import hello.entity.Vet;
import hello.service.owner.OwnerService;
import hello.service.vet.VetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
// klasa sprawdzajaca wpisane dane i tworzaca userdetailsservice
@Service
public class UserDetailsServiceDAO implements UserDetailsService {

    private Owner owner;
    private Vet vet;
    private OwnerService ownerService;
    private VetService vetService;

    @Autowired
    public UserDetailsServiceDAO(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

            return buildUserFromUserEntity(mail);
    }

    private User buildUserFromUserEntity(String mail) throws UsernameNotFoundException {
        // convert model user to spring security user
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

            if(mail.contains("@petclinic.com")){

                vet = vetService.findByMail(mail);
                if (vet == null )throw new UsernameNotFoundException("Username vet not found");

                String username = vet.getMail();


                String password = vet.getPassword();
                logger.info(passwordEncoder().encode(password)+" - password");
                Collection authorities = new ArrayList();

                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                User springUser = new User(username, password, enabled,
                        accountNonExpired, credentialsNonExpired, accountNonLocked,
                        authorities);
                return springUser;
            }else {

                owner = ownerService.findByMail(mail);
                if (owner == null )throw new UsernameNotFoundException("Username owner not found");

                String username = owner.getMail();
                String password = owner.getPassword();
                Collection authorities = new ArrayList();

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                User springUser = new User(username, password, enabled,
                        accountNonExpired, credentialsNonExpired, accountNonLocked,
                        authorities);
                return springUser;
            }
    }
}
