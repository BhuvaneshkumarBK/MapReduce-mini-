package unittest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.FloatWritable;
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

public class testcase1 {
	MapDriver<LongWritable, Text, CustomewrittableString, FloatWritable> mapperdriver;
	ReduceDriver<CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable> reducedriver;
	MapReduceDriver<LongWritable, Text, CustomewrittableString, FloatWritable, CustomewrittableString, FloatWritable> mrdriver;

	@Before
	public void setUp() {
		Map mapper = new Map();
		Reducer rd = new Reducer();
		mapperdriver = MapDriver.newMapDriver(mapper);
		reducedriver = ReduceDriver.newReduceDriver(rd);
		mrdriver = MapReduceDriver.newMapReduceDriver(mapper, rd);
	}

	@Test
	public void testmapper() {
		mapperdriver.withInput(new LongWritable(),
				new Text("FKLY490998LB,2010-01-29 06:12:17,Mumbai,Ecommerce,39,13,25-35"));
		mapperdriver.withOutput(new CustomewrittableString("Ecommerce-Mumbai"),
				new FloatWritable(Float.parseFloat("39") / Float.parseFloat("13")));
		
		try {
			mapperdriver.runTest();
		} catch (IOException e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	@Test
	public void testReducer() {
		List<FloatWritable> values = new ArrayList<FloatWritable>();
		values.add(new FloatWritable(3));
		values.add(new FloatWritable(3));
		values.add(new FloatWritable(3));
		values.add(new FloatWritable(3));
		values.add(new FloatWritable(3));
		reducedriver.withInput(new CustomewrittableString("Ecommerce-Mumbai"), values);
		reducedriver.withOutput(new CustomewrittableString("Ecommerce-Mumbai"), new FloatWritable(3));
		try {
			reducedriver.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





