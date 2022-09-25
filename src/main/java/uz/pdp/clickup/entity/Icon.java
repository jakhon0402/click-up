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
public class Icon extends AbstractEntity{
    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Attachment attachment;

    @Column(nullable = false)
    private String initialLetter;

    @Column(nullable = false)
    private String icon;
}
