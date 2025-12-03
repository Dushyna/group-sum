package ait.numbers.model;

import ait.numbers.task.OneGropuSum;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() throws InterruptedException {
        OneGropuSum[] tasks = new OneGropuSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGropuSum(numberGroups[i]);

        }
        ExecutorService executorService = Executors.newFixedThreadPool(tasks.length);
        for (int i = 0; i < tasks.length; i++) {
            executorService.execute(tasks[i]);

        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        int res = Arrays.stream(tasks).mapToInt(OneGropuSum::getSum).sum();
        return res;
    }
}
