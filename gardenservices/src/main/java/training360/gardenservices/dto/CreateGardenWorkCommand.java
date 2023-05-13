package training360.gardenservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGardenWorkCommand {

    @NotEmpty(message = "can not be empty")
    @NotNull(message = "can not be empty")
    @NotBlank(message = "can not be empty")
    private String description;
}
