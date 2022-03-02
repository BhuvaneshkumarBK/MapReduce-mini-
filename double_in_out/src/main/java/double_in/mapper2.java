package double_in;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class mapper2 extends Mapper<Text, Text, Text, IntWritable>{
@Override
protected void map(Text key, Text value, Mapper<Text, Text, Text, IntWritable>.Context context)
		throws IOException, InterruptedException {
	context.write(value, new IntWritable(1));
}
}
