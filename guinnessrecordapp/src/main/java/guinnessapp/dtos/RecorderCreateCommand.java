package guinnessapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecorderCreateCommand {

    @NotNull(message = "must not be blank")
    @NotBlank(message = "must not be blank")
    private String name;

    @Past(message = "must be in the past")
    private LocalDate dateOfBirth;
}
