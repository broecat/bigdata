package edu.upr.tweets.task6;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.User;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.io.IOException;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskSixMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            User user = TwitterObjectFactory.createStatus(rawTweet).getUser();
            context.write(new Text(user.getScreenName()),new IntWritable(1));


        } catch (TwitterException e) {

        }
    }
}