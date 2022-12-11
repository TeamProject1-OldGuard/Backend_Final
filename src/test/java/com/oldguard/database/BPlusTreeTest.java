package com.oldguard.database;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;


class BPlusTreeTest {
    private BPlusTree<Integer> bPlusTree=new BPlusTree<>();
    @Test
    void test() {
        List<Integer> result=bPlusTree.values();
        System.out.println(result.stream().filter(v -> v<=15&&v>=0).collect(Collectors.toList()));
    }

}