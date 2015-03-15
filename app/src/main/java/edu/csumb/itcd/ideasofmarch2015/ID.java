package edu.csumb.itcd.ideasofmarch2015;

import android.app.PendingIntent;
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
import java.util.ArrayList;
import android.nfc.tech.IsoDep;
//import mil.osd.dmdc.ctis.common.data.gsc.Fascn;
//import mil.osd.dmdc.ctis.common.data.piv.Chuid;


//test
public class ID extends ActionBarActivity {


    private static final String TAG = "zMessage";
    private static NfcAdapter mAdapter;
    private static PendingIntent mPendingIntent;
    private static IntentFilter[] mFilters;
    private static String[][] mTechLists;
    //private static Chuid chuid;
    public static int ediPin;
    public static String t;
    ArrayList<Object> EdiList = new ArrayList<Object>();
    ArrayList<Object> scannedEdiList = new ArrayList<Object>();
    ArrayList<Object> Roster = new ArrayList<Object>();

    //ArrayList<Chuid> list;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);

        EdiList.add("2001397721");


        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndef.addDataType("*/*");    /* Handles all MIME based dispatches.
                                           You should specify only the ones that you need. */
        }
        catch (MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] {ndef, };

        mTechLists = new String[][] { new String[] { IsoDep.class.getName() } };
    }


    public void onResume() {
        super.onResume();
        if (mAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Intent intent = getIntent();
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(mAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    Log.i(TAG, msgs[i].toString());
                }
            }
        }
        //process the msgs array
    }


    public void onPause(){
        super.onPause();
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
