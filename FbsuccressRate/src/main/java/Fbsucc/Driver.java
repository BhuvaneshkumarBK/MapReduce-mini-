package Fbsucc;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {
	private static final Log log = LogFactory.getLog(Driver.class);

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		try {
			log.info("started");
			Configuration conf = new Configuration();

			log.info("done with conf");

			Job job = new Job(conf);
			job.setJarByClass(Driver.class);
			job.setJobName("Fb");
			log.info("done with jobset");

			log.info("done with inputFormat");

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			log.info("done with  format");

			// job.setMapOutputKeyClass(CustomewrittableString.class);
			// job.setMapOutputValueClass(FloatWritable.class);
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));

			job.setMapperClass(Map.class);
			job.setReducerClass(Reducer.class);

			job.setOutputKeyClass(CustomewrittableString.class);
			job.setOutputValueClass(FloatWritable.class);
			System.out.println(job.waitForCompletion(true) ? "bro done!!" + job.getJobName() : "Opps problem!!!!!!");
		} catch (Exception e) {
			log.error("error", e);
		}

	}

}
