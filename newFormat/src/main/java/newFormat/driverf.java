package newFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driverf {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(driverf.class);

		job.setInputFormatClass(xmlInputFormat.class);
		job.setMapperClass(mapf.class);
		job.setReducerClass(redf.class);

		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		System.out.println(job.waitForCompletion(true));

	}
}
