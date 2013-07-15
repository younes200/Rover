package com.example.rover;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {


	private Spinner devicesSpinner;
	Button btnListPairedDevices;
	BluetoothAdapter bluetoothAdapter;
	TextView textViewState;
	
	 private BluetoothDevice device = null;// le périphérique (le module bluetooth)
	    private BluetoothSocket socket = null;
	    private InputStream receiveStream = null;// Canal de réception
	    private OutputStream sendStream = null;// Canal d'émission
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        devicesSpinner = (Spinner)findViewById(R.id.spinner1);
        textViewState = (TextView)findViewById(R.id.textViewState);	
        

        
        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        CheckBlueToothState();
    }
    
    private void ListPairedDevices(){
    	if(bluetoothAdapter != null){
	        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
	
	        if (pairedDevices.size() > 0) {
		        List<String> s = new ArrayList<String>();
		        for(BluetoothDevice bt : pairedDevices)
		           s.add(bt.getName());
		        
		        devicesSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s));
	        }
        }
    }
    
    private void CheckBlueToothState(){
        if (bluetoothAdapter == null){
        	
			textViewState.setText("Bluetooth NOT supported");
           }else{
            if (bluetoothAdapter.isEnabled()){
             if(bluetoothAdapter.isDiscovering()){
            	 textViewState.setText("Bluetooth is currently in device discovery process.");
             }else{
            	 textViewState.setText("Bluetooth is Enabled.");
              btnListPairedDevices.setEnabled(true);
             }
            }else{
            	textViewState.setText("Bluetooth is NOT Enabled!");
             
            }
           }
     }
    
    
    public void onDiscoverClick(View view){
    	ListPairedDevices();
    }


    


    
}
