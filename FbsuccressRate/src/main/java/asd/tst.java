package asd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import Fbsucc.CustomewrittableString;
import Fbsucc.Map;
import Fbsucc.Reducer;

public class tst {

//Specification of Mapper
	MapDriver<LongWritable, Text, CustomewrittableString, FloatWritable> mapDriver;
//Specification of Reduce
	ReduceDriver<CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable> reduceDriver;
//Specification of MapReduce program
	MapReduceDriver<LongWritable, Text, CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable> mapReduceDriver;

	@Before
	public void setUp() {
		Map mapper = new Map();
		Reducer reducer = new Reducer();
//Setup Mapper
		mapDriver = MapDriver.newMapDriver(mapper);
//Setup Reduce
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
//Setup MapReduce job
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

@Test
public void testMapper() {
//Test Mapper with this input
mapDriver.withInput(new LongWritable(),
		new Text("FKLY490998LB,2010-01-29 06:12:17,Mumbai,Ecommerce,39,13,25-35"));
//Expect this output
mapDriver.withOutput(new CustomewrittableString("Ecommerce-Mumbai"),
		new FloatWritable(Float.parseFloat("39") / Float.parseFloat("13")));

try {
//Run Map test with above input and ouput
mapDriver.runTest();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

	@Test
	public void testReducer() {
		List<FloatWritable> values = new ArrayList<FloatWritable>();
		values.add(new FloatWritable(3));
		
//Run Reduce with this input
		reduceDriver.withInput(new CustomewrittableString("Ecommerce-Mumbai"), values);
//Expect this output
		reduceDriver.withOutput(new CustomewrittableString("Ecommerce-Mumbai"), new FloatWritable(3));
		try {
//Run Reduce test with above input and ouput
			reduceDriver.runTest();
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}