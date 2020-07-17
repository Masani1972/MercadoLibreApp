package com.mercadolibreapp.ui.searchproducts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<PictureProduct> images;

    public ViewPagerAdapter(Context context,  List<PictureProduct>images) {
        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {return images.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
        imageView.setLayoutParams(layoutParams);

        Picasso.with(context)
                .load(images.get(position).url)
                .fit()
                .centerCrop()
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
