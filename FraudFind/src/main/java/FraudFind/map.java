package FraudFind;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class map extends Mapper<LongWritable, Text, Text, writtercus> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, writtercus>.Context context)
			throws IOException, InterruptedException {
String values[]=value.toString().split(",");
String cutName=values[1];
String ordered=values[3];
String returned=values[7];
String isreturned=values[6];
context.write(new Text(cutName), new writtercus(ordered+","+returned+","+isreturned));
	}
}
