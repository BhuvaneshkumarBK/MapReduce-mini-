package chainMapper;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class r extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> itr= arg1.iterator();
        int count=0;
        while(itr.hasNext()) {
        	count=count+itr.next().get();
        }
        context.write(arg0,new IntWritable(count));
	
	}
}
