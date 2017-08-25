package hello.security.service;

public interface SecurityService {

    String findLoggedInMail();
    void autologin(String mail, String password);

}
