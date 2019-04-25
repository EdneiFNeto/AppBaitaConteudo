package com.example.appbaitaconteudo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbaitaconteudo.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPOST {

    private static final String TAG = "LoginHTTPLog";
    private static Context context;
    //private static String URL_API = "https://baitaplay.com.br/api/";
    private static String URL_API = "http://wiki.moebius.com.br/fibra_externo/baita-conteudo/api/login_api.php";

    private User user;
    public String login, senha;

    public LoginPOST(Context context, User user) {

        this.context = context;
        this.user = user;
    }

    public  void LoginPOST() {


        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);
                            Log.e(TAG, "ArrayJson: " + jsonarray);

                            for (int i = 0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                login = jsonobject.getString("login");
                                senha = jsonobject.getString("senha");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("login", user.getLogin());
                params.put("senha", user.getSenha());

                return params;
            }
        };

        Volley.newRequestQueue(context).add(postRequest);
    }
}
