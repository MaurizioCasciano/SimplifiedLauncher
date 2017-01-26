package com.simplifiedlauncher.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.List;

/**
 * Created by dtrop on 20/01/2017.
 */

public class ImageAdapter extends ArrayAdapter<itemOfImages> {
    private Context context;
    private int resource;
    private LayoutInflater inflater;
    private List<itemOfImages> images;

    public ImageAdapter(Context context, int resourceId, List<itemOfImages> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        this.context=context;
        inflater = LayoutInflater.from(context);
        images = objects;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public itemOfImages getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row, null);
        }
        itemOfImages image = getItem(position);
        ImageView imageView1=(ImageView) convertView.findViewById(R.id.leftImageView);
        ImageView imageView2=(ImageView) convertView.findViewById(R.id.centerImageView);
        ImageView imageView3=(ImageView) convertView.findViewById(R.id.rightImageView);
        imageView1.setImageBitmap(image.getFirstImage().getSourceImage());
        imageView2.setImageBitmap(image.getSecondImage().getSourceImage());
        imageView3.setImageBitmap(image.getThirdImage().getSourceImage());
        return convertView;
    }
}
