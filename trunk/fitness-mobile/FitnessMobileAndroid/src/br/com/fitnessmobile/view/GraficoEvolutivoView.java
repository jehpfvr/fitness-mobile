package br.com.fitnessmobile.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
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

		this.anatomiaCompleta = BitmapFactory.decodeResource(getResources(), R.drawable.anatomia);
		Bitmap.createScaledBitmap(anatomiaCompleta, 420, 640, false);

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

//		Display display = ((WindowManager) contexto.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//		int altura = display.getWidth();
//		int largura = altura * anatomiaCompleta.getHeight()/anatomiaCompleta.getWidth();

		float altura = Math.round(canvas.getWidth()/2);
		float largura = Math.round(canvas.getHeight()/4);
		

		// Imagem da anatomia absoluta
		canvas.drawBitmap(anatomiaCompleta,largura ,altura, paint);

		// Imagens da parte da frente
		canvas.drawBitmap(bicepsEsquerdo, anatomiaCompleta.getHeight()-30f, anatomiaCompleta.getWidth()+1f, null);
		canvas.drawBitmap(bicepsDireito, anatomiaCompleta.getHeight()-60f, anatomiaCompleta.getWidth()+1f, null);
//		canvas.drawBitmap(peitoral, anatomiaCompleta.getHeight()+29f, anatomiaCompleta.getWidth()+23f, paint);
//		canvas.drawBitmap(abdomen, anatomiaCompleta.getHeight()+31f, anatomiaCompleta.getWidth()+35f, paint);
//		canvas.drawBitmap(coxaEsquerda, anatomiaCompleta.getHeight()+43f, anatomiaCompleta.getWidth()+58f, paint);
//		canvas.drawBitmap(coxaDireita, anatomiaCompleta.getHeight()+28f, anatomiaCompleta.getWidth()+58f, paint);

		// Imagens da parte de trás
//		canvas.drawBitmap(tricepsEsquerdo, anatomiaCompleta.getHeight()+87f, anatomiaCompleta.getWidth()+28f, null);
//		canvas.drawBitmap(tricepsDireito, anatomiaCompleta.getHeight()+121f, anatomiaCompleta.getWidth()+28f, null);
//		canvas.drawBitmap(panturrilhaEsquerda, anatomiaCompleta.getHeight()+117f, anatomiaCompleta.getWidth()+98f, null);
//		canvas.drawBitmap(panturrilhaDireita, anatomiaCompleta.getHeight()+91f, anatomiaCompleta.getWidth()+98f, null);
	}

}