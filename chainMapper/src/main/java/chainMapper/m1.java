package chainMapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class m1 extends Mapper<LongWritable, Text, Text, IntWritable>{
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
		throws IOException, InterruptedException {
	String values= value.toString().replaceAll("\\s", "");
	String k=values.substring(0, values.length()-1);
	System.out.println(k);
	System.out.println(value.toString());
	int v=Integer.parseInt(values.substring(k.length(), values.length()));
	context.write(new Text(k.substring(0,1)), new IntWritable(v));
}
}
