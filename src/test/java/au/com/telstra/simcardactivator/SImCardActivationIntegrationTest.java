package au.com.telstra.simcardactivator;

import au.com.telstra.simcardactivator.entity.SimCardActivationRequest;
import au.com.telstra.simcardactivator.entity.SimCardActivationResponse;
import au.com.telstra.simcardactivator.repository.SimCardRepository;
import au.com.telstra.simcardactivator.service.SimCardActivationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class SImCardActivationIntegrationTest {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private SimCardRepository simCardRepository;

    private SimCardActivationService simCardActivationService;

    @BeforeEach
    public void setup() {
        simCardActivationService = new SimCardActivationService(
                restTemplate,
                simCardRepository
        );
    }

    //successful simcard activation request
    @Test
    public void checkIfSimCardIsSuccessfullyActivatedForValidIccid() {
        //given
        SimCardActivationRequest activationRequest = new SimCardActivationRequest(
                "1255789453849037777",
                "successfulactivation@gmail.com"
        );

        //when
        SimCardActivationResponse expectedResponse = new SimCardActivationResponse(true);

        Mockito.when(restTemplate.postForObject(
                "http://localhost:8444/actuate",
                activationRequest,
                SimCardActivationResponse.class)
        ).thenReturn(expectedResponse);

        SimCardActivationResponse actualResponse = simCardActivationService.activateSimCard(activationRequest);

        //then
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    //failed simcard activation request
    @Test
    public void checkIfSimCardIsSuccessfullyActivatedForInValidIccid() {
        //given
        SimCardActivationRequest activationRequest = new SimCardActivationRequest(
                "8944500102198304826",
                "failedactivation@gmail.com"
        );

        //when

        SimCardActivationResponse expectedResponse = new SimCardActivationResponse(false);

        Mockito.when(restTemplate.postForObject(
                "http://localhost:8444/actuate",
                activationRequest,
                SimCardActivationResponse.class)
        ).thenReturn(expectedResponse);

        SimCardActivationResponse actualResponse = simCardActivationService.activateSimCard(activationRequest);

        //then
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

}
