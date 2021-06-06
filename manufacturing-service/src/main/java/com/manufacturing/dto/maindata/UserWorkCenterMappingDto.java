package com.manufacturing.dto.maindata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserWorkCenterMappingDto {

    private WorkCenterDto workCenter;
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected WorkCenterDto workCenter;

        protected String username;

    }
}
