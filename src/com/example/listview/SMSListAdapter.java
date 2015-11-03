package com.example.listview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SMSListAdapter extends BaseAdapter {
	
	   private Context mContext;
	    Cursor cursor;
	    public SMSListAdapter(Context context,Cursor cur)
	    {
	            super();
	            mContext=context;
	            cursor=cur;
	          
	    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return cursor.getCount();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view = inflater.inflate(R.layout.listview_each_item, null);
         
         // move the cursor to required position
         cursor.moveToPosition(position);
        
         // fetch the sender number and sms body from cursor
         String senderNumber=cursor.getString(cursor.getColumnIndex("address"));
         String smsBody=cursor.getString(cursor.getColumnIndex("body"));
       
         // get the reference of textViews
         TextView textViewConatctNumber=(TextView)view.findViewById(R.id.textViewSMSSender);
         TextView textViewSMSBody=(TextView)view.findViewById(R.id.textViewMessageBody);
        
         // Set the Sender number and smsBody to respective TextViews
         textViewConatctNumber.setText(senderNumber);
         textViewSMSBody.setText(smsBody);
        

         return view;
	}

}
