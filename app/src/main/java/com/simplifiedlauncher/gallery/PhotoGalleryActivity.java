package com.simplifiedlauncher.gallery;


import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.widget.ListView;


import com.example.maurizio.simplifiedlauncher.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryActivity extends AppCompatActivity {
    List<OurImage> arrayImage = new ArrayList<>();
    List<itemOfImages> arrayOfItem = new ArrayList<>();
    private static final int REQUEST_CODE_PERMISSION = 2;
    String[] arrayPermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    ArrayList<String> filePaths = new ArrayList<String>();
    ListView listView;
    ImageAdapter adapter;
    int numberOfImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_photo_gallery);
        try {
            if (ActivityCompat.checkSelfPermission(this, arrayPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,arrayPermission[1])!=MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        arrayPermission, REQUEST_CODE_PERMISSION);

                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            } else {
                setAdapter();
                getFilePaths();
                loadImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadImage() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = null;
        for (int i = 0; i < numberOfImage; i++) {
            File image = new File(filePaths.get(i));
            bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
            arrayImage.add(new OurImage(bitmap,filePaths.get(i)));
            Log.d("SL", "loadImage: " + image.getAbsolutePath());
            if ((i + 1) % 3 == 0 && i != 0) {
                itemOfImages ioi = new itemOfImages(arrayImage.get(i - 2), arrayImage.get(i - 1), arrayImage.get(i));
                Log.d("SL", "loadImage: item of images " + ioi);
                adapter.add(ioi);
            }
        }

    }

    // Reading file paths from SDCard
    private void getFilePaths() {
        Log.d("SL", "getFilePaths: ci siamo ");
        File folder = new File(Environment.getExternalStorageDirectory(), "SimplifiedLauncher");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            Log.d("SL", "getFilePaths: cartella " + folder.getAbsolutePath());
        } else {
            // Do something else on failure
        }
        Log.d("SL", Environment.getExternalStorageState());
        Log.d("SL", folder.getAbsolutePath());
        // getting list of file paths
        File[] listFiles = folder.listFiles();
        numberOfImage=listFiles.length;
        for (int i = 0; i < numberOfImage; i++) {
            filePaths.add(listFiles[i].getAbsolutePath());
        }
        Log.d("SL", "getFilePaths: numero di foto " + listFiles.length);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("SL", "onRequest result code: " + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 2 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[1]==MockPackageManager.PERMISSION_GRANTED) {

                // Success Stuff here
                getFilePaths();
                loadImage();
                setAdapter();
            }
        }
    }

    private void setAdapter() {
        listView = (ListView) findViewById(R.id.imageListView);
        adapter = new ImageAdapter(this, R.layout.single_row, new ArrayList<itemOfImages>());
        listView.setAdapter(adapter);
    }


}