package com.example.audiorecorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.login_activity_main, container,
					false);
			return rootView;
		}
	}
	
	public void buttonClicked(View v)
	{
		EditText text = (EditText) findViewById(R.id.passwordEditText);
		
		switch (v.getId()) {

		case (R.id.buttonClear):
			text.setText("");
		break;
		case (R.id.buttonEnter):
			
			if (text.length() == 0) {
				Toast.makeText(getApplicationContext(), "Your passcode cannot be empty!", Toast.LENGTH_SHORT).show();
			} else {
				text.setText("");

				SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
				boolean firstRun = settings.getBoolean("firstRun", true);
				
				if (firstRun) {
					Toast.makeText(getApplicationContext(), "You have successfully registered!", Toast.LENGTH_SHORT).show();
					settings.edit().putBoolean("firstRun", false).commit();
				} else {
					Toast.makeText(getApplicationContext(), "You have successfully logged in!", Toast.LENGTH_SHORT).show();
				}

				Intent startNewActivityOpen = new Intent(LoginActivity.this, RecordActivity.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		break;
		default:
			Editable selectedButtonDigit = text.getText();

			Button button = (Button) v;
			String buttonText = button.getText().toString();

			text.setText(selectedButtonDigit + buttonText);
			text.setSelection(text.length());
			break;
		}
	}

}
