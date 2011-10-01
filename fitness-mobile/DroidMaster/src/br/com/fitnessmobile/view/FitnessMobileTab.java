package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class FitnessMobileTab extends TabActivity implements OnTabChangeListener{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();
        Intent intent;
        
        intent = new Intent(this, TabHome.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_home").setIndicator("Home",getResources().getDrawable(android.R.drawable.ic_dialog_dialer)).setContent(intent));
        
        intent = new Intent(this, TabPrograma.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_Programa").setIndicator("Programa",getResources().getDrawable(android.R.drawable.ic_menu_agenda)).setContent(intent));
        
        intent = new Intent(this,TabExercicio.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_exercicios").setIndicator("Exercicios",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent));    
     
        intent = new Intent(this,TabUsuario.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_usuário").setIndicator("Medidas",getResources().getDrawable(R.drawable.ico_tab1)).setContent(intent));
        
        intent = new Intent(this,TabModoVisualizacao.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_modo_visualizacao").setIndicator("Visualizacao",getResources().getDrawable(R.drawable.ico_tab1)).setContent(intent));
        
        
        mTabHost.setCurrentTab(getIntent().getIntExtra("aba", 0));
        
        getTabHost().setOnTabChangedListener(this);
    }

    public void onTabChanged(String tabId) {
		if(getTabHost().getCurrentTab() == 0){
			System.out.println("Selecionou Home");
			startActivity(new Intent(this,FitnessMobileMain.class));
		}
		if(getTabHost().getCurrentTab() == 1){
			System.out.println("Selecionou Programa");
		}
		if(getTabHost().getCurrentTab() == 2){
			System.out.println("Selecionou Exercicios");
		}
		if(getTabHost().getCurrentTab() == 3){
			System.out.println("Selecionou Medidas");
		}
	}
}