package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class FitnessMobileTab extends TabActivity implements OnTabChangeListener{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        
        
        TabHost mTabHost = getTabHost();
        Intent intent;
        
        intent = new Intent(this, TabHome.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_home").setIndicator("Home",getResources().getDrawable(android.R.drawable.ic_dialog_dialer)).setContent(intent));
        

        intent = new Intent(this, TabPrograma.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_Programa").setIndicator("Programa",getResources().getDrawable(android.R.drawable.ic_menu_agenda)).setContent(intent));
        
        intent = new Intent(this,TabMedidas.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_medidas").setIndicator("Medidas",getResources().getDrawable(R.drawable.ico_tab1)).setContent(intent));
        
        intent = new Intent(this,TabExercicios.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_exercicios").setIndicator("Exercicios",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent));
        
        /*
        intent = new Intent(this,ExercicioAerobicoView.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_corrida").setIndicator("Corrida",getResources().getDrawable(R.drawable.ico_tab_exer)).setContent(intent));
        */
       
        mTabHost.setCurrentTab( getIntent().getIntExtra("aba", 0));
        
        getTabHost().setOnTabChangedListener(this);
    }


	public void onTabChanged(String tabId) {
		
		if(getTabHost().getCurrentTab() == 0){
			startActivity(new Intent(this,FitnessMobileMain.class));
		}
		
	}
}