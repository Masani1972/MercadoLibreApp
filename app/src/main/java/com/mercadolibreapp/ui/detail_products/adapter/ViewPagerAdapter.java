package com.mercadolibreapp.ui.detail_products.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<PictureProduct> images;

    public ViewPagerAdapter(Context context) {
        this.context = context;

    }

    public void setImages(List<PictureProduct> images) {
        if (images != null && !images.isEmpty())
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

        String url = images.get(position).url.replaceAll("http","https");
        Picasso.with(context)
                .load(url)
                .resize(200, 200)
                .centerInside()
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
