package edu.upr.tweets.task5;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.io.IOException;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskFiveMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status status = TwitterObjectFactory.createStatus(rawTweet);
            if (status.getInReplyToStatusId()>0) {

                context.write(new LongWritable(status.getInReplyToStatusId()), new LongWritable(status.getId()));
            }


        } catch (TwitterException e) {

        }
    }
}