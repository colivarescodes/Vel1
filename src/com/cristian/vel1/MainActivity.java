package com.cristian.vel1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
 

public class MainActivity extends SherlockActivity{
	
	TextView 	inputVelocidad,estadoGPSDato,velocidadChico,distancia,patenteMostrar;
	double 		LatitudNuevo = 0;
	double 		LongitudNuevo = 0;
	double 		LatitudViejo = 0;
	double 		LongitudViejo = 0;
	double 		nuevaDistancia;
	long 		tInicial = java.util.Calendar.getInstance().getTimeInMillis();
	long 		tFinal;
	int 		vAnterior = 0;
	int 		vActual = 0;
	boolean mostrarVelocidad = true;
	LocationManager lm;
	LocationListener ll;
	int i;
	int satGeneral;	
	
	private ProgressDialog pd = null;
	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //BASICOS PARA DEL MAIN
        inputVelocidad = (TextView)this.findViewById(R.id.velLinea);
        estadoGPSDato = (TextView)this.findViewById(R.id.estadodato);
        patenteMostrar = (TextView)this.findViewById(R.id.patenteMostrar);
        distancia = (TextView)this.findViewById(R.id.distancia);
        
        Bundle bPatente = this.getIntent().getExtras();
        patenteMostrar.setText(bPatente.getString("Patente"));
        
        //pd = ProgressDialog.show(this, "Obteniendo Satelites", "Obteniendo Satelites minimos \npara funcionar optimamente");
       // inicializaGPS();
    }
	
    
 
    
    
    
    
    //OPCIONES DEL MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
        
        
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        //ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        return true;
    }    
    
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.usuarios) {
			 startActivity(new Intent(this, ctausuario.class));
		}else if(item.getItemId() == R.id.velocidad){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);			
			// startActivity(new Intent(this, MainActivity.class));
		}
		return true;
	}	
}

