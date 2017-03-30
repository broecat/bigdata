package edu.upr.tweets.task2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskTwoDriver {
    public static boolean start(String[] args)
    {
        try {
            Job job = new Job();
            job.setJarByClass(edu.upr.tweets.task2.TaskTwoDriver.class);
            job.setJobName("Count Occurrences of Every Word");

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]+"/task2"));

            job.setMapperClass(edu.upr.tweets.task2.TaskTwoMapper.class);
            job.setReducerClass(edu.upr.tweets.task2.TaskTwoReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            return job.waitForCompletion(true);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
