package com.manufacturing.dto.maindata;

import com.manufacturing.enums.NodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HierarchyDto {
    private Long id;
    private HierarchyDto child;
    private String name;
    private NodeType type;
}
