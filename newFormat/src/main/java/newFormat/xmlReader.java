package newFormat;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;



public class xmlReader extends RecordReader<LongWritable, Text> {
	private long startposition;
	private long endposition;
	private long currentposition = 0;
	private LineReader lineReader;
	private Text value= new Text();
	private LongWritable key= new LongWritable();
	private String startTag = "<MOVIES>";
	private String endTag="</MOVIES>";

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		FileSplit filesplit = (FileSplit) split;
		System.out.println(Arrays.toString(split.getLocations()));

		

		Path file = filesplit.getPath();
		System.out.println(file+"j");
		FileSystem fs = file.getFileSystem(conf);
		FSDataInputStream in = fs.open(file);
		startposition = filesplit.getStart();
		endposition = filesplit.getLength() + startposition;
		in.seek(startposition);
		lineReader = new LineReader(in, conf);
		this.currentposition = startposition;
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		value.clear();
		Text line= new Text();
		System.out.println(currentposition);



		boolean started=false;
		while(currentposition<endposition) {
			long lineNumber= lineReader.readLine(line);

			currentposition=lineNumber+currentposition;

			if(!started&&line.toString().trim().equalsIgnoreCase(startTag)) {
				started=true;
			}
			else if(started){
				String S1 = line.toString();
				String content = S1.replaceAll("<[^>]+>", ""); 
				value.append(content.getBytes("utf-8"), 0, content.length());
				value.append(",".getBytes("utf-8"), 0, ",".length());			}
			else if(started && line.toString().trim().equalsIgnoreCase(endTag)){
				value.set(value.toString().substring(0, value.toString().length()-1));
				return true;
			}

		}
		return false;
	}

	@Override
	public LongWritable getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return (currentposition - startposition) / (float) (endposition - startposition);	}

	@Override
	public void close() throws IOException {
		if (lineReader != null) {
			
		    lineReader.close();}
	}

}
