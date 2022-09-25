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
public class TaskAttachment extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @Column(nullable = false)
    private Task task;

    @ManyToOne
    @Column(nullable = false)
    private Attachment attachment;

    @Column(nullable = false)
    private boolean pinCoverImage;
}
