package ait.numbers;

import ait.numbers.model.ExecutorGroupSum;
import ait.numbers.model.GroupSum;
import ait.numbers.model.ParallelStreamGroupSum;
import ait.numbers.model.ThreadGroupSum;
import ait.numbers.test.GroupSumPerfomanceTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS = 10_000;
    private static final int N_NUMBERS_IN_GROUP = 10_000;
    private static final int[][] NUMBERS = new int[N_GROUPS][N_NUMBERS_IN_GROUP];
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        fillArray();
        GroupSum threadGroupSum = new ThreadGroupSum(NUMBERS);
        GroupSum executorGroupSum = new ExecutorGroupSum(NUMBERS);
        GroupSum parallelStreamGroupSum = new ParallelStreamGroupSum(NUMBERS);
        new GroupSumPerfomanceTest("ThreadGroupSum", threadGroupSum).runTest();
        new GroupSumPerfomanceTest("ExecutorGroupSum", executorGroupSum).runTest();
        new GroupSumPerfomanceTest("ParallelStreamGroupSum", parallelStreamGroupSum).runTest();
    }

    private static void fillArray() {
        for (int i = 0; i < NUMBERS.length; i++) {
            for (int j = 0; j < NUMBERS[i].length; j++) {
                NUMBERS[i][j] = random.nextInt();
            }
        }
    }
}
