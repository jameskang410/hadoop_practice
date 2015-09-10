package me.jameskang.WordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private IntWritable totalWordCount = new IntWritable();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
		throws IOException, InterruptedException {
		
		int wordCount = 0;
		
		for (IntWritable v : values) {
			wordCount += v.get();
		}
		
		totalWordCount.set(wordCount);
		context.write(key, totalWordCount);
	}
}
