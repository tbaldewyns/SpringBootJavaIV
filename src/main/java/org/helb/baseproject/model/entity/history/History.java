package org.helb.baseproject.model.entity.history;

import lombok.*;
import org.helb.baseproject.model.entity.music.Music;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="History")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class History {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Music music;
    private Date listenedAt;
}
