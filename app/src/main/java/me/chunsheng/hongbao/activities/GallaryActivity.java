package me.chunsheng.hongbao.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

import me.chunsheng.hongbao.R;

public class GallaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        final ImageView diplayImage = (ImageView) findViewById(R.id.displayImage);
        final LinearLayout myGallery = (LinearLayout) findViewById(R.id.mygallery);

        try {
            String galleryDirectoryName = "gallery";
            String[] listImages = new String[3];
            for (int i = 0; i < 4; i++) {
                //InputStream is = getAssets().open(galleryDirectoryName + "/" + imageName);
                //final Bitmap bitmap = BitmapFactory.decodeStream(is);

                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(700, 700));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(R.mipmap.mayun);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        diplayImage.setImageResource(R.mipmap.bg_action_bar);
                    }
                });

                myGallery.addView(imageView);
            }
        } catch (Exception e) {
        }
    }
}
