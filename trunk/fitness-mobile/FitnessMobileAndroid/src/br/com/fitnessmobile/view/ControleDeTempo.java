package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.fitnessmobile.R;

public class ControleDeTempo extends Activity implements OnClickListener {

	Chronometer cronometro;
	ImageButton btnStart;
	ImageButton btnStop;
	ImageButton btnReset;
	TextView tempo;

	long tempoDecorrido = 0;
	String tempoAtual = "";
	Boolean continuar = false;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controle_de_tempo);
		cronometro = (Chronometer) findViewById(R.id.chrono);
		btnStart = (ImageButton) findViewById(R.id.btnStart);
		btnStop = (ImageButton) findViewById(R.id.btnStop);
		btnReset = (ImageButton) findViewById(R.id.btnReset);
		tempo = (TextView) findViewById(R.id.txt);

		cronometro
				.setOnChronometerTickListener(new OnChronometerTickListener() {

					public void onChronometerTick(Chronometer arg0) {

						if (!continuar) {

							long minutes = ((SystemClock.elapsedRealtime() - cronometro
									.getBase()) / 1000) / 60;
							long seconds = ((SystemClock.elapsedRealtime() - cronometro
									.getBase()) / 1000) % 60;
							tempoAtual = minutes + ":" + seconds;
							arg0.setText(tempoAtual);
							tempoDecorrido = SystemClock.elapsedRealtime();
						} else {

							long minutes = ((tempoDecorrido - cronometro.getBase()) / 1000) / 60;
							long seconds = ((tempoDecorrido - cronometro.getBase()) / 1000) % 60;
							tempoAtual = minutes + ":" + seconds;
							arg0.setText(tempoAtual);
							tempoDecorrido = tempoDecorrido + 1000;
						}

					}

				});

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnStart:
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			if (!continuar) {
				cronometro.setBase(SystemClock.elapsedRealtime());
				cronometro.start();
			} else {

				cronometro.start();
			}

			break;
		case R.id.btnStop:
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			cronometro.stop();
			cronometro.setText(tempoAtual);
			continuar = true;
			break;
		case R.id.btnReset:

			cronometro.stop();
			cronometro.setText("00:00");
			continuar = false;
			btnStop.setEnabled(false);
			break;
		}
	}

}