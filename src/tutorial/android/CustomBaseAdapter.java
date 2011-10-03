package tutorial.android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter{

	private static LayoutInflater inflator = null;
	
	private ArrayList<HashMap<String,String>> list;
	
	private static ViewHolder holder;
	
	private Context context;
	
	public CustomBaseAdapter(Context context, ArrayList<HashMap<String,String>> list){
		
		
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		this.list = list;
		
		this.context = context;
		
	}
	
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return id;
	}

	
	public static class ViewHolder{
		
		public TextView text1;
		public TextView text2;
		public TextView text3;
		public TextView text4;
		
		public ImageView image;
		public ImageView image1;
		public ImageView image2;

		
	}
	
	
	public View getView(final int position, View convertView, ViewGroup vG) {
		
		View vi = convertView;
		
		if(vi == null){
			
			vi = inflator.inflate(R.layout.list_contacto, null);
			
			holder = new ViewHolder();
			
			holder.text1 = (TextView) vi.findViewById(R.id.text1);
			holder.text2 = (TextView) vi.findViewById(R.id.text2);
			holder.text3 = (TextView) vi.findViewById(R.id.text3);
			holder.text4 = (TextView) vi.findViewById(R.id.text4);
			
			holder.image = (ImageView) vi.findViewById(R.id.image);
			holder.image1 = (ImageView) vi.findViewById(R.id.image1);
			holder.image2 = (ImageView) vi.findViewById(R.id.image2);			
			vi.setTag(holder);
			
		}else{
			
			holder = (ViewHolder)vi.getTag();
			
		}
		
		holder.text1.setText(list.get(position).get(contacto.CONTACTO));
		holder.text2.setText(list.get(position).get(contacto.ANEXO));
		holder.text3.setText(list.get(position).get(contacto.TELF_FIJO));
		holder.text4.setText(list.get(position).get(contacto.CORREO));
		
		holder.image.setImageResource(R.drawable.phone);
		holder.image1.setImageResource(R.drawable.iphone);
		holder.image2.setImageResource(R.drawable.envelope);
		
		
		holder.image.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					Log.v("TAG", "Mensaje: "+v+" / "+position);
					
					call(list.get(position).get(contacto.TELF_FIJO), context);
					
				}
		});
	
		holder.image1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Log.v("TAG", "Mensaje: "+v+" / "+position);
				
				call(list.get(position).get(contacto.ANEXO), context);
				
			}
		});
		
		holder.image2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				email(list.get(position).get(contacto.CORREO), context);	
				
			}
		});
		
		
		return vi;
	}
	
	
	
	
	private static void call(String nro_phone, Context context) {
    	try {
    		Intent callIntent = new Intent(Intent.ACTION_CALL);
    		callIntent.setData(Uri.parse("tel:"+nro_phone));
    		context.startActivity(callIntent);
    	} catch (ActivityNotFoundException e) {
           Log.e("Llamada teléfonica", "Fallo llamada teléfonica", e);
        }
    }
    
    private static void email(String email, Context context) {
    	final Intent emailIntent = new Intent(Intent.ACTION_SEND);  
    	emailIntent.setType("text/plain");  
    	emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});    
    	emailIntent.putExtra(Intent.EXTRA_SUBJECT, "asunto sobre android");  
    	emailIntent.putExtra(Intent.EXTRA_TEXT, "texto sobre android");  
    	context.startActivity(Intent.createChooser(emailIntent, "Enviando correo desde android ...")); 
    	
    }

}
