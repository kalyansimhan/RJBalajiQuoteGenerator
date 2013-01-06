package com.apv.rjbalajiquote;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.util.Log;

public class QuoteReader {

	private static final String filePath = "quotes";
	static List<String> quotes;
	Context context;

	public static void readQuotes(Context context) {
		Log.d("CrossTalk", "readQuotes");
		quotes = new ArrayList<String>();
		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.quotes);
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String quote = bufferedReader.readLine();
			while (quote != null) {
				Log.d("CrossTalk", quote);
				quotes.add(quote);
				Log.d("CrossTalk", "" + quotes.size());
				quote = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getNext() {
		Random random = new Random();
		int nextInt = random.nextInt(quotes.size());
		Log.d("CrossTalk", "" + nextInt);
		return quotes.get(nextInt);
	}
}
