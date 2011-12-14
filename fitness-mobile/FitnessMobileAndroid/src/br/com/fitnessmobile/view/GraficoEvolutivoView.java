package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.GraficoEvolutivoController;

public class GraficoEvolutivoView extends View {

	private Paint paint;

	private Bitmap anatomiaCompleta;
	private Bitmap bicepsEsquerdo;
	private Bitmap bicepsDireito;
	private Bitmap tricepsDireito;
	private Bitmap tricepsEsquerdo;
	private Bitmap peitoral;
	private Bitmap abdomen;
	private Bitmap coxaEsquerda;
	private Bitmap coxaDireita;
	private Bitmap panturrilhaDireita;
	private Bitmap panturrilhaEsquerda;

	GraficoEvolutivoController gec;

	private Context contexto;

	public GraficoEvolutivoView(Context context, String datainicio, String datafim) {
		super(context);
		this.paint = new Paint();

		this.contexto = context;
		this.gec = new GraficoEvolutivoController(context);
		this.gec.geraRelatorio(datainicio, datafim);
	}

	public Boolean verificarHdpi(int largura, int altura){
		if(largura == 100 && altura == 240 || largura == 60 && altura == 427){
			return true;
		}
		else
			return false;
	}

	public Boolean verificarMdpi(int largura, int altura){
		if(largura == 40 && altura == 240 || largura == 60 && altura == 160){
			return true;
		}
		else
			return false;
	}

	public Boolean verificarLdpi(int largura, int altura){
		if(largura == 30 && altura == 160 || largura == 30 && altura == 300 || largura == 30 && altura == 216 || largura == 40 && altura == 120){
			return true;
		}
		else
			return false;
	}

