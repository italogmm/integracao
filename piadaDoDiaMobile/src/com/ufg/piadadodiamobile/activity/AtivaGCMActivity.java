package com.ufg.piadadodiamobile.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ufg.piadadodiamobile.GCM;
import com.ufg.piadadodiamobile.R;
import com.ufg.piadadodiamobile.bean.Piada;
import com.ufg.piadadodiamobile.modelo.dao.PiadaDAO;

public class AtivaGCMActivity extends ActionBarActivity {

	TextView piadasRecebidas;
	
	ListView lvListagem;
	
	private List<Piada> piadas;
	private ArrayAdapter<Piada> adapter;
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	private static boolean primeiraExecucao = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ativa_gcm);
		
		GCM.ativa(getApplicationContext());
		
		if (GCM.isAtivo(getApplicationContext())) {
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
