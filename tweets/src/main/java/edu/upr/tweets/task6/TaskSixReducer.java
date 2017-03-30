package edu.upr.tweets.task6;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskSixReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int count = 0;

        for(IntWritable i: values)
        {
            count+=i.get();
        }

        context.write(key, new IntWritable(count));
    }
}