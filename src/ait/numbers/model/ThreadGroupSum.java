package ait.numbers.model;

import ait.numbers.task.OneGropuSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        OneGropuSum[] tasks = new OneGropuSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGropuSum(numberGroups[i]);
        }
        Thread[] threads = new Thread[tasks.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();

        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        int res = Arrays.stream(tasks).mapToInt(OneGropuSum::getSum).sum();
        return res;

    }

}

