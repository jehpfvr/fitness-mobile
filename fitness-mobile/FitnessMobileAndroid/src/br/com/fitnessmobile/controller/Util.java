package br.com.fitnessmobile.controller;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.view.FitnessMobileMain;
import android.app.Activity;
import android.content.Intent;


public class Util {
	
	public static int sTheme;
	
	public final static int TEMA_DIA = 0;
	public final static int TEMA_NOITE = 1;
	
	public static void mudarParaTema(Activity activity, int tema)
	{
		sTheme = tema;
        //activity.finish();
		activity.startActivity(new Intent(activity, FitnessMobileMain.class));
	}
	
	public static void inicioActivitySetTema(Activity activity)
	{
		switch (sTheme)
		{
		case TEMA_DIA:
			activity.setTheme(R.style.Button_dia);	 
			
			break;
		case TEMA_NOITE:
			activity.setTheme(R.style.Button_noite);
			break;
		}
	}
}
