package com.mygdx.game.Screens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//http://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java
public class TestList
{

    public <T> List<T> union(List<T> list1, List<T> list2)
    {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public <T> List<T> intersection(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<T>();

        for (T t : list1)
        {
            if(list2.contains(t))
            {
                list.add(t);
            }
        }

        return list;
    }

}
