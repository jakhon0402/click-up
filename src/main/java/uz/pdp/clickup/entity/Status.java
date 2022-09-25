package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;
import uz.pdp.clickup.entity.enums.StatusTypes;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Status extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Space space;

    @ManyToOne
    @Column(nullable = false)
    private Project project;

    @ManyToOne
    @Column(nullable = false)
    private Category category;

    @Enumerated(value = EnumType.STRING)
    private StatusTypes statusType;


}
