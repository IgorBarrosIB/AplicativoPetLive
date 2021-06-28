package com.example.petlife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar,buttonPorcaoUnica;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private TextView textoResultado;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        TaskArray taskTeste = new TaskArray();
        String url = "http://192.168.15.5:3000/horario/all";
        taskTeste.execute(url);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        buttonPorcaoUnica = findViewById(R.id.buttonPorcaoUnica);

        textoResultado = findViewById(R.id.textResultado);

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskObject task = new TaskObject();
                String url = "http://192.168.15.5:3000/horario/all";
                task.execute(url);
            }
        });


        buttonPorcaoUnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskObject task = new TaskObject();
                String url = "http://192.168.15.5:3000/porcaoUnica/1";
                task.execute(url);
            }
        });
    }

    class TaskObject extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {

                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recupera os dados em Bytes
                inputStream = conexao.getInputStream();

                //inputStreamReader lê os dados em Bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );

                //Objeto utilizado para leitura dos caracteres do InpuStreamReader
                BufferedReader reader = new BufferedReader( inputStreamReader );
                buffer = new StringBuffer();
                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append( linha );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString();//buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            try {
                jsonObject = new JSONObject(resultado);
                textoResultado.setText( jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class TaskArray extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {

                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recupera os dados em Bytes
                inputStream = conexao.getInputStream();

                //inputStreamReader lê os dados em Bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );

                //Objeto utilizado para leitura dos caracteres do InpuStreamReader
                BufferedReader reader = new BufferedReader( inputStreamReader );
                buffer = new StringBuffer();
                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append( linha );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString();//buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            try {
                jsonArray = new JSONArray(resultado);
                //textoResultado.setText( jsonArray.toString());

                recyclerView = findViewById(R.id.recyclerView);

                //Configurar adapter

                //Configurar Recyclerview
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}