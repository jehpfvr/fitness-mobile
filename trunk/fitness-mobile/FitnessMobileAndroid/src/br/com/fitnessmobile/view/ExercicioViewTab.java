package br.com.fitnessmobile.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class ExercicioViewTab extends TabActivity implements OnTabChangeListener {

	private Integer etapaID;
	private Integer diaID;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		etapaID = getIntent().getIntExtra("etapaID", -1);
		diaID = getIntent().getIntExtra("diaID", -1);
		
		TabHost mTabHost = getTabHost();
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 0).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_segunda").setIndicator("S",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 1).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_terca").setIndicator("T",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 2).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_quarta").setIndicator("Q",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 3).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_quinta").setIndicator("Q",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 4).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_sexta").setIndicator("S",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 5).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_sabado").setIndicator("S",null).setContent(intent));
        
        intent = new Intent(this, ExercicioView.class).putExtra("diaID", 6).putExtra("etapaID", etapaID);
        mTabHost.addTab(mTabHost.newTabSpec("tab_domingo").setIndicator("D",null).setContent(intent));

        mTabHost.setCurrentTab(diaID);

        getTabHost().setOnTabChangedListener(this);
	}
	
	public void onTabChanged(String tabId) {
		if (getTabHost().getCurrentTab() == 0) { // Segunda-Feira
			
		}
		if (getTabHost().getCurrentTab() == 1) { // Terca-Feira
			
		}
		if (getTabHost().getCurrentTab() == 2) { // Quarta-Feira
			
		}
		if (getTabHost().getCurrentTab() == 3) { // Quinta-Feira

		}
		if (getTabHost().getCurrentTab() == 4) { // Sexta-Feira

		}
		if (getTabHost().getCurrentTab() == 5) { // Sabado

		}
		if (getTabHost().getCurrentTab() == 6) { // Domingo

		}
	}
}