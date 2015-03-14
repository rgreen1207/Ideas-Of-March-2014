package edu.csumb.itcd.ideasofmarch2015;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BarCode extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
    }

    public void F_BackBar(View view){
        Intent intent = new Intent(this, ScanCode.class);
        startActivity(intent);
    }

    /* Add a QR scanner and uncomment this. Upon scanning have it switch to true and go to the next intent.
    public void ScanBar(){
        boolean success = false;
        if(success == true)
        {
            Intent intent = new Intent(this, Bar_Success.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, QR_Fail.class);
            startActivity(intent);
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar_code, menu);
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
