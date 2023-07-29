package au.com.telstra.simcardactivator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimCardActivationRequest {

    private String iccid;

    @JsonIgnore
    private String customerEmail;

}
