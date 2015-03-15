package edu.csumb.itcd.ideasofmarch2015;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.nfc.*;
import android.util.Log;
import android.os.Parcelable;

import net.sf.scuba.smartcards.CardServiceException;

import java.nio.charset.Charset;



public class ID extends ActionBarActivity {

    private static final String TAG = "zMessage";
    private static IntentFilter[] mFilters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
    }


    public void onResume() {
        super.onResume();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            Intent intent = getIntent();
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    Log.e(TAG, msgs[i].toString());
                }
            }
        }
        //process the msgs array
    }

    /*
    public void scanID() {
        boolean success = false;
        if (success == true) {
            Intent intent = new Intent(this, ID_Info.class);
            startActivity(intent);
        }
        else {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
                Parcelable[] rawMsgs;
                try {
                    rawMsgs = Intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                } catch (CardServiceException e) {
                    Log.i(TAG, "exception");
                }
                if (rawMsgs != null) {
                    msgs = new NdefMessage[rawMsgs.length];
                    for (int i = 0; i < rawMsgs.length; i++) {
                        msgs[i] = (NdefMessage) rawMsgs[i];
                        Log.i(TAG, msgs[i]);
                    }
                }
            }
        }
    }
    */

    public void F_BackID(View view){
        Intent intent = new Intent(this, ScanDoc.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_id, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
