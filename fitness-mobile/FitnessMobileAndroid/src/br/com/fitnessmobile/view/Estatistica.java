package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class Estatistica extends Activity implements OnClickListener{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView googleChartView = new WebView(this);
		setContentView(googleChartView);
		String mUrl = "http://chart.apis.google.com/chart?cht=p3&chd=t:30,60&chs=320x120&chl=feito|programado";
		googleChartView.loadUrl(mUrl);
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
