package com.anibaba.dict;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class MainTests {


    @Test
    public void main() {
        HashSet<Integer> arr = new HashSet<>();
        HashSet<Integer> arr2 = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            arr.add(i);
            if (i % 2 == 1)
                arr2.add(i);
        }
        arr2.forEach(arr::remove);

        arr.forEach(System.out::println);
    }


}
