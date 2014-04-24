package com.ufg.piadadodiamobile.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ufg.piadadodiamobile.GoogleCloudMessaging;
import com.ufg.piadadodiamobile.R;
import com.ufg.piadadodiamobile.VisualizaPiada;
import com.ufg.piadadodiamobile.bean.Piada;
import com.ufg.piadadodiamobile.modelo.dao.PiadaDAO;

public class PrincipalPiadaDoDia extends ActionBarActivity {

	ListView lvListagem;
	
	private List<Piada> piadas;
	private ArrayAdapter<Piada> adapter;
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	private static boolean primeiraExecucao = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ativa_gcm);
		
		GoogleCloudMessaging.ativa(getApplicationContext());
		
		if (GoogleCloudMessaging.isAtivo(getApplicationContext())) {
			Toast.makeText(getApplicationContext(), "Recebimento de piadas ativado!", Toast.LENGTH_LONG).show();
			
			carregarLista();
		}else{
			Toast.makeText(getApplicationContext(), "Recebimento de piadas n‹o ativado!", Toast.LENGTH_LONG).show();
		}
	}
	
	private void carregarLista(){
		PiadaDAO piadaDao = new PiadaDAO(this);
		piadas = piadaDao.listar();
		piadaDao.close();
		
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		this.adapter = new ArrayAdapter<Piada>(this, adapterLayout, piadas);
		this.lvListagem.setAdapter(adapter);
		lvListagem.setOnItemClickListener(new OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> Parent, View view, int position,
	                long id) {
	        	
	        	Piada piadaItem = (Piada)lvListagem.getItemAtPosition((int)id);
	        	
	        	Intent intent = new Intent(PrincipalPiadaDoDia.this, VisualizaPiada.class);
	        	Bundle sendBundle = new Bundle();
	            sendBundle.putLong("idPiada", piadaItem.getId());
	            intent.putExtras(sendBundle);
	            
	        	PrincipalPiadaDoDia.this.startActivity(intent);
	        }});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		setContentView(R.layout.activity_ativa_gcm);
		
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		carregarLista();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		
		if(!primeiraExecucao){
			primeiraExecucao = false;
			finish();
		}
	}
}
