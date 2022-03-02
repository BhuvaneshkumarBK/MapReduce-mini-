package chainMapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class map2 extends Mapper<Text, IntWritable, Text, IntWritable>{
	@Override
	protected void map(Text key, IntWritable value, Mapper<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
context.write(new Text(key.toString().toLowerCase()), value);	}

}
