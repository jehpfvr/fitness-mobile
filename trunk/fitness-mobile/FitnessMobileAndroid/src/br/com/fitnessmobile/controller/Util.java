package br.com.fitnessmobile.controller;

import android.app.Activity;
import br.com.fitnessmobile.R;

public class Util extends Activity{

	public static int sTheme;

	public final static int TEMA_DIA = 0;
	public final static int TEMA_NOITE = 1;

	public static void mudarParaTema(Activity activity, int tema)
	{
		sTheme = tema;
		activity.finish();
		//activity.startActivity(new Intent(activity.getApplicationContext(),FitnessMobileMain.class));
	}

	public static void inicioActivitySetTema(Activity activity)
	{
		switch (sTheme)
		{
		case TEMA_DIA:
			activity.setTheme(R.style.TemaDia);	 
			break;
		case TEMA_NOITE:
			activity.setTheme(R.style.TemaNoite);
			break;
		}
	}
}
