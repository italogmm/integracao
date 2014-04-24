package com.ufg.piadadodiamobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ufg.piadadodiamobile.bean.Piada;
import com.ufg.piadadodiamobile.modelo.dao.PiadaDAO;

public class VisualizaPiada extends Activity {
	
	TextView txtViewPiada;
	TextView textViewData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiza_piada);
		
		Bundle receiveBundle = this.getIntent().getExtras();
		final long idPiada = receiveBundle.getLong("idPiada");
		
		PiadaDAO piadaDao = new PiadaDAO(this);
		Piada consultada = piadaDao.consultar(idPiada);
		 
		txtViewPiada = (TextView)findViewById(R.id.textViewPiada);
		txtViewPiada.setText(consultada.getCorpo());
		
		textViewData = (TextView)findViewById(R.id.textViewData);
		textViewData.setText(consultada.getDataFormatada());
	}
}
