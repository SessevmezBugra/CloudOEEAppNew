package com.oee.util.filter;

import com.oee.entity.NodeEntity;

import java.util.List;
import java.util.stream.Stream;

class NodeFilter {
    public Stream<NodeEntity> filter(List<NodeEntity> nodes, Specification<NodeEntity> specification) {
        return nodes.stream().filter(n -> specification.isSatisfied(n));
    }
}
