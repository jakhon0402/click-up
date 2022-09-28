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
public class TimeTracked extends AbstractEntity{
    @ManyToOne
    private Task task;

    @Column(nullable = false)
    private Date startedAt;

    @Column(nullable = false)
    private Date stoppedAt;
}
