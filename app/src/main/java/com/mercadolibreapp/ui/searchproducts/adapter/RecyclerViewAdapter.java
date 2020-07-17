package com.mercadolibreapp.ui.searchproducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mercadolibreapp.R;
import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.squareup.picasso.Picasso;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<ProductModel> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        holder.txtName.setText(data.get(position).name);
        holder.txtStatus.setText(data.get(position).status);
        Picasso.with(context).load(data.get(position).pictures.get(0).url).into(holder.ivPreview);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        @BindView(R.id.ivPreview)
        ImageView ivPreview;

        @BindView(R.id.layoutProduct)
        ConstraintLayout layoutProduct;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            layoutProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()).pictures );
                }
            });
        }


    }

    public interface ClickListener {
        void launchIntent( List<PictureProduct> listImages);
    }

    public void setData(List<ProductModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}