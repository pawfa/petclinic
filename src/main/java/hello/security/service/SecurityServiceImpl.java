package hello.security.service;

import hello.entity.Owner;
import hello.service.owner.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {


    private AuthenticationManager authenticationManager;
    private OwnerService ownerService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, OwnerService ownerService) {
        this.authenticationManager = authenticationManager;
        this.ownerService = ownerService;
    }

    @Override
    public String findLoggedInMail() {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof Owner) {
            System.out.println("Securiry service casting");
            return ((Owner)userDetails).getMail();
        }
        return null;
    }

    @Override
    public void autologin(String mail, String password) {
        Owner userDetails = ownerService.findByMail(mail);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password);

        System.out.println("Usernamepasswordauth name"+usernamePasswordAuthenticationToken.getName());
        System.out.println("Usernamepasswordauth credentials"+usernamePasswordAuthenticationToken.getCredentials());
        System.out.println("Usernamepasswordauth credentials"+usernamePasswordAuthenticationToken.isAuthenticated());
        System.out.println("Metoda autologin autentykacja");
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        System.out.println("Usernamepasswordauth is authenticated"+usernamePasswordAuthenticationToken.isAuthenticated());

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

    }
}
