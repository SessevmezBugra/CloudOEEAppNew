package com.manufacturing.dto.maindata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroupingDto {
    private Long id;
    private NodeDto node;
    private String name;
    private String desc;
}
