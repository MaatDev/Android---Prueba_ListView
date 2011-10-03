package tutorial.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class contacto extends ListActivity{
	
	
	// Campos del HashMap
	public static final String CONTACTO = "contacto";
	public static final String ANEXO = "anexo";
	public static final String TELF_FIJO = "telf.fijo";
	public static final String CORREO = "correo";
	
	private CustomBaseAdapter customAdapter;
	
	
	
	private static final String LOGID = "CHECKTHISOUT";
    static final ArrayList<HashMap<String,String>> list = 
		new ArrayList<HashMap<String,String>>();
    
	private static Context context; 

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
//        setListAdapter(new IconicAdapter());

//        SimpleAdapter adapter = new SimpleAdapter(
//        	this,
//        	list,
//        	R.layout.list_contacto,
//        	new String[] {"contacto","anexo","telf.fijo","correo"},
//        	new int[] {R.id.text1,R.id.text2, R.id.text3, R.id.text4}
//        );     
//        populateList();        
//        setListAdapter(adapter); 
        
        populateList();
        setCustomAdapterToList();
   
    }
    
    public void setCustomAdapterToList(){
    	
    	this.customAdapter = new CustomBaseAdapter(this, list);
    	
    	if(this.customAdapter != null){
    		
    		setListAdapter(this.customAdapter);
    		
    		getListView().setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					Log.v("TAG", "Estoy en nose"+view);
					
					AlertDialog.Builder dialog = new AlertDialog.Builder(contacto.this);
					dialog.setTitle(list.get(position).get("contacto"));
					dialog.setMessage(String.valueOf(list.get(position).values()));
					dialog.show();
					
				}
			});
    		
    		
    		
    	}
    	
    	
    	
    	
    }
    
    
   

    private void populateList() {
    	
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	map.put(CONTACTO,"Alfa Romeo");
    	map.put(ANEXO, "123");
    	map.put(TELF_FIJO, "4825120");
    	map.put(CORREO, "Seidi_Olazabal@hotmail.com");
       	list.add(map);          	
    	map = new HashMap();
    	map.put(CONTACTO,"Audi");
    	map.put(ANEXO, "789");
    	map.put(TELF_FIJO,"");
    	map.put(CORREO, "BillSoriano@gmail.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"BMW");
    	map.put(ANEXO, "4567");
    	map.put(TELF_FIJO, "");
    	map.put(CORREO, "");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Bugatti");
    	map.put(ANEXO, "");
    	map.put(TELF_FIJO, "");
    	map.put(CORREO, "lolazabal@wayraperu.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Dodge");
    	map.put(ANEXO, "456");
    	map.put(TELF_FIJO, "2548814");
    	map.put(CORREO, "");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Ferrari");
    	map.put(ANEXO, "7654");
    	map.put(TELF_FIJO, "5710968");
    	map.put(CORREO, "BillSoriano@hotmail.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Honda");
    	map.put(ANEXO, "321");
    	map.put(TELF_FIJO,"");
    	map.put(CORREO, "paco_sebastian@hotmail.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Nissan");
    	map.put(ANEXO, "4376767,31317");
    	map.put(TELF_FIJO,"");
    	map.put(CORREO, "milisoriano@hotmail.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Subaru");
    	map.put(ANEXO, "531");
    	map.put(TELF_FIJO,"");
    	map.put(CORREO, "russ_soriano_r@yahoo.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Toyota");
    	map.put(ANEXO, "31323");
    	map.put(TELF_FIJO,"4376767");
    	map.put(CORREO, "Seidi_Olazabal@hotmail.com");
    	list.add(map);
    	map = new HashMap();
    	map.put(CONTACTO,"Volkswagen");
    	map.put(ANEXO, "31324");
    	map.put(TELF_FIJO,"4376767");
    	map.put(CORREO, "BillSoriano@gmail.com");
    	list.add(map);
    	
    	System.out.println("Los elementos de HashMap son");
    	Set list= map.keySet();
    	Iterator it = list.iterator();
    	while(it.hasNext()) {
    		System.out.println(it.next());
        	Object clave = it.next();
        	Object valor = map.get(clave);
        	System.out.println("Key: " + clave + " Value: " + valor);
    	}
    	
    }
    
    private void call(String nro_phone) {
    	try {
    		Intent callIntent = new Intent(Intent.ACTION_CALL);
    		callIntent.setData(Uri.parse("tel:"+nro_phone));
    		startActivity(callIntent);
    	} catch (ActivityNotFoundException e) {
           Log.e("Llamada teléfonica", "Fallo llamada teléfonica", e);
        }
    }
    
    private void email(String email) {
    	final Intent emailIntent = new Intent(Intent.ACTION_SEND);  
    	emailIntent.setType("text/plain");  
    	emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});    
    	emailIntent.putExtra(Intent.EXTRA_SUBJECT, "asunto sobre android");  
    	emailIntent.putExtra(Intent.EXTRA_TEXT, "texto sobre android");  
    	startActivity(Intent.createChooser(emailIntent, "Enviando correo desde android ...")); 
    	
    }
    
}
