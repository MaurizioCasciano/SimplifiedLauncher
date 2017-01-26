package com.simplifiedlauncher.gallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.maurizio.simplifiedlauncher.R;

import java.io.File;
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
        final ImageView imageView1=(ImageView) convertView.findViewById(R.id.leftImageView);
        final ImageView imageView2=(ImageView) convertView.findViewById(R.id.centerImageView);
        final ImageView imageView3=(ImageView) convertView.findViewById(R.id.rightImageView);
        imageView1.setImageBitmap(image.getFirstImage().getSourceImage());
        imageView1.setTag(image.getFirstImage().getPath());
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SL", "onClick: uri = "+imageView1.getTag().toString());
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(imageView1.getTag().toString())),"image/jpeg");
                getContext().startActivity(intent);
            }
        });
        imageView2.setImageBitmap(image.getSecondImage().getSourceImage());
        imageView2.setTag(image.getSecondImage().getPath());
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SL", "onClick: uri = "+imageView2.getTag().toString());
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(imageView2.getTag().toString())),"image/jpeg");
                getContext().startActivity(intent);
            }
        });
        imageView3.setImageBitmap(image.getThirdImage().getSourceImage());
        imageView3.setTag(image.getThirdImage().getPath());
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SL", "onClick: uri = "+imageView3.getTag().toString());
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(imageView3.getTag().toString())),"image/jpeg");
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
