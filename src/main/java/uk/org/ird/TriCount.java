package uk.org.ird;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class TriCount {
  public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text trigram = new Text();
    public void map(LongWritable key, Text value,
                    OutputCollector<Text, IntWritable> output, Reporter rep) throws IOException {
      String line = value.toString();
      int i = 0;
      while(i < line.length()-3) {
        trigram.set(line.substring(i, i+3));
        output.collect(trigram, one);
        i++;
      }
    }
  }
  public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output, Reporter rep) throws IOException {
      int sum = 0;
      while(values.hasNext()){
        sum += values.next().get();
      }
      output.collect(key, new IntWritable(sum));
    }
  }
  public static void main(String[] args) {
    JobConf job = new JobConf(TriCount.class);
    job.setJobName("TriCount");
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setMapperClass(Map.class);
    job.setCombinerClass(Reduce.class);
    job.setReducerClass(Reduce.class);
    job.setInputFormat(TextInputFormat.class);
    job.setOutputFormat(TextOutputFormat.class);
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    try {
      JobClient.runJob(job);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}

      
