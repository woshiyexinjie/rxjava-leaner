package com.helloxin.leaner.rxjava.prepare;

/**
 * Created by nandiexin on 2018/4/1.
 */
public class Indexed<T> {


        public final int index;
        public final T item;
        public Indexed(int index, T item) {
            this.index = index;
            this.item = item;
        }

}
