package double_in;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class red extends Reducer<Text, IntWritable, Text, IntWritable> {
    private MultipleOutputs<Text, Text> out;
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
        int count=0;    
		for(IntWritable x: arg1) {
            count=count+1;	
            }
		if(count>1) {
			out.write("1",arg0, new IntWritable(count));
	}
		else{
			out.write("2",arg0, new IntWritable(count));

			}
		}
	

}
