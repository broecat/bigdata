package edu.upr.tweets;
import edu.upr.tweets.task1.TaskOneDriver;
import edu.upr.tweets.task2.TaskTwoDriver;
import edu.upr.tweets.task3.TaskThreeDriver;
import edu.upr.tweets.task4.TaskFourDriver;
import edu.upr.tweets.task5.TaskFiveDriver;
import edu.upr.tweets.task6.TaskSixDriver;
import edu.upr.tweets.task7.TaskSevenDriver;

public class MainDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: MainDriver <input path> <output path>");
            System.exit(-1);
        }

        TaskOneDriver.start(args);
        TaskTwoDriver.start(args);
        TaskThreeDriver.start(args);
        TaskFourDriver.start(args);
        TaskFiveDriver.start(args);
        TaskSixDriver.start(args);
        TaskSevenDriver.start(args);
        System.exit(0);
    }

}