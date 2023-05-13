package training360.gardenservices.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "gardeners")
public class Gardener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "gardener")
    private List<GardenWork> gardenWorks;

    public void addGardenWork(GardenWork gardenWork) {
        gardenWorks.add(gardenWork);
        gardenWork.setGardener(this);
    }


}
