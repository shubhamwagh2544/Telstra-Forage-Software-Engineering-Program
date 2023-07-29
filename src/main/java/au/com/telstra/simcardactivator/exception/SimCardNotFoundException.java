package au.com.telstra.simcardactivator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND
)
public class SimCardNotFoundException extends RuntimeException {
    public SimCardNotFoundException(String message) {
        super(message);
    }
}
