package guinnessapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecordCreateCommand {

    @NotNull(message = "must not be blank")
    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be blank")
    private String description;

    @NotNull(message = "must not be null")
    private double value;

    @NotNull(message = "must not be blank")
    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be blank")
    private String unitOfMeasure;

    @NotNull(message = "must not be null")
    private LocalDate dateOfRecord;

    @NotNull(message = "must not be null")
    private long recorderId;
}
