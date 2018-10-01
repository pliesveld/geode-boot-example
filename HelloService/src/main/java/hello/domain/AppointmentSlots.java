package hello.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Region("AppointmentSlots")
@RequiredArgsConstructor(staticName = "newAppointment")
public class AppointmentSlots implements Serializable {

    @Id @NotNull
    private Long id;

    private Integer siteCode;

    private Integer locationId;

    private Integer slotsAvailable;
}
