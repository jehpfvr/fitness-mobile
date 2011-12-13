package br.com.fitnessmobile.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import br.com.fitnessmobile.R;

public class TabEstatistica extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();
        Intent intent;
        
        intent = new Intent(this, Estatistica.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_esta_exer").setIndicator("Exercicios",getResources().getDrawable(R.drawable.ic_tab_exer)).setContent(intent));
        
        intent = new Intent(this, TabGraficoEvolutivo.class).putExtra("type", "linha");
        mTabHost.addTab(mTabHost.newTabSpec("tab_grafico_evolutivo").setIndicator("Grafico Evolutivo",getResources().getDrawable(R.drawable.ic_tab_estatistica)).setContent(intent));    
        
        mTabHost.setCurrentTab(getIntent().getIntExtra("aba_estatistica", 0));
    }
}