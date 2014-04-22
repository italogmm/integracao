package com.ufg.piadadodiamobile.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.ufg.piadadodiamobile.GCM;
import com.ufg.piadadodiamobile.R;

public class AtivaGCMActivity extends ActionBarActivity {

	TextView piadasRecebidas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ativa_gcm);
		
		if (!GCM.isAtivo(getApplicationContext())) {
			GCM.ativa(getApplicationContext());
        }
		
		if (GCM.isAtivo(getApplicationContext())) {
			piadasRecebidas = (TextView) findViewById(R.id.status);
			piadasRecebidas.setText("Recebimento de piadas ativado!");
			Toast.makeText(getApplicationContext(), "Recebimento de piadas ativado!", Toast.LENGTH_LONG).show();
		}
	}
}