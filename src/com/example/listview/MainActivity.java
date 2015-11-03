package com.example.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ListView listViewSMS;
    Cursor cursor;
    SMSListAdapter smsListAdapter;
    Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		listViewSMS=(ListView) findViewById(R.id.listViewSMS);
		
		cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
	
		// Create the Adapter
        smsListAdapter=new SMSListAdapter(this,cursor);
       
        // Set The Adapter to ListView
        listViewSMS.setAdapter(smsListAdapter);
        
     // to handle click event on listView item
        listViewSMS.setOnItemClickListener(new OnItemClickListener()
        {
                public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
                {
                    // when user clicks on ListView Item , onItemClick is called
                    // with position and View of the item which is clicked
                    // we can use the position parameter to get index of clicked item
                    TextView textViewSMSSender=(TextView)v.findViewById(R.id.textViewSMSSender);
                    TextView textViewSMSBody=(TextView)v.findViewById(R.id.textViewMessageBody);
                    String smsSender=textViewSMSSender.getText().toString();
                    String smsBody=textViewSMSBody.getText().toString();
                   
                    // Show The Dialog with Selected SMS
                    AlertDialog dialog = new AlertDialog.Builder(context).create();
                    dialog.setTitle("SMS From : "+smsSender);
                    dialog.setIcon(android.R.drawable.ic_dialog_info);
                    dialog.setMessage(smsBody);
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                       
                                dialog.dismiss();
                                return;
                    }   
                    });
                    dialog.show();
                }
            });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
