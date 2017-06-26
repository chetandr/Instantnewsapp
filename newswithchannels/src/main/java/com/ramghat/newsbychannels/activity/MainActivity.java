package com.ramghat.newsbychannels.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ramghat.newsbychannels.R;
import com.ramghat.newsbychannels.adapters.ChannelAdapter;
import com.ramghat.newsbychannels.apiclient.ApiClient;
import com.ramghat.newsbychannels.apiclient.ApiInterface;
import com.ramghat.newsbychannels.model.Source;
import com.ramghat.newsbychannels.model.Sources;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public final static String API_KEY = "0ea0143e1a7c42b5a2c47cce7a956424";

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView instantMsg = (TextView) findViewById(R.id.instantmsg);

        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Sources> call = apiService.getSources(API_KEY);
        call.enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                instantMsg.setVisibility(View.INVISIBLE);
                Sources sources = response.body();
                List<Source> sourceList = new ArrayList<Source>();
                Log.d(TAG, "Source " + sources);
                instantMsg.setText("Response");
                if (sources != null) {
                    sourceList = sources.getSources();
                    instantMsg.setText("Sources " + sourceList);
                }
                instantMsg.setText("Sources Null");
                ChannelAdapter adapter = new ChannelAdapter(sourceList);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {
                instantMsg.setVisibility(View.VISIBLE);
                instantMsg.setText("Message : "+ t.getLocalizedMessage());
            }
        });


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
