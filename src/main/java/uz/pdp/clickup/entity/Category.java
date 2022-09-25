package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Project project;

    @Column(nullable = false)
    private boolean archived;

    @Column(nullable = false)
    private String accessType;


}
