package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.entity.SimCardActivationRequest;
import au.com.telstra.simcardactivator.entity.SimCardActivationResponse;
import au.com.telstra.simcardactivator.exception.SimCardNotFoundException;
import au.com.telstra.simcardactivator.repository.SimCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SimCardActivationService {

    private final RestTemplate restTemplate;
    private final SimCardRepository simCardRepository;

    public SimCardActivationResponse activateSimCard(SimCardActivationRequest activationRequest) {

        //simcard-activator microservice communicating with actuator microservice
        SimCardActivationResponse activationResponse = restTemplate.postForObject(
                "http://localhost:8444/actuate",
                activationRequest,
                SimCardActivationResponse.class
        );

        SimCard simCard = new SimCard(
                activationRequest.getIccid(),
                activationRequest.getCustomerEmail(),
                activationResponse.isSuccess()
        );

        //save simcard to repository
        simCardRepository.save(simCard);

        return activationResponse;
    }

    public SimCard getSimCard(Long simCardId) {
        SimCard simCard = simCardRepository.
                findById(simCardId)
                .orElseThrow(() -> new SimCardNotFoundException(
                        String.format("Simcard with id %s was not found", simCardId)
                ));

        return simCard;
    }
}
