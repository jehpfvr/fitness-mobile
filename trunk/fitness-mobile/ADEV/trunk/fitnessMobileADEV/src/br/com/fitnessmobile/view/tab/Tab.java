package br.com.fitnessmobile.view.tab;

import br.com.fitnessmobile.R;

import android.os.Bundle;
import android.app.TabActivity;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.content.Intent;

public class Tab extends TabActivity implements OnTabChangeListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        TabHost mTabHost = getTabHost();
        Intent intent;
        
        // Tabs
        intent = new Intent(this, TabPrograma.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_programa").setIndicator("Programa",getResources().getDrawable(android.R.drawable.ic_menu_agenda)).setContent(intent)); // Aba com ID 0
        
        intent = new Intent(this,TabExercicio.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_exercicios").setIndicator("Exercicios",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent)); // Aba com ID 1

        intent = new Intent(this,TabMedida.class); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_medidas").setIndicator("Medidas",getResources().getDrawable(R.drawable.ico_tab1)).setContent(intent)); // Aba com ID 2
        
        // Pega o valor da Chave "abaSelecionada" da tela Principal (Se não houver nenhum valor, o padrão será '0')
        mTabHost.setCurrentTab(getIntent().getIntExtra("abaSelecionada", 0));
        
        // Evento - Listener
        getTabHost().setOnTabChangedListener(this);
    }

    public void onTabChanged(String tabId) {
		if(getTabHost().getCurrentTab() == 0){
			System.out.println("Selecionou Programa");
		}
		if(getTabHost().getCurrentTab() == 1){
			System.out.println("Selecionou Exercicios");
		}
		if(getTabHost().getCurrentTab() == 2){
			System.out.println("Selecionou Medidas");
		}
	}
}