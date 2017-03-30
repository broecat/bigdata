package edu.upr.tweets.task7;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.User;

import java.io.IOException;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskSevenMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status status = TwitterObjectFactory.createStatus(rawTweet);
            User user = status.getUser();
            context.write(new Text(user.getScreenName()),new LongWritable(status.getId()));


        } catch (TwitterException e) {

        }
    }
}