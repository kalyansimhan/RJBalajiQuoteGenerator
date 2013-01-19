package com.apv.rjbalajiquote;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.OnCreateOptionsMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends Activity implements
		OnCreateOptionsMenuListener, OnOptionsItemSelectedListener {

	String theRealQuote = "";
	String sideDish = "( via @RJ_Balaji )"; // theivaammeeee
	TextView theRealQuoteTextView;
	ShareActionProvider actionProvider;
	Context context;

	ActionBarSherlock mSherlock = ActionBarSherlock.wrap(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		QuoteReader.readQuotes(this);

		mSherlock
				.setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
		mSherlock.setContentView(R.layout.activity_main);

		((TextView) findViewById(R.id.contribute))
				.setMovementMethod(LinkMovementMethod.getInstance());

	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		return mSherlock.dispatchOptionsItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("CrossTalk", "here");
		switch (item.getItemId()) {
		case 0:
			theRealQuote = QuoteReader.getNext();
			if ("".equals(theRealQuote)) {
				theRealQuote = getResources().getString(R.string.bio);
			}
			theRealQuoteTextView = (TextView) findViewById(R.id.theRealQuote);
			theRealQuoteTextView.setText(theRealQuote);
			return true;
		case 1:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, theRealQuote + " " + sideDish);
			intent.setType("text/plain");
			startActivity(intent);
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		boolean dispatchCreateOptionsMenu = mSherlock
				.dispatchCreateOptionsMenu(menu);
		Log.d("CrossTalk", "dispatchCreateOptionsMenu "
				+ dispatchCreateOptionsMenu);
		return dispatchCreateOptionsMenu;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("CrossTalk", "Menu Creation");
		menu.add(0, 0, 0, "Generate").setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM
						| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		menu.add(0, 1, 0, "Share")
				.setIcon(android.R.drawable.ic_menu_share)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}
}
