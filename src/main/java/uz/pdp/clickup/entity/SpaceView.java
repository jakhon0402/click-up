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
public class SpaceView extends AbstractEntity{
    @ManyToOne
    @Column(nullable = false)
    private Space space;

    @ManyToOne
    @Column(nullable = false)
    private View view;
}
