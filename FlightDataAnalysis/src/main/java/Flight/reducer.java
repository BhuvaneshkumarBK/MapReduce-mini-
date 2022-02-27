package Flight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducer extends Reducer<Text, Text, Text, Text> {
	List<String> topFlights= new ArrayList<String>();
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Reducer<Text, Text, Text, Text>.Context con)
			throws IOException, InterruptedException {
		int totaltrips = 0;
		int totalcustome=0;
		int jantrip=0;
		int jancust=0;

		Iterator<Text> itr = arg1.iterator();
		while (itr.hasNext()) {
			String values[] = itr.next().toString().split(",");
			totaltrips = totaltrips + 1;
			totalcustome=totalcustome+Integer.parseInt(values[3]);
			if(values[4].contains("Jan")) {
				con.getCounter("Jan Counter", "Jan").increment(1);
				jantrip=jantrip+1;
				jancust=jancust+Integer.parseInt(values[3]);
			}
		}
		if(totaltrips>25 && totalcustome<3000) {
			System.out.println("no flights sorry");
			con.write(arg0, new Text(totaltrips+","+totalcustome+"hai"));
		}
		float avg=totalcustome/totaltrips;
		System.out.println(totaltrips);
		System.out.println(totalcustome);
		topFlights.add(arg0.toString()+","+avg);
		
		
		con.getCounter("Jan Counter", "Trip").increment(jantrip);
		con.getCounter("Jan Counter", "cust").increment(jancust);
		con.getCounter("Jan Counter", "Avg").increment(jantrip/jancust);
		System.out.println(topFlights.size());
		

	}
	
	@Override
	protected void cleanup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		
		
		Collections.sort(topFlights, new Comparator<String>() {

			public int compare(String o1, String o2) {
				Float s1=Float.parseFloat(o1.split(",")[1]);
				Float s2=Float.parseFloat(o2.split(",")[1]);
				return s1.compareTo(s2);
			}
			
		});
		for(int x=0; x<3;x++) {
		context.write(new Text(topFlights.get(x).split(",")[0]),new Text(topFlights.get(x).split(",")[1]));
	}}

}
