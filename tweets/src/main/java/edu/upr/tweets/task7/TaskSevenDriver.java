package edu.upr.tweets.task7;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskSevenDriver{
    public static boolean start(String[] args)
    {
        try {
            Job job = new Job();
            job.setJarByClass(edu.upr.tweets.task6.TaskSixDriver.class);
            job.setJobName("Find all messages posted");

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]+"/task7"));

            job.setMapperClass(edu.upr.tweets.task7.TaskSevenMapper.class);
            job.setReducerClass(edu.upr.tweets.task7.TaskSevenReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            return job.waitForCompletion(true);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
