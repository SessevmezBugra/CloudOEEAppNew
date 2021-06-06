package com.manufacturing.dto.maindata;

import com.manufacturing.enums.HierarchyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HierarchyHeaderDto {

    private Long id;
    private HierarchyDto child;
    private String name;
    private HierarchyType type;
}
