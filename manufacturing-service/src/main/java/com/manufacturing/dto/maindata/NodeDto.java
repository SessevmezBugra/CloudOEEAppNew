package com.manufacturing.dto.maindata;

import com.manufacturing.enums.NodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NodeDto {

    private Long id;
    private NodeDto parent;
    private List<NodeDto> children;
    private List<UserNodeMappingDto> users;
    private String name;
    private NodeType type;
    private HierarchyDto hierarchy;

}
