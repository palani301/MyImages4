package com.example.palani.myimages4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    String imgDecordable;
    ImageView i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void loadImagefromGallery(View view){
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,RESULT_LOAD_IMG);
    }
    @Override
protected void onActivityResult(int reqcode,int reqresult,Intent data){
        super.onActivityResult(reqcode,reqresult,data);
        try{
            if(reqcode==RESULT_LOAD_IMG && reqresult==RESULT_OK && null !=data){
            Uri simage=data.getData();
            String[] s1={MediaStore.Images.Media.DATA};
            Cursor c=getContentResolver().query(simage,s1,null,null,null);
            c.moveToFirst();
            int c1=c.getColumnIndex(s1[0]);
            imgDecordable=c.getString(c1);
            c.close();
           i=(ImageView)findViewById(R.id.imgView);
            i.setImageBitmap(BitmapFactory.decodeFile(imgDecordable));
        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
    } catch (Exception e) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show();
    }

}

}