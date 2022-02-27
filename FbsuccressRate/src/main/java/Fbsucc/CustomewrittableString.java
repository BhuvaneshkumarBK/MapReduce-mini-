package Fbsucc;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomewrittableString implements WritableComparable<CustomewrittableString> {

	private String word;

	public CustomewrittableString() {

		set("");
	}

	public CustomewrittableString(String text) {

		set(text);
	}

	public void set(String text) {
		this.word = text;
	}

	public String getWord() {
		return this.word;
	}
  
	public void readFields(DataInput arg0) throws IOException {
		
		this.word=WritableUtils.readString(arg0);
	}
   
	public void write(DataOutput arg0) throws IOException {
		WritableUtils.writeString(arg0, word);
	}
  

	public int compareTo(CustomewrittableString o) {
		return this.word.compareTo(o.getWord());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getWord();
	}
}
