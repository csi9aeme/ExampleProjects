package guinnessapp.dtos;

import guinnessapp.model.Recorder;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorldRecordDto {

    private Long id;

    private String description;

    private double value;

    private String unitOfMeasure;

    private LocalDate dateOfRecord;

    private String recorderName;
}
