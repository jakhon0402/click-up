package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Priority priority;

    @ManyToOne
    private Task parentTask;

    @Column(nullable = false)
    private Date startedDate;

    

    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Space space;

    @Column(nullable = false)
    private boolean archived;

    @Column(nullable = false)
    private String accessType;


}
