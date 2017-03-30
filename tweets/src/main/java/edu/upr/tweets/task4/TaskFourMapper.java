package edu.upr.tweets.task4;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.Status;

import java.io.IOException;

/**
 * Created by broecat on 03-30-17.
 */
public class TaskFourMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status status = TwitterObjectFactory.createStatus(rawTweet);
            if(status.isRetweet())
            {

                context.write(new LongWritable(status.getRetweetedStatus().getId()),new LongWritable(status.getId()));
            }


        }
        catch(TwitterException e){

        }

    }
}