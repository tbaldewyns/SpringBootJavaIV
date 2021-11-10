package org.helb.baseproject.api.statistic;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Statistic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistic {

    @Id
    @GeneratedValue
    private int id;
    private int year;
    private String mostListenedMusic;
    private int totalMinuteListened;
    private String genderMostListened;


}
