package br.com.fitnessmobile.view;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import br.com.fitnessmobile.R;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class LoginView extends Activity {
    /** Called when the activity is first created. */
	private Facebook facebook = new Facebook("163858517033730");
	private String[] permissions = new String[]{"email"};
	
	
	//Usado para conexões assincronas
	private AsyncFacebookRunner asyncRunner = new AsyncFacebookRunner(facebook);
	//Preference usado localmente para guardar as informações
	private SharedPreferences preferences ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
                
    	final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        
        final Intent intent = new Intent(this, FitnessMobileMain.class);
        
        preferences = getPreferences(MODE_PRIVATE);
        
        String access_token = preferences.getString("access_token", null);
        long expires = preferences.getLong("expires", 0);
        
        if(access_token != null){
        	facebook.setAccessToken(access_token);
        }
        
        if (expires != 0){
        	facebook.setAccessExpires(expires);
        }
        
        if (!facebook.isSessionValid()){ 
        	
        //Solicito a autorização do face passando minha activity e o array de permissões
        facebook.authorize(this,permissions, new DialogListener() {
			
			public void onFacebookError(FacebookError e) {
				// TODO Auto-generated method stub
				
				dlgAlert.setMessage("Erro interno no facebook");
				dlgAlert.setTitle("Erro facebook");
				dlgAlert.setPositiveButton("OK", null);
		        dlgAlert.setCancelable(true);
				dlgAlert.create().show();
				
			}
			
			public void onError(DialogError e) {
				// TODO Auto-generated method stub
				
				dlgAlert.setMessage("Ocorreu um erro, tente novamente");
				dlgAlert.setTitle("Erro!");
				dlgAlert.setPositiveButton("OK", null);
		        dlgAlert.setCancelable(true);
				dlgAlert.create().show();
				
			}
			
			//Quando terminar de Autorizar vem para esse método!
			public void onComplete(Bundle values) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("access_token", facebook.getAccessToken());
				editor.putLong("access_expires", facebook.getAccessExpires());
				editor.commit();
				
				startActivity(intent);
				
			}
			
			public void onCancel() {
				// TODO Auto-generated method stub
				
			}
		});
       try {
		JSONObject response = Util.parseJson(facebook.request("me"));
		String nome = response.getString("name");
		
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (FacebookError e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    }
        
  }
}