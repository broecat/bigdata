package edu.upr.tweets.task1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskOneReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int count = 0;

        for (IntWritable value : values){
            count += value.get();
        }
        context.write(key, new IntWritable(count));
    }
}