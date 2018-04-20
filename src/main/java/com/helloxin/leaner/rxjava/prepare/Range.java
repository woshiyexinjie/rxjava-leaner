package com.helloxin.leaner.rxjava.prepare;

import java.util.Iterator;

/**
 * Created by nandiexin on 2018/3/30.
 */
public  class Range implements Iterable<Integer> {

    private static class RangeIterator implements Iterator<Integer> {

        private int next;
        private final int end;

        RangeIterator(int start, int count) {
            this.next = start;
            this.end = start + count;
        }

        @Override
        public boolean hasNext() {
            return next < end;
        }

        @Override
        public Integer next() {
            return next++;
        }

    }

    private final int start;
    private final int count;

    public Range(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator(start, count);
    }

}
