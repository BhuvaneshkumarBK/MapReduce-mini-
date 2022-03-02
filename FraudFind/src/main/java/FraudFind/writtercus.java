package FraudFind;



import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class writtercus implements Writable {

	public String orderdate;
	private String retundate;
	public boolean isReturned;

	writtercus(String text) {

		String[] values = text.toString().split(",");
		this.orderdate = values[0].trim();
		this.retundate = values[1].trim();
		if (((values[2].trim()).equals("yes"))) {
			this.isReturned = true;
		} else {
			this.isReturned = false;

		}
	}

	writtercus() {
		orderdate = "";
		retundate = "";
		this.isReturned = false;
	}

	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, this.orderdate);
		WritableUtils.writeString(out, this.retundate);
		out.writeBoolean(this.isReturned);

	}
   
	public void readFields(DataInput in) throws IOException {
		this.orderdate = WritableUtils.readString(in);
		this.retundate = WritableUtils.readString(in);
		this.isReturned = in.readBoolean();


	}

	public String getOrderdate() {

		return orderdate;
	}

	public String getRetundate() {
		return retundate;
	}

	public boolean getIsReturned() {
		return isReturned;
	}
	
}
