package com.example.comunicacaohttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tvCotacao;
    private Button btCotacao;
    private static final String URL = "http://www.google.com.br";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCotacao = (TextView) findViewById(R.id.tvCotacao);
        btCotacao = (Button) findViewById(R.id.button_cotacao);



    }


    public void obterCotacao(View v) {
       // tvCotacao.setText("1 USB = 3 BR");
        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //requisicao aconteceu com sucesso!
                        if (response.has("rate")){
                            try {
                                double rate = response.getDouble("rate");
                                tvCotacao.setText(String.format("1 USD = %.2F BRL",rate));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            tvCotacao.setText("Nao foi possivel obter dado");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    //tratar erro caso requisicao tenha dado errado
                        tvCotacao.setText("Nao foi possivel obter dado");

                    }
                }
                // requisicao via get/browser ou post
                //enviar dados para a requisicao
                //listener qando sucesso ou erro
        );


        RequestQueue fila = Volley.newRequestQueue(this);
        fila.add(requisicao); //json gerencia isso e dispara.

        //como obter os dados e retornar.

    }

    //opcap 1 de post
    public void PostaAqui(){
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response",response);
                    }
                }

        ){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", URL);

                return params;
            }
        };

        RequestQueue fila = Volley.newRequestQueue(this);
        fila.add(postRequest); //json gerencia isso e dispara.
                ;
    }




    //opcap 2 de post
    public void Postagem(View vi){
        final double ii = 90;
        JsonObjectRequest postar = new JsonObjectRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.put("rate",ii);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue fila = Volley.newRequestQueue(this);
        fila.add(postar); //json gerencia isso e dispara.
    }


}