package hello.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    private final Log logger = LogFactory.getLog(getClass());

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(Exception.class)
    public void handleConflict() {
        System.out.println("working");
    }

    @ExceptionHandler(IOException.class)
    public String exceptionHandler(HttpServletRequest req, Exception ex, Model theModel){
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        theModel.addAttribute("nullPointer",true);
        return "login-error";
    }
}
