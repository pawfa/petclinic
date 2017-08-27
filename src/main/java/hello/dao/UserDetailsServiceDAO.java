package hello.dao;

import hello.entity.Owner;
import hello.entity.Vet;
import hello.service.owner.OwnerService;
import hello.service.vet.VetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;

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

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

//        try {
            return buildUserFromUserEntity(mail);
//        } catch (UsernameNotFoundException e) {
//            logger.error("Throwed",e);
//            throw new UsernameNotFoundException("Username not found");
//        }



    }

    private User buildUserFromUserEntity(String mail) throws UsernameNotFoundException {
        // convert model user to spring security user
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;


            if(mail.contains("@petclinic.com")){

                if (vet == null )throw new UsernameNotFoundException("Username vet not found");
                vet = vetService.findByMail(mail);
                String username = vet.getMail();
                String password = vet.getPassword();

                Collection authorities = new ArrayList();

                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                User springUser = new User(username, password, enabled,
                        accountNonExpired, credentialsNonExpired, accountNonLocked,
                        authorities);
                return springUser;
            }else {

                if (owner == null )throw new UsernameNotFoundException("Username owner not found");
                owner = ownerService.findByMail(mail);
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
