package com.flopezluis;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.widget.ImageView;

public class AndroidGallery extends Activity {
    
	private static final int IMAGE_SELECTED = 0;
    
    private ImageView IVselected = null;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        IVselected = (ImageView)findViewById(R.id.IVselected);
        /*
         * as soon as it starts we open the gallery
         */
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), IMAGE_SELECTED);
}
    /**
     * When the user selects the image, then it returns here.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
    		if (requestCode == IMAGE_SELECTED) {
    			Uri uri = data.getData();
    			try {
					IVselected.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
}