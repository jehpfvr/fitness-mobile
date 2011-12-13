package br.com.fitnessmobile.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import br.com.fitnessmobile.R;

public class FitnessMobileTab extends TabActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        Log.i("aba que chegou", "" + getIntent().getIntExtra("aba", 0));

        TabHost mTabHost = getTabHost();
        Intent intent;

        intent = new Intent(this, TabPrograma.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_Programa").setIndicator("Programa",getResources().getDrawable(R.drawable.ic_programa)).setContent(intent));
        
        intent = new Intent(this,TabExercicio.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_exercicios").setIndicator("Exercicios",getResources().getDrawable(R.drawable.ic_tab_exer)).setContent(intent));    
     
        intent = new Intent(this,TabMedidas.class);
        mTabHost.addTab(mTabHost.newTabSpec("tab_medidas").setIndicator("Medidas",getResources().getDrawable(R.drawable.ic_tab_medidas)).setContent(intent));
 
        mTabHost.setCurrentTab(getIntent().getIntExtra("aba", 0));
    }

}