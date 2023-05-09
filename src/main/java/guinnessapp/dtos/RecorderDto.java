package guinnessapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecorderDto {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;
}
