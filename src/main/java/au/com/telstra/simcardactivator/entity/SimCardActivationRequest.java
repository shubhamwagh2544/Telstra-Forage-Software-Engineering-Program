package au.com.telstra.simcardactivator.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimCardActivationRequest {

    private String iccid;

    private String customerEmail;

}