	//Ajuste LDPI resoluções largura == 240 && altura == 320 || largura == 240 && altura == 400 || largura == 240 && altura == 432
	private void ajusteLdpi(Canvas canvas){
		// Imagens da parte da frente
		canvas.drawBitmap(bicepsEsquerdo, anatomiaCompleta.getHeight()-40f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(bicepsDireito, anatomiaCompleta.getHeight()-71f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(peitoral, anatomiaCompleta.getHeight()-64f, anatomiaCompleta.getWidth()-3f, paint);
		canvas.drawBitmap(abdomen, anatomiaCompleta.getHeight()-62f, anatomiaCompleta.getWidth()+5f, paint);
		canvas.drawBitmap(coxaEsquerda, anatomiaCompleta.getHeight()-65f, anatomiaCompleta.getWidth()+28f, paint);
		canvas.drawBitmap(coxaDireita, anatomiaCompleta.getHeight()-49f, anatomiaCompleta.getWidth()+28f, paint);

		// Imagens da parte de trás
		canvas.drawBitmap(tricepsEsquerdo, anatomiaCompleta.getHeight()-5f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(tricepsDireito, anatomiaCompleta.getHeight()+32f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(panturrilhaEsquerda, anatomiaCompleta.getHeight()+22f, anatomiaCompleta.getWidth()+68f, null);
		canvas.drawBitmap(panturrilhaDireita, anatomiaCompleta.getHeight()+0f, anatomiaCompleta.getWidth()+68f, null);
	}
	
	//Ajuste MDPI resoluções largura == 320 && altura == 480
	private void ajusteMdpi(Canvas canvas){
		// Imagens da parte da frente
		canvas.drawBitmap(bicepsEsquerdo, anatomiaCompleta.getHeight()-49f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(bicepsDireito, anatomiaCompleta.getHeight()-90f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(peitoral, anatomiaCompleta.getHeight()-79f, anatomiaCompleta.getWidth()-4f, paint);
		canvas.drawBitmap(abdomen, anatomiaCompleta.getHeight()-77f, anatomiaCompleta.getWidth()+10f, paint);
		canvas.drawBitmap(coxaEsquerda, anatomiaCompleta.getHeight()-78f, anatomiaCompleta.getWidth()+40f, paint);
		canvas.drawBitmap(coxaDireita, anatomiaCompleta.getHeight()-58f, anatomiaCompleta.getWidth()+40f, paint);

		// Imagens da parte de trás
		canvas.drawBitmap(tricepsEsquerdo, anatomiaCompleta.getHeight()+1f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(tricepsDireito, anatomiaCompleta.getHeight()+47f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(panturrilhaEsquerda, anatomiaCompleta.getHeight()+37f, anatomiaCompleta.getWidth()+85f, null);
		canvas.drawBitmap(panturrilhaDireita, anatomiaCompleta.getHeight()+8f, anatomiaCompleta.getWidth()+85f, null);
	}

	//Ajuste para HDPI resoluções largura == 480 && altura == 800 || largura == 480 && altura == 854
	private void ajusteHdpi(Canvas canvas){
		Log.i("Chegou",  "Resolução Grande");
		
		// Imagens da parte da frente
		canvas.drawBitmap(bicepsEsquerdo, anatomiaCompleta.getHeight()-60f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(bicepsDireito, anatomiaCompleta.getHeight()-122f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(peitoral, anatomiaCompleta.getHeight()-110f, anatomiaCompleta.getWidth()-7f, paint);
		canvas.drawBitmap(abdomen, anatomiaCompleta.getHeight()-105f, anatomiaCompleta.getWidth()+12f, paint);
		canvas.drawBitmap(coxaEsquerda, anatomiaCompleta.getHeight()-110f, anatomiaCompleta.getWidth()+61f, paint);
		canvas.drawBitmap(coxaDireita, anatomiaCompleta.getHeight()-77f, anatomiaCompleta.getWidth()+61f, paint);

		// Imagens da parte de trás
		canvas.drawBitmap(tricepsEsquerdo, anatomiaCompleta.getHeight()+10f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(tricepsDireito, anatomiaCompleta.getHeight()+80f, anatomiaCompleta.getWidth()+2f, null);
		canvas.drawBitmap(panturrilhaEsquerda, anatomiaCompleta.getHeight()+65f, anatomiaCompleta.getWidth()+128f, null);
		canvas.drawBitmap(panturrilhaDireita, anatomiaCompleta.getHeight()+20f, anatomiaCompleta.getWidth()+128f, null);
	}

	//Ajuste default
	private void ajuste(Canvas canvas){
		// Imagens da parte da frente
		canvas.drawBitmap(bicepsEsquerdo, anatomiaCompleta.getHeight()-30f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(bicepsDireito, anatomiaCompleta.getHeight()-60f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(peitoral, anatomiaCompleta.getHeight()+29f, anatomiaCompleta.getWidth()+23f, paint);
		canvas.drawBitmap(abdomen, anatomiaCompleta.getHeight()+31f, anatomiaCompleta.getWidth()+35f, paint);
		canvas.drawBitmap(coxaEsquerda, anatomiaCompleta.getHeight()+43f, anatomiaCompleta.getWidth()+58f, paint);
		canvas.drawBitmap(coxaDireita, anatomiaCompleta.getHeight()+28f, anatomiaCompleta.getWidth()+58f, paint);

		// Imagens da parte de trás
		canvas.drawBitmap(tricepsEsquerdo, anatomiaCompleta.getHeight()+87f, anatomiaCompleta.getWidth()+28f, null);
		canvas.drawBitmap(tricepsDireito, anatomiaCompleta.getHeight()+121f, anatomiaCompleta.getWidth()+28f, null);
		canvas.drawBitmap(panturrilhaEsquerda, anatomiaCompleta.getHeight()+117f, anatomiaCompleta.getWidth()+98f, null);
		canvas.drawBitmap(panturrilhaDireita, anatomiaCompleta.getHeight()+91f, anatomiaCompleta.getWidth()+98f, null);
	}
	@Override
	public void onDraw(Canvas canvas) {

		paint.setColor(Color.BLACK);
		canvas.drawPaint(paint);
		paint.setColor(Color.rgb(220, 220, 220));

		/*int pixels = 15;

		for (int i = 0; i < canvas.getHeight(); i += pixels) {
			paint.setColor(Color.GREEN);
			canvas.drawLine(i, 0, i, canvas.getHeight(), paint);
			canvas.drawLine(0, i, canvas.getWidth(), i, paint);
		}
		 */

		if (gec.getBicepsEsquerdoInicial().getTamanho() < gec.getBicepsEsquerdoFinal().getTamanho()) {
			this.bicepsEsquerdo	= BitmapFactory.decodeResource(getResources(), R.drawable.bicepsesquerdo);
		} else {
			this.bicepsEsquerdo	= BitmapFactory.decodeResource(getResources(), R.drawable.bicepsesquerdovermelho);
		}

		if (gec.getBicepsDireitoInicial().getTamanho() < gec.getBicepsDireitoFinal().getTamanho()) {
			this.bicepsDireito = BitmapFactory.decodeResource(getResources(), R.drawable.bicepsdireito);
		} else {
			this.bicepsDireito = BitmapFactory.decodeResource(getResources(), R.drawable.bicepsdireitovermelho);
		}

		if (gec.getTricepsEsquerdoInicial().getTamanho() < gec.getTricepsEsquerdoFinal().getTamanho()) {
			this.tricepsEsquerdo = BitmapFactory.decodeResource(getResources(), R.drawable.tricepsesquerdo);
		} else {
			this.tricepsEsquerdo = BitmapFactory.decodeResource(getResources(), R.drawable.tricepsesquerdovermelho);
		}

		if (gec.getTricepsDireitoInicial().getTamanho() < gec.getTricepsDireitoFinal().getTamanho()) {
			this.tricepsDireito	= BitmapFactory.decodeResource(getResources(), R.drawable.tricepsdireito);
		} else {
			this.tricepsDireito	= BitmapFactory.decodeResource(getResources(), R.drawable.tricepsdireitovermelho);
		}

		if (gec.getPeitoralInicial().getTamanho() < gec.getPeitoralFinal().getTamanho()) {
			this.peitoral = BitmapFactory.decodeResource(getResources(), R.drawable.peito_);
		} else {
			this.peitoral = BitmapFactory.decodeResource(getResources(), R.drawable.peitovermelho);
		}

		if (gec.getAbdomenInicial().getTamanho() < gec.getAbdomenFinal().getTamanho()) {
			this.abdomen = BitmapFactory.decodeResource(getResources(), R.drawable.abdomen_);	
		} else {
			this.abdomen = BitmapFactory.decodeResource(getResources(), R.drawable.abdomenvermelho);
		}

		if (gec.getCoxaEsquerdaInicial().getTamanho() < gec.getCoxaEsquerdaFinal().getTamanho()) {
			this.coxaEsquerda = BitmapFactory.decodeResource(getResources(), R.drawable.coxaesquerda);
		} else {
			this.coxaEsquerda = BitmapFactory.decodeResource(getResources(), R.drawable.coxaesquerdavermelho);
		}

		if (gec.getCoxaDireitaInicial().getTamanho() < gec.getCoxaDireitaFinal().getTamanho()) {
			this.coxaDireita = BitmapFactory.decodeResource(getResources(), R.drawable.coxadireita);
		} else {
			this.coxaDireita = BitmapFactory.decodeResource(getResources(), R.drawable.coxadireitavermelho);
		}

		if (gec.getPanturrilhaEsquerdaInicial().getTamanho() < gec.getPanturrilhaEsquerdaFinal().getTamanho()) {
			this.panturrilhaDireita	= BitmapFactory.decodeResource(getResources(), R.drawable.panturrilhadireita);
		} else {
			this.panturrilhaDireita	= BitmapFactory.decodeResource(getResources(), R.drawable.panturrilhadireitavermelho);
		}

		if (gec.getPanturrilhaDireitaInicial().getTamanho() < gec.getPanturrilhaDireitaFinal().getTamanho()) {
			this.panturrilhaEsquerda = BitmapFactory.decodeResource(getResources(), R.drawable.panturrilhaesquerda);
		} else {
			this.panturrilhaEsquerda = BitmapFactory.decodeResource(getResources(), R.drawable.panturrilhaesquerdavermelho);
		}

		//Pegar informações da tela para fazer o dinâmico
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) contexto).getWindowManager().getDefaultDisplay().getMetrics(dm);

		int largura = dm.heightPixels/8;
		int altura = dm.widthPixels/2;
		Log.i("Tela",""+ largura + "x" +altura);

		this.anatomiaCompleta = BitmapFactory.decodeResource(getResources(), R.drawable.anatomia);
		Bitmap.createScaledBitmap(anatomiaCompleta, largura, altura, false);

		// Imagem da anatomia absoluta
		canvas.drawBitmap(anatomiaCompleta,largura ,altura, paint);

		if(verificarHdpi(largura, altura).equals(true)){
			ajusteHdpi(canvas);
		}
		else if (verificarMdpi(largura, altura).equals(true)){
			ajusteMdpi(canvas);
		}
		else if (verificarLdpi(largura, altura).equals(true)){
			ajusteLdpi(canvas);

		}
		else ajuste(canvas);
	}
}