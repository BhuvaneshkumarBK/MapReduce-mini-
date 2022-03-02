package chainMapper;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(driver.class);
	    ChainMapper.addMapper(job, map1.class, LongWritable.class, Text.class, Text.class, IntWritable.class, conf);
	    ChainMapper.addMapper(job, map2.class, Text.class, IntWritable.class, Text.class, IntWritable.class, conf);
    ChainReducer.setReducer(job, red.class, Text.class, IntWritable.class, Text.class, IntWritable.class, conf);

		job.setInputFormatClass(TextInputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);

		System.out.println(job.waitForCompletion(true));
		
		Configuration conf2 = new Configuration();
		Job job2 = new Job(conf);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		job2.setJarByClass(driver.class);
		job2.setMapperClass(m1.class);
		job2.setReducerClass(r.class);
		FileInputFormat.addInputPath(job2, new Path(args[1].toString().concat("/part-r-00000")));
		FileOutputFormat.setOutputPath(job2, new Path(args[2]));
		
		System.out.println(job2.waitForCompletion(true));


	}
}
