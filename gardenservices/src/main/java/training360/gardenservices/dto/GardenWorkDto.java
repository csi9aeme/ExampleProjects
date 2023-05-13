package training360.gardenservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GardenWorkDto {


    private Long id;

    private boolean done;

    private String description;

    private String answer;

    private LocalDateTime createdAt;

    private LocalDateTime answeredAt;

    private GardenerDto gardener;
}
