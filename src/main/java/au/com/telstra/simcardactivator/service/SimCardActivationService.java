package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.entity.SimCardActivationRequest;
import au.com.telstra.simcardactivator.entity.SimCardActivationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SimCardActivationService {

    private final RestTemplate restTemplate;

    public SimCardActivationResponse activateSimCard(SimCardActivationRequest activationRequest) {

        SimCardActivationResponse activationResponse = restTemplate.postForObject(
                "http://localhost:8444/actuate",
                activationRequest,
                SimCardActivationResponse.class
        );
        return activationResponse;
    }
}
