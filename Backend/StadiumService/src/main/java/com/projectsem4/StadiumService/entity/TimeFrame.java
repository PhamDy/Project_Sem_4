package com.projectsem4.StadiumService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeFrame extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_frame_id")
    private Long timeFrameId;

    @Basic
    @Column(name = "time_from")
    private Integer timeFrom;

    @Basic
    @Column(name = "time_to")
    private Integer timeTo;

    @Basic
    @Column(name = "type")
    private Integer type;

}
