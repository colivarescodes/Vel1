package com.cristian.vel1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class patente extends Activity {
	EditText patente1;
	@Override 
	//patente1 = ediText patente1;
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patente);
        final EditText patente1 = (EditText) findViewById(R.id.patente1);
        final EditText patente2 = (EditText) findViewById(R.id.patente2);
        final EditText patente3 = (EditText) findViewById(R.id.patente3);
        Button btnEnviar = (Button)findViewById(R.id.btnEnviar);
 
        btnEnviar.setOnClickListener(new OnClickListener() {
        	String patenteCompleta = patente1.getText().toString()+"-"+patente2.getText().toString()+"-"+patente3.getText().toString();
        	public void onClick(View v) {
        		 Intent intent = new Intent(patente.this, MainActivity.class);
        		 Bundle b = new Bundle();
        		 b.putString("Patente", patenteCompleta);
        		 intent.putExtras(b);
                 startActivity(intent);
        	}
        });
        
    }

}
