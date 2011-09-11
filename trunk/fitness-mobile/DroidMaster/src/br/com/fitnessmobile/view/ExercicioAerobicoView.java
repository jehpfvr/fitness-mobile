package br.com.fitnessmobile.view;

import java.text.DecimalFormat;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.EstatisticaGPS;
import br.com.fitnessmobile.service.MapOverlay;
import br.com.fitnessmobile.service.OnControladorGPSListener;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class ExercicioAerobicoView extends MapActivity implements OnClickListener,ServiceConnection, OnControladorGPSListener, OnTouchListener{

	protected static final int MSG_ATUALIZAR = 1;
	private TextView tv_distancia;
	private TextView tv_velocidade;
	private TextView tv_duracao;
	private Button btn_iniciar;
	private Button btn_parar;
	private EstatisticaGPS dados;
	private ControladorGPS controlador;
	private DecimalFormat df;
	private ViewFlipper vf;
	private float downXValue;
	private LinearLayout linearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico);

		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
		
		this.instanciarViews();
	}
 

	private void instanciarViews() {
		this.dados = new EstatisticaGPS();
		
		this.linearLayout = (LinearLayout) findViewById(R.id.layout_aerobico);
		this.linearLayout.setOnTouchListener(this);
		
		this.vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
		this.vf.setOnTouchListener(this);
		
		this.df = new DecimalFormat("0.00");
		this.tv_distancia = (TextView) findViewById(R.id.exer_aero_display_distancia);
		this.tv_velocidade = (TextView) findViewById(R.id.exer_aero_display_velocidade);
		this.tv_duracao = (TextView) findViewById(R.id.exer_aero_display_duracao);
		

		this.btn_iniciar = (Button) findViewById(R.id.exer_aero_btn_iniciar);
		this.btn_parar = (Button) findViewById(R.id.exer_aero_btn_parar);
		
	
		
		this.btn_iniciar.setOnClickListener(this);
		this.btn_parar.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		if(controlador != null )this.controlador.setOnControladorGPS(this);
		super.onResume();
	}

	
	
	
	@Override
	protected void onDestroy() {
		if(controlador != null )this.controlador.removerOnControladorGPS(this);
		unbindService(this);
		super.onDestroy();
	}
	

	public void onClick(View v) {
		Log.v("click", "Click");
		
		Button button = (Button) v;
		
		if(button.getText().equals(getString(R.string.Pausar))){
			this.btn_iniciar.setText(R.string.Iniciar);
			controlador.stopGPS();
		}else if(v == btn_parar){
			controlador.stopGPS();
			
			Intent intent = new Intent();
			intent.putExtra("distancia", dados.getDistancia());
			intent.putExtra("tempo", dados.getTempoEmAndamento());
			intent.putExtra("velocidade", dados.getVelocidadeMaxima());
			setResult(1, intent);
			finish();
			
			
		}else if(v == btn_iniciar){
			this.btn_iniciar.setText(R.string.Pausar);
			controlador.startGPS();
		}
	}


	public void onServiceConnected(ComponentName name, IBinder service) {
		LocalBinder binder = (LocalBinder) service;
		this.controlador = binder.getControlador();
		this.controlador.setOnControladorGPS(this);
	}

	public void onServiceDisconnected(ComponentName name) {
		this.controlador = null;
	}
	
	
	public void onControladorGPS(EstatisticaGPS dados) {	
		this.dados = dados;
		handler.sendEmptyMessage(0);
	}
	
	private Handler handler = new Handler() {
        public void  handleMessage(Message msg) {
        	tv_distancia.setText(df.format(dados.getDistancia()));
    		tv_velocidade.setText(df.format(dados.getVelocidade()));
    		tv_duracao.setText(DateFormat.format("mm:ss", dados.getTempoEmAndamento()));
   
        }
   };

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}


	public boolean onTouch(View v, MotionEvent m) {
		Log.v("click", "Click motion" + m.getAction());
		 // Get the action that was done on this touch event
        switch (m.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                // store the X value when the user's finger was pressed down
                downXValue = m.getX();
                break;
            }

            case MotionEvent.ACTION_UP:
            {
                // Get the X value when the user released his/her finger
                float currentX = m.getX();            

                // going backwards: pushing stuff to the right
                if (downXValue < currentX)
                {
                     // Set the animation
                      vf.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
                      // Flip!
                      vf.showPrevious();
                }

                // going forwards: pushing stuff to the left
                if (downXValue > currentX)
                {
                     // Set the animation
                     vf.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
                      // Flip!
                     vf.showNext();
                }
                break;
            }
        }

		return true;
	}
	
	

	

};


