package FraudFind;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class rduce extends Reducer<Text, writtercus, Text, Text> {
	HashMap<String, String> hashh= new HashMap<String, String>();
	List<Float> li= new ArrayList();

	@Override
	protected void reduce(Text key, Iterable<writtercus> value, Reducer<Text, writtercus, Text, Text>.Context context)
			throws IOException, InterruptedException {
	
		int total = 0;
		int fraud = 0;
		int totalreturn = 0;
		writtercus vale= null;
		Iterator<writtercus> v= value.iterator();
		while (v.hasNext())
		{
		vale=v.next();
			
			total = total + 1;

			String ordered = vale.getOrderdate();
			String returned = vale.getRetundate();

			if (vale.getIsReturned()) {
				totalreturn = totalreturn + 1;
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				    Date receiveDate = sdf.parse(returned);
				    Date returnDate = sdf.parse(ordered);
					
					long diffInMillies = Math.abs(returnDate.getTime() - receiveDate.getTime());
				    long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);     
					System.out.println(diffDays);
					
					if (diffDays > 10) {
						fraud = fraud + 1;

					}

				} catch (ParseException e) {
					e.printStackTrace();

				}

			}

		}
		float fraudper = ((totalreturn * 100) / total);
		li.add(fraudper);

		
		hashh.put((key.toString()), fraudper+","+fraud);

	}
	@Override
	protected void cleanup(Reducer<Text, writtercus, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Collections.sort(li, new Comparator<Float>() {

			public int compare(Float o1, Float o2) {
				System.out.println(o1.compareTo(o2));
				return o1.compareTo(o2);
			}});
		for(Float v:li) {
			System.out.println("li");
			System.out.println(v);
			for(Entry<String, String> v2:hashh.entrySet()) {
				System.out.println("hashh");
				System.out.println(v2.getValue());
				Float two=Float.parseFloat(v2.getValue().split(",")[0]);
				if(Float.compare(v, two)==0&& !(context.getCurrentKey().toString().equals(v2.getKey()))); {
				context.write(new Text(v2.getKey()), new Text(String.valueOf(v2.getValue().split(",")[0])+","+v2.getValue().split(",")[1]));
				}
			}
		}
		
	}
}
