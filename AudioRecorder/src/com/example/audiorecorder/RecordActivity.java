package com.example.audiorecorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends Activity {

	private MediaRecorder myAudioRecorder;
	private File outputFile = null;
	private TextView textView1;
	private Button start, stop, play, send;
	Long availableSpace;
	Logger logger = Logger.getRootLogger();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_activity);
		start = (Button) findViewById(R.id.buttonRecord);
		stop = (Button) findViewById(R.id.buttonStop);
		play = (Button) findViewById(R.id.buttonPlay);
		send = (Button) findViewById(R.id.buttonSend);
		textView1 = (TextView) findViewById(R.id.textView1);

		stop.setEnabled(false);
		play.setEnabled(false);
		send.setEnabled(false);

		defineAvailableSpace();
		

	}

	private void defineAvailableSpace() {

		logger.info("Define the availble space for dummyUsrName.");
		
		availableSpace = getFreeInternalStorageSize();

		Long size = availableSpace;
		String suffix = "";

		if (size >= 1024) {
			suffix = "kB";
			size /= 1024;
			//        	if (size >= 1024) {
			//        		suffix = "MB";
			//        		size /= 1024;
			//        		if (size >= 1024) {
			//        			suffix = "GB";
			//        			size /= 1024;
			//        		}
			//        	}
		}

		textView1.setText("Available Space: " + size + suffix);

		outputFile = new File(this.getFilesDir(), "myrecording.mp4");
		
		logger.info("Set the output file to: " + outputFile.getName() + " for dummyUsrName");
	}

	public void start(View view){
		logger.info("Prepare to record for dummyUsrName");
		
		try {
			myAudioRecorder = new MediaRecorder();
			myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
			myAudioRecorder.setAudioEncodingBitRate(96000);
			myAudioRecorder.setAudioSamplingRate(44100);
			myAudioRecorder.setOutputFile(outputFile.getAbsolutePath());
			myAudioRecorder.prepare();
			
			logger.info("dummyUsrName Star recording");
			myAudioRecorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start.setEnabled(false);
		stop.setEnabled(true);

		Log.i("Seconds", Long.toString(8 * availableSpace / (44100 * 2 * 1)));

		long totalSeconds = 8 * availableSpace / (44100 * 2 * 1) ;
		long totalMinutes = totalSeconds / 60;
		long totalHours = totalMinutes / 60;

		Log.i("Seconds", Long.toString(totalSeconds));

		totalSeconds = totalSeconds % 60;
		totalMinutes = totalMinutes % 60;

		Toast.makeText(getApplicationContext(), "You could record approximately for " + totalHours + "h:" + totalMinutes + "m:" + totalSeconds + "s", Toast.LENGTH_SHORT).show();

		//		Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();

	}

	public void stop(View view){
		int dummyLength = 10000;
		logger.info("dummyUsrName stopped recording at length " + dummyLength);
		
		myAudioRecorder.stop();
		
		
		encryptingRecordedAudioOnMemory();
		
		saveEncryptedDataToFile();
	}

	private void saveEncryptedDataToFile() {
		
		logger.info("Start saving encrypted data to file for dummyUsrName");
		
		myAudioRecorder.release();
		myAudioRecorder  = null;
		start.setEnabled(true);
		stop.setEnabled(false);
		play.setEnabled(true);
		send.setEnabled(true);
		//		Toast.makeText(getApplicationContext(), "Audio recorded successfully",
		//				Toast.LENGTH_LONG).show();

		// Update the size
		availableSpace = getFreeInternalStorageSize();

		Long size = availableSpace;
		String suffix = "";

		if (size >= 1024) {
			suffix = "kB";
			size /= 1024;
			//        	if (size >= 1024) {
			//        		suffix = "MB";
			//        		size /= 1024;
			//        		if (size >= 1024) {
			//        			suffix = "GB";
			//        			size /= 1024;
			//        		}
			//        	}
		}

		textView1.setText("Available Space: " + size + suffix);
		
		logger.info("Saving encrypted data to file for dummyUsrName has done successfully");
	}

	private void encryptingRecordedAudioOnMemory() {
		
		logger.info("Start encrypting data that is on the memory, for dummyUsrName.");
		
		// encrypting code goes here
		
		logger.info("Encrypted finished for dummyUsrName");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void play(View view) throws IllegalArgumentException,   
	SecurityException, IllegalStateException, IOException{

		MediaPlayer m = new MediaPlayer();

		FileInputStream fileInputStream = new FileInputStream(outputFile.getAbsolutePath());
		m.setDataSource(fileInputStream.getFD());
		m.prepare();
		m.start();
		
		logger.info("dummyUsrName start playing the message");
		//		Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

	}

	public void send(View view)
	{
		logger.info("Showing contact to dummyUsrName");
		
		final CharSequence users[] = new CharSequence[] {"Alice", "Bob", "Christine", "Dennis"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Please select a user");
		builder.setItems(users, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int user) {
				logger.info("dummyUsrName chose " + users[user]);
				selection(users[user]);

			}
		});
		builder.show();
	}

	public void selection(final CharSequence name)
	{
		signAndAttachedHashFunction();
		
		logger.info("Suggesting dummyUsrName to choose Bluetooth.");
		
		final CharSequence medium[] = new CharSequence[] {"Wi-Fi", "4G", "Bluetooth", "NFC"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Please select a transfer medium");
		builder.setItems(medium, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int mediumPosition) {
				Toast.makeText(getApplicationContext(), "You have selected to transfer to " + name.toString() + " using " + medium[mediumPosition] + " as a transfer medium.", Toast.LENGTH_LONG).show();

				logger.info("dummyUsrName has chosen " +  medium[mediumPosition] + " as a medium to send recorded message.");
			}
		});
		
		
		builder.show();
		
		connectingAndSending();
	}

	private void connectingAndSending() {
		logger.info("Start sending message of dummyUsrName to dummyUsrName2");
		
		// connecting code goes here
		
	}

	private void signAndAttachedHashFunction() {
		logger.info("Start signing hash function for dummyUsrName");
		
		// signing hash function code goes here
		
		logger.info("Attaching hash function to message for dummyUsrName");
		
		// attaching hashfunction code goes here
		
		logger.info("Signing hash function done successfully for dummyUsrName");
	}

	public Long getFreeInternalStorageSize()
	{		
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();

		long size = availableBlocks * blockSize;

		return size;

	}

}