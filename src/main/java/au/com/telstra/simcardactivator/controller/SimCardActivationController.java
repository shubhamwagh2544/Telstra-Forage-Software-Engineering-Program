package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.entity.SimCardActivationRequest;
import au.com.telstra.simcardactivator.entity.SimCardActivationResponse;
import au.com.telstra.simcardactivator.service.SimCardActivationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            path = "{simCardId}"
    )
    public SimCard getSimCard(@PathVariable Long simCardId) {
        log.info("Reading simcard with id {}", simCardId);
        SimCard simCard = activationService.getSimCard(simCardId);
        return simCard;
    }

}
