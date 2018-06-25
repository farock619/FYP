package hextogen.daurm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class lock_panel extends AppCompatActivity {


    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_panel);

        btn1 = findViewById(R.id.lock1_on);
        btn2 = findViewById(R.id.lock1_off);


        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK=Locked");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK=Unlocked");

            }
        });

    }

    public void request(String command){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            String url = "http://192.168.43.151/";

            new RequestedData().execute(url+command);
        }
        else{
            Toast.makeText(lock_panel.this,"No connection detected", Toast.LENGTH_LONG).show();
        }
    }



    private class RequestedData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return connection.getData(url[0]);
        }

    }
}
