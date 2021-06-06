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
public class UserHierarchyHeaderMappingDto {

    private HierarchyHeaderDto hierarchy;
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected HierarchyHeaderDto hierarchy;

        protected String username;

    }
}
