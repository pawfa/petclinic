package hello.service.owner;

import hello.entity.Owner;
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
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceDAO implements UserDetailsService {

    private Owner o;
    private Iterable<Owner> owners;


    private OwnerService ownerService;

    @Autowired
    public UserDetailsServiceDAO(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        logger.info("loadUserByUsername username="+mail);

            o = ownerService.findByMail("u");
        System.out.println(o);
            owners = ownerService.findAll();
        for (Owner ow: owners
             ) {
            System.out.println(ow.getFirstName());

        }

//            if (o == null){
//                logger.info("Owner jest null");
//            }


        return buildUserFromUserEntity(o);
    }

    private User buildUserFromUserEntity(Owner ownerEntity) {
        // convert model user to spring security user

        String username = ownerEntity.getMail();
        String password = ownerEntity.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Collection authorities = new ArrayList();

            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User springUser = new User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);

        return springUser;
    }
}
