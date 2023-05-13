package training360.gardenservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGardenerCommand {

    @NotEmpty(message = "can not be empty")
    @NotNull(message = "can not be empty")
    @NotBlank(message = "can not be empty")
    private String name;
}
