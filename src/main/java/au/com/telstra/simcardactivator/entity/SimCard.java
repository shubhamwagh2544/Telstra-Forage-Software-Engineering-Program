package au.com.telstra.simcardactivator.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "simcard")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimCard {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "simcard_id_seq"
    )
    @SequenceGenerator(
            name = "simcard_id_seq",
            sequenceName = "simcard_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(
            name = "iccid",
            nullable = false
    )
    private String iccid;

    @Column(
            name = "customer_email",
            nullable = false
    )
    private String customerEmail;

    @Column(
            name = "is_active",
            nullable = false
    )
    private boolean isActive;

    public SimCard(String iccid, String customerEmail, boolean success) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.isActive = success;
    }
}
