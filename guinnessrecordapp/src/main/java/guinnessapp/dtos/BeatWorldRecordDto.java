package guinnessapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeatWorldRecordDto {

    private String description;

    private String unitOfMeasure;

    private String oldRecorderName;

    private double oldRecordValue;

    private String newRecorderName;

    private double newRecordValue;

    private double recordDifference;

}
