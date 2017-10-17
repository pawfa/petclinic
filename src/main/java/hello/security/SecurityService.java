package hello.security;

public interface SecurityService {

    String findLoggedInMail();
    void autologin(String mail, String password);

}
