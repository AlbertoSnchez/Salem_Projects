package com.example.lab5a;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("WrongThread")
    ProgressBar pro;
    ImageView imgC;
    List list;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Integer>();
        pro = (ProgressBar) findViewById(R.id.progressBar);
        imgC = (ImageView) findViewById(R.id.imageView);
        //CatImages imgcat = new CatImages();
        //imgcat.doInBackground();
        list.add(R.drawable.u4e41oguuwthfx);
        list.add(R.drawable.l4czryosnlywmlxb);
        list.add(R.drawable.lngkghmzfi5y4qyf);
        imgC.setImageResource((Integer) list.get(0));

        cont++;
        setP(0);
    }

    private void setP(final int progress){
        pro.setProgress(progress);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (progress == 100) {
                    //imgC.setImageResource(R.drawable.l4czryosnlywmlxb);
                    setP(0);
                }
                else
                    setP(progress + 10);
            }
        });
        t.start();
    }
}

class CatImages extends AsyncTask<String, Integer, String> {
    Bitmap img;
    @Override
    protected String doInBackground(String... strings) {
        while (true){
            //JSONObject json = null;
            System.out.println("hola 2");
            /*try {
                json = readJsonFromUrl("https://cataas.com/cat?json=true");
                System.out.println(json.toString());
                System.out.println(json.get("id"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                URL url = new URL("https://cataas.com/"+json.get("id"));
                img = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch(IOException e) {
                System.out.println(e);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

        }
    }
}

