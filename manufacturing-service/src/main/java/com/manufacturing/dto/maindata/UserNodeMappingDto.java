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
public class UserNodeMappingDto {

    private NodeDto node;
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected NodeDto node;

        protected String username;

    }
}
