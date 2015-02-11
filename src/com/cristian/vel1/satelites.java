package com.cristian.vel1;

import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.GpsStatus;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
public class satelites extends Activity {
	LocationManager locM;
	LocationListener locListener;
	TextView sat, lati, longi;
	String patenteFinal;
	int i;
	int satGeneral;
	
	public void run(){
		//setContentView(R.layout.satelites);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.satelites);
		
		sat = (TextView)this.findViewById(R.id.sat);
		lati = (TextView)this.findViewById(R.id.lati);
		longi = (TextView)this.findViewById(R.id.longi);
		
		Bundle bundle = this.getIntent().getExtras();
		patenteFinal = bundle.getString("Patente"); 
		//patenteFinal = bundle.
		
		
		locM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		//Satelites 
		MyGpsListener listener = new MyGpsListener();
		locM.addGpsStatusListener(listener);      
		
		locListener = new misMovimientos();
        locM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener); 
        
        if (locM.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	//estadoGPSDato.setTextColor(Color.GREEN);
        	//estadoGPSDato.setText("Encendido");
        	Toast.makeText(this, "GPS Habilitado", Toast.LENGTH_LONG).show();
        }else{
        	showGPSDisabledAlertToUser();
        }		
	}
	@Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
	private void mostrarInfo(){
		//Toast.makeText(getApplicationContext(), "Va a saltar!", Toast.LENGTH_LONG).show();

		Intent intent = new Intent(satelites.this, MainActivity.class);
        
        //Bundle bundle = this.getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "Va a saltar!", Toast.LENGTH_SHORT).show();
        Bundle b = new Bundle();
        b.putString("Patente", patenteFinal);
        intent.putExtras(b);
		onDestroy();
        startActivity(intent);
	}
	public class MyGpsListener implements GpsStatus.Listener{

		@Override
		public void onGpsStatusChanged(int event) {
			// TODO Auto-generated method stub
			i=0;
			satGeneral=0;
			 if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
				final GpsStatus gs = locM.getGpsStatus(null);			
				Iterable<GpsSatellite> sats = gs.getSatellites();
				Iterator it = sats.iterator() ;
				 while (it.hasNext()){
					 GpsSatellite oSat = (GpsSatellite) it.next() ; 
					 if (oSat.usedInFix()){
						 i++;
						 
					 }
					 
					 satGeneral++;
					 sat.setText(""+i+" de "+satGeneral);
					 if(i >= 3){
						 //lm = null;
						 //locM.removeUpdates(locListener);
						 //mostrarInfo();
						 //detenListen();
						 //break
						 //lm = null;
						 
				        /* Intent intent = new Intent(satelites.this, MainActivity.class);
				            
				         //Bundle bundle = this.getIntent().getExtras();
				         Toast.makeText(getApplicationContext(), "Va a saltar!", Toast.LENGTH_SHORT).show();
				         Bundle b = new Bundle();
				         b.putString("Patente", patenteFinal);
				         intent.putExtras(b);
				         finish();
				         startActivity(intent);	*/	    	
					}
				}
			 }
		}
		
	}
	
    private class misMovimientos implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			if(location != null){
				double latimue = location.getLatitude();
				double longimue = location.getLongitude();
				lati.setText(""+latimue);
				longi.setText(""+longimue);
				if(i>=3){
					Toast.makeText(getApplicationContext(), "Satelites Usados: "+i, Toast.LENGTH_SHORT).show();
					locM.removeUpdates(locListener);
					mostrarInfo();
				}
				
			}
		}
		@Override
		public void onProviderDisabled(String provider) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}
    	
    }
	

		
		/*public void updateSats() {
		    final GpsStatus gs = lm.getGpsStatus(null);
		    int i = 0;
		    final Iterator<GpsSatellite> it = gs.getSatellites().iterator();
		    while( it.hasNext() ) {
		        it.next();
		        i += 1;
		    }
		    //this.gpsSatsAvailable = i;3
		    sat.setText(""+i);
		    if(i >= 3){
	            Intent intent = new Intent(satelites.this, MainActivity.class);
	            
	            //Bundle bundle = this.getIntent().getExtras();
	            
	            Bundle b = new Bundle();
	            b.putString("Patente", patenteFinal);
	            intent.putExtras(b);
	            //b.put
	            //intent.putExtra(name, value)
	            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);		    	
		    }
		}	
		
		
		public void onGpsStatusChanged(final int event) {
		    switch( event ) {
		        // ...
		    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
		        updateSats();
		        break;
		    }
		}*/

	
	
	//EN CASO QUE EL USUARIO NO TENGA EL GPS ACTIVADO
	
	private void showGPSDisabledAlertToUser(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("El GPS se encuentra DESHABILITADO, para el correcto funcionamiento debe habilitarlo, Desea realizarlo? ")
		.setCancelable(false)
		.setPositiveButton("Habilitar ahora!",
		new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int id){
		Intent callGPSSettingIntent = new Intent(
		android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(callGPSSettingIntent);
		}
		});
		alertDialogBuilder.setNegativeButton("Cancelar",
		new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int id){
		Toast.makeText(getApplicationContext(), "La aplicacion requiere de GPS Encendido", Toast.LENGTH_LONG).show();
		dialog.cancel();
		}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}	
	
}
