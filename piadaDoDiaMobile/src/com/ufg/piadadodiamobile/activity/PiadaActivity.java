package com.ufg.piadadodiamobile.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ufg.piadadodiamobile.R;
import com.ufg.piadadodiamobile.bean.Piada;
import com.ufg.piadadodiamobile.modelo.dao.PiadaDAO;

public class PiadaActivity extends Activity {
	
	ListView lvListagem;
	
	private List<Piada> piadas;
	private ArrayAdapter<Piada> adapter;
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	private void carregarLista(){
		PiadaDAO piadaDao = new PiadaDAO(this);
		piadas = piadaDao.listar();
		piadaDao.close();
		
		this.adapter = new ArrayAdapter<Piada>(this, adapterLayout, piadas);
		this.lvListagem.setAdapter(adapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        
		setContentView(R.layout.list_piadas);
		
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		carregarLista();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		setContentView(R.layout.list_piadas);
		
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		carregarLista();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
}
