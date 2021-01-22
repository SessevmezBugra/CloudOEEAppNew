package com.oee.entity;


import com.oee.enums.ReasonCodeType;
import com.oee.enums.WorkUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="DOWNTIME")
@Getter
@Setter
@NoArgsConstructor
public class Downtime {

    @Id
    @Column(name="DOWNTIME_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long downtimeId;

    @Column(name="WORK_UNIT_ID")
    private Long workUnitId;

    @Enumerated(EnumType.STRING)
    @Column(name="WORK_UNIT_TYPE")
    private WorkUnit workUnitType;

    @Enumerated(EnumType.STRING)
    @Column(name="REASON_CODE_TYPE")
    private ReasonCodeType reasonCodeType;

    @Column(name="REASON_CODE")
    private String reasonCode;

    @Column(name="START_TIME")
    private String startTime;

    @Column(name="END_TIME")
    private String endTime;

    @Column(name="COMMENTS")
    private String comments;
}
