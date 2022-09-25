package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpaceClickApps extends AbstractEntity{
    @ManyToOne
    @Column(nullable = false)
    private Space space;

    @ManyToOne
    @Column(nullable = false)
    private ClickApps clickApps;
}
