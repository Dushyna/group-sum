package ait.numbers.task;

import java.util.Arrays;

public class OneGropuSum implements Runnable {
    private int[] group;
    private int sum;

    public OneGropuSum(int[] group) {
        this.group = group;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        sum = Arrays.stream(group).sum();
    }
}
