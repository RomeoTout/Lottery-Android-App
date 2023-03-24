package com.rtlab.numerenorocoase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Rate app
        AppRater.app_launched(this);

        tv = findViewById(R.id.resultTextView);
        new Scraper().execute();


    }

    public class Scraper extends AsyncTask<Void, Void, Void> {
        String text;
        //String text2;
        //ArrayList list = new ArrayList();

        //crawl loto49.ro
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            try {
//                Document doc = Jsoup.connect("http://www.loto49.ro/").get();
//                Element mElement = doc.select("table").get(3);
//                if(mElement.hasText()){
//                    text = mElement.wholeText();
//                    //eliminates whitespace
//                    text = text.replaceAll("[\\s&&[^\\n]]+", " ");
//                    text = text.replaceAll("^\\s+", "");
//                    text = text.replaceAll("\\s+$", "");
//                }
//                else{
//                    text = "Rezultatele inca nu sunt accesibile!";
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }


        //crawl wordpress
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect("https://treoz.wordpress.com/2019/08/04/results-ro/").get();
                Element mElement = doc.select("div.entry-content").get(0);
                //Elements mElement2 = mElement.select("p");
                if(mElement.hasText()){
                    //text = mElement2.get(1).text();
                    //text2 = mElement2.get(0).text();

                    text = mElement.wholeText();
                    if(text.contains("Noroc Plus")){
                        int index = text.indexOf("Noroc Plus");
                        text = text.substring(0, index);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            tv.setText(text);
        }
    }
}
