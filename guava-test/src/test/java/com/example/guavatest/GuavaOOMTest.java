package com.example.guavatest;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/10/23 20:53
 */
public class GuavaOOMTest {

    private static DataCache dataCache = new DataCache();

    @Test
    public void oom() {
        List<String> newData = new ArrayList<>();
        newData.add("A");
        newData.add("B");

        dataCache.setData(newData);

        System.gc();
        System.out.println("");

    }
}
class DataCache {
    private ImmutableList<String> data;

    public void setData(List<String> newData) {
        this.data = ImmutableList.copyOf(newData);
    }

    public ImmutableList<String> getData() {
        return data;
    }
}

