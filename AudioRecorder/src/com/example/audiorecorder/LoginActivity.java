package com.example.audiorecorder;

import org.apache.log4j.Logger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	Logger logger = Logger.getRootLogger();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConfigureLog4J.configure(this.getFilesDir() + "activity.log");
		logger = Logger.getRootLogger();
		
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
				
				
				String dummyUsrName = "";
				String dummyPswd = "";
				if (firstRun) {
					
					registerAccount(dummyUsrName, dummyPswd);
					
					Toast.makeText(getApplicationContext(), "You have successfully registered!", Toast.LENGTH_SHORT).show();
					settings.edit().putBoolean("firstRun", false).commit();
				} else {
					
					if(authenticateUser(dummyUsrName, dummyPswd)){
						
						defineAccessRightsTo(dummyUsrName);
						
						Toast.makeText(getApplicationContext(), "You have successfully logged in!", Toast.LENGTH_SHORT).show();
					}
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

	private void defineAccessRightsTo(String dummyUsrName) {
		
		logger.info("define this " + dummyUsrName + "'s access rights to file system and recording hardwares.");
		
		// code to define access rights to this user goes here
		
	}

	private boolean authenticateUser(String dummyUsrName, String dummyPswd) {

		logger.info(dummyUsrName +" trying to authenticate");
		
		// autheication code goes here
		
		logger.info("Checking passcode for " +dummyUsrName);
		
		logger.info(dummyUsrName +" has typed passcode correctly.");
		
		logger.info(dummyUsrName +" has authenticated successfully");
		
		return true;
	}

	private void registerAccount(String usr, String pwd) {
		logger.info("Registring user account for " + usr);

		encryptAndStoreUsernamePasswordForNewAccount(usr, pwd);
		
		logger.info("New account for " + usr + " has been done successfully.");
		// register code goes here
	}

	private void encryptAndStoreUsernamePasswordForNewAccount(String usr, String pwd) { 
		logger.info("Encrypting username and password for " + usr);
		
		// encrypt uasername and password code goes here

		logger.info("Encrypting username and password was done successfully for " + usr);
		
		logger.info("Saving encrypted username and password into secured database for " + usr);
		// store encrypted username and password into a secure encrypted database code goes here
		
		logger.info("Storing encrypted username and password for " + usr + " was done successfully");
	}
	
}
