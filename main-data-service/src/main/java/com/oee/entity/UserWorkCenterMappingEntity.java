package com.oee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "WORK_CENTER_USER_MAPPING")
@IdClass(UserWorkCenterMappingEntity.Key.class)
public class UserWorkCenterMappingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_CENTER_ID")
    private WorkCenterEntity workCenter;

    @Id
    @JoinColumn(name = "USERNAME")
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected WorkCenterEntity workCenter;

        protected String username;

    }
}
