package br.com.fitnessmobile.service;

import android.text.format.DateFormat;

public class Cronometro extends Thread {
	
	private long milissegundos;
	private boolean isPausado;
	private OnCronometroListener listener;



	public Cronometro() {
		this.milissegundos = 0;
		this.isPausado =false;
	}
	
	public void startCronometro(){
		if(isPausado){
			this.isPausado = false;
			synchronized (this) {
				notifyAll();
			}
		}else{
			this.isPausado = false;
			this.start();
		}
	}
	
	
	public void stopCronometro(){
		this.isPausado = true;
		synchronized (this) {
			//this.milissegundos -= 1000; seria para sincronizar melhor... no pausa e inicio.
			notifyAll();
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			
			try {
				this.verificaPausa();
				this.milissegundos += 1000;
				if(listener != null) listener.onCronometro(this);
				sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}		
	
	}
	

	private void verificaPausa() throws InterruptedException {
		while(isPausado){
			synchronized (this) {
				wait();
			}
		}
		
	}

	public long getMilissegundos() {
		return milissegundos;
	}
	
	public long getSegundos(){
		return milissegundos/1000;
	}
	
	public long getHoras(){
		return milissegundos / 60000;
	}
	
	public CharSequence getText(){
		return DateFormat.format("mm:ss", milissegundos);
	}
	
	public CharSequence getTextnoFormato(String formato){
		return DateFormat.format(formato, milissegundos);
	}

	public void setOnCronometroListener(OnCronometroListener listener) {
		this.listener = listener;
	}
	
	public void clear(){
		this.milissegundos = 0;
		if(listener != null) listener.onCronometro(this);
	}
	
	

}
