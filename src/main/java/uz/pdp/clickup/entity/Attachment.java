package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String contentType;
}
