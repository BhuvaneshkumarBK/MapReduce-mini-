package Fbsucc;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;

public class Reducer extends org.apache.hadoop.mapreduce.Reducer<CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable> {
	@Override
	protected void reduce(CustomewrittableString key, Iterable<FloatWritable> values,
			org.apache.hadoop.mapreduce.Reducer<CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable>.Context context)
			throws IOException, InterruptedException {
Float sum=0f;
int count=0;
for(FloatWritable value: values) {
	sum=value.get()+sum;
	count+=1;
	
}
Float avg=sum/count;
context.write(key, new FloatWritable(avg));
	}

}
