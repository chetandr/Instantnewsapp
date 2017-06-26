package com.ramghat.newsbychannels.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramghat.newsbychannels.R;
import com.ramghat.newsbychannels.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chetan_rane on 5/31/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder>{

    private List<Article> articles;
    private Context context;
    public ArticleAdapter(List<Article> articleList) {
        this.articles = articleList;
    }

    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(this.context).inflate(R.layout.article_card, viewGroup, false);
        ArticleHolder articleHolder = new ArticleHolder(view);

        return articleHolder;
    }

    @Override
    public void onBindViewHolder(ArticleHolder articleHolder, int i) {
        articleHolder.title.setText(articles.get(i).getTitle());
        articleHolder.desc.setText(articles.get(i).getDescription());
        articleHolder.author.setText(articles.get(i).getAuthor());
        articleHolder.date.setText(articles.get(i).getPublishedAt());
        articleHolder.url.setText(articles.get(i).getUrl());
        Picasso.with(this.context).load(articles.get(i).getUrlToImage()).into(articleHolder.articleImage);
    }

    public class ArticleHolder extends RecyclerView.ViewHolder{
        CardView articleCard;
        TextView title, desc, author, date, url;
        ImageView articleImage;
        ArticleHolder(View itemView) {
            super(itemView);

            articleCard = (CardView) itemView.findViewById(R.id.articleCard);
            articleImage = (ImageView) itemView.findViewById(R.id.articleImage);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            author = (TextView) itemView.findViewById(R.id.author);
            date = (TextView) itemView.findViewById(R.id.date);
            url = (TextView) itemView.findViewById(R.id.url);
        }

    }
}
