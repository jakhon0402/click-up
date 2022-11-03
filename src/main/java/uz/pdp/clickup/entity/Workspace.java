package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;
import uz.pdp.clickup.entity.template.AbsLongEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"id", "name"})
})
public class Workspace extends AbsLongEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private String initialLetter;

    @OneToOne
    private Attachment avatarId;

    public Workspace(String name, String color, User owner, Attachment avatarId) {
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.avatarId = avatarId;
    }

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod(){
        this.initialLetter = name.substring(0,1);
    }
}
