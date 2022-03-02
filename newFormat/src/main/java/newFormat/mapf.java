package newFormat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class mapf extends Mapper<LongWritable, Text, Text, Text>{
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	System.out.println("ddd");
	System.out.println(value.toString().split(",")[0]);
	System.out.println(value.toString().split(",")[1]);
	context.write(new Text(value.toString().split(",")[0]), new Text(value.toString().split(",")[1]));
}
}
