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
public class Space extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String initialLetter;

    @OneToOne
    @Column(nullable = false)
    private Attachment avatarId;

    @ManyToOne
    @Column(nullable = false)
    private Icon icon;

    @Column(nullable = false)
    private String accessType;


}
