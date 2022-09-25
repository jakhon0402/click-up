package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskTag extends AbstractEntity{
    @ManyToOne
    @Column(nullable = false)
    private Task task;

    @ManyToOne
    @Column(nullable = false)
    private Tag tag;
}
