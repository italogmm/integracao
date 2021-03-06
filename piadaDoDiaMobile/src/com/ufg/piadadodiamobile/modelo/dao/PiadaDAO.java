package com.ufg.piadadodiamobile.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ufg.piadadodiamobile.bean.Piada;

public class PiadaDAO extends SQLiteOpenHelper{
	
	private static final int VERSAO = 1;
	private static final String TABELA = "Piada";
	private static final String DATABASE = "PiadaDoDiaMobile";
	
	private static final String TAG = "CADASTRO_PIADAS";
	
	public PiadaDAO(Context context){
		super(context, DATABASE, null, VERSAO);
	}
			
	public PiadaDAO(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE " + TABELA + "( id INTEGER PRIMARY KEY, corpo TEXT, dataRecebimento NUMERIC)";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public List<Piada> listar(){
		
		List<Piada> lista = new ArrayList<Piada>();
		
		String sql = "Select * from " + TABELA + " order by dataRecebimento ";
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while(cursor.moveToNext()){
				Piada piada = new Piada();
				
				piada.setId(cursor.getLong(0));
				piada.setCorpo(cursor.getString(1));
				piada.setTimeDataRecebimento(cursor.getLong(2));
				
				lista.add(piada);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		
		Log.i(TAG, "LISTANDO PIADAS");
		return lista;
	}
	
	public Piada consultar(long id){
		
		String sql = "Select * from " + TABELA + " where id = " + id;
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while(cursor.moveToNext()){
				Piada piada = new Piada();
				
				piada.setId(cursor.getLong(0));
				piada.setCorpo(cursor.getString(1));
				piada.setTimeDataRecebimento(cursor.getLong(2));
				
				return piada;
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		
		return null;
	}
	
	public void cadastrar(Piada piada){
		piada.setTimeDataRecebimento(new Date().getTime());
		
		ContentValues values = new ContentValues();
		
		values.put("corpo", piada.getCorpo());
		values.put("dataRecebimento", piada.getTimeDataRecebimento());
		
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "PIADA CADASTRADA: " + piada.getCorpo());
	}
}
