package com.ramghat.newsbychannels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;

import com.ramghat.newsbychannels.R;
import com.ramghat.newsbychannels.adapters.ArticleAdapter;
import com.ramghat.newsbychannels.adapters.ChannelAdapter;
import com.ramghat.newsbychannels.apiclient.ApiClient;
import com.ramghat.newsbychannels.apiclient.ApiInterface;
import com.ramghat.newsbychannels.model.Article;
import com.ramghat.newsbychannels.model.Articles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.LinearLayout.VERTICAL;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);

        Intent intent = getIntent();
        String mChannel = intent.getStringExtra(ChannelAdapter.ChannelViewHolder.SOURCE_CHANNEL);

        final RecyclerView articleView = (RecyclerView) findViewById(R.id.articleRV);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(VERTICAL);
        articleView.setLayoutManager(llm);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(articleView);

        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

        Call<Articles> call = apiClient.getArticles(mChannel, MainActivity.API_KEY);

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                Articles articles = response.body();
                List<Article> articleList = new ArrayList<Article>();

                if(articles != null) {
                    articleList = articles.getArticles();
                }
                ArticleAdapter articleAdapter = new ArticleAdapter(articleList);
                articleView.setAdapter(articleAdapter);
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

            }
        });
    }

}
