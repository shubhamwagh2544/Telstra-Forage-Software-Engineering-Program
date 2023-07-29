package au.com.telstra.simcardactivator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/simcard-activation")
public class SimCardActivationController {

    private final SimCardActivationService activationService;

    @PostMapping(
            path = "activate",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public SimCardActivationResponse activateSimCard(@RequestBody SimCardActivationRequest activationRequest) {
        log.info("SimCard Activation Request {} : ", activationRequest);
        SimCardActivationResponse activationResponse = activationService.activateSimCard(activationRequest);
        log.info("SimCard Activation Response {} : ", activationResponse);
        return activationResponse;
    }

}
