package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabEstatistica extends TabActivity implements OnTabChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();
        Intent intent;
        
        intent = new Intent(this, Estatistica.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_home").setIndicator("Pizza",getResources().getDrawable(android.R.drawable.ic_dialog_dialer)).setContent(intent));
        
        intent = new Intent(this, EstatisticaBarraLinha.class).putExtra("type", "barra");
        mTabHost.addTab(mTabHost.newTabSpec("tab_Programa").setIndicator("Barra",getResources().getDrawable(android.R.drawable.ic_menu_agenda)).setContent(intent));
        
        intent = new Intent(this, EstatisticaBarraLinha.class).putExtra("type", "linha");
        mTabHost.addTab(mTabHost.newTabSpec("tab_exercicios").setIndicator("Linha",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent));    

        intent = new Intent(this, TabGraficoEvolutivo.class).putExtra("type", "linha");
        mTabHost.addTab(mTabHost.newTabSpec("tab_grafico_evolutivo").setIndicator("Grafico Evolutivo",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent));    
        
        mTabHost.setCurrentTab(getIntent().getIntExtra("aba_estatistica", 0));
        
        getTabHost().setOnTabChangedListener(this);
    }

    public void onTabChanged(String tabId) {
		if(getTabHost().getCurrentTab() == 0){
			System.out.println("Selecionou Grafico de Pizza");
		}
		if(getTabHost().getCurrentTab() == 1){
			System.out.println("Selecionou Grafico de Barra");
		}
		if(getTabHost().getCurrentTab() == 2){
			System.out.println("Selecionou Grafico de Linha");
		}
	}

}
