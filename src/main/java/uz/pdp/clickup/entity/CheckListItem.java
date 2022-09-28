package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckListItem extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private CheckList checkList;

    @Column(nullable = false)
    private boolean resolved;

    @ManyToOne
    private User assignedUser;
}
