package edu.upr.tweets.task5;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskFiveReducer extends Reducer<LongWritable, LongWritable, LongWritable, Text> {
    @Override
    protected void reduce(LongWritable key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {
        ArrayList<LongWritable> arr = new ArrayList<LongWritable>();

        for(LongWritable lw: values)
        {
            arr.add(lw);
        }

        context.write(key, new Text(Arrays.toString(arr.toArray())));
    }
}