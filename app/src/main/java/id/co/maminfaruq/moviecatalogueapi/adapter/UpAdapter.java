package id.co.maminfaruq.moviecatalogueapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.moviecatalogueapi.R;
import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;
import id.co.maminfaruq.moviecatalogueapi.view.DetailActivityNow;

public class UpAdapter extends RecyclerView.Adapter<UpAdapter.ViewHolder> {


    private Context context;
    private List<ResultsItem> itemList;

    public UpAdapter(Context context, List<ResultsItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ResultsItem resultItem = itemList.get(i);

        viewHolder.tvTitle.setText(resultItem.getOriginalTitle());
        viewHolder.tvContent.setText(resultItem.getOverview());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp);
        final String urlImage = "https://image.tmdb.org/t/p/original" + resultItem.getPosterPath();
        Glide.with(context).load(urlImage).apply(options).into(viewHolder.imgNowPlaying);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultItem.getId());

                context.startActivity(new Intent(context, DetailActivityNow.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgNowPlaying)
        ImageView imgNowPlaying;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
