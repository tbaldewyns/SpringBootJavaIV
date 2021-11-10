package org.helb.baseproject.model.entity.music;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Music")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Music {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String author;
    private float time;
    private Date realsedAt;
    private String gender;

}
