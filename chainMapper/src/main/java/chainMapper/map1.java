package chainMapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class map1 extends Mapper<LongWritable, Text, Text, IntWritable> {
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws java.io.IOException, InterruptedException {
		String[] values= value.toString().split(" ");
		for(String v:values) {
			context.write(new Text(v), new IntWritable(1));
		}
		
	};

}
