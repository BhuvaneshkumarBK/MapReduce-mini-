package double_in;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Path in= new Path(args[0]);
		Path in2= new Path(args[1]);
		Path out= new Path(args[2]);
		
		Configuration conf= new Configuration();
		conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");

		Job job= new Job(conf);
		job.setJobName("bhuviin");
		job.setJarByClass(driver.class);
		job.setMapperClass(map.class);
		job.setMapperClass(mapper2.class);
		job.setReducerClass(red.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		MultipleInputs.addInputPath(job, in, TextInputFormat.class,map.class );
		MultipleInputs.addInputPath(job, in2, KeyValueTextInputFormat.class,mapper2.class);
		
		MultipleOutputs.addNamedOutput(job, "1", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "2", TextOutputFormat.class, Text.class, IntWritable.class);
		FileOutputFormat.setOutputPath(job, out);
		
		
		System.out.println(job.waitForCompletion(true)?"bro done!!"+job.getJobName():"Opps problem!!!!!!");
	}

}
