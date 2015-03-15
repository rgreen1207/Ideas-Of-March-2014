package edu.csumb.itcd.ideasofmarch2015;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.jmrtd.PassportApduService;
import org.jmrtd.PassportService;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CardTerminalListener;
import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.smartcards.WrappingCardService;

public class Passport extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
    }


    public void scanPass(){
        boolean success = false;
        if(success == true){
            Intent intent = new Intent(this, Passport_Info.class);
            startActivity(intent);
        }
        else{
            CardService service = null;
            try {
                service = new PassportApduService(service);
            } catch (CardServiceException e) {
                e.printStackTrace();
            }
            PassportService pass = null;
            try {
                pass = new PassportService(service);
            } catch (CardServiceException e) {
                e.printStackTrace();
            }
            short var = 0;
            if (pass != null) {
                try {
                    pass.open();
                    pass.getInputStream(var);
                } catch (CardServiceException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void F_BackP(View view){
        Intent intent = new Intent(this, ScanDoc.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_passport, menu);
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
