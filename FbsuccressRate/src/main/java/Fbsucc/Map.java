package Fbsucc;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, CustomewrittableString, FloatWritable> {
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomewrittableString, FloatWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("haiii");

		String combination[] = value.toString().split(",");
		String keyyer = combination[3] + "-" + combination[2];
		float val = Float.parseFloat(combination[4]) / Float.parseFloat(combination[5]);
		context.write(new CustomewrittableString(keyyer), new FloatWritable(val));
		
	}

}
