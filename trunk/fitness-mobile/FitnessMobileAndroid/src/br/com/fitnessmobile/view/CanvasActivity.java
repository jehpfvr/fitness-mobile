package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;

public class CanvasActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(new GraficoEvolutivoView(this, getIntent().getStringExtra("DataInicio"),getIntent().getStringExtra("DataFim")));
    }
}