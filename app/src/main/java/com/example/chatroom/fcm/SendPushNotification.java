package com.example.chatroom.fcm;

import android.content.Context;
import android.util.Log;
import android.view.PixelCopy;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SendPushNotification {

    Context context;
    String KEY = "AAAA3Hi6Ihs:APA91bFcUPAo76IFkrHLu0XbmgJMz6RrOkrKvzLYA9bX7zxZmaWCIN2G8MdfDqXjw3Oj4Qlcs92JCN1v0KBUTWZYA2E5YGXUwwIksiKBitX2MGusJTe3MKZC5MixRmHPAEOpX4Kvd1C9";
    public SendPushNotification(Context context) {
        this.context = context;
    }

    public void startPush(String username, String messageBody, String token) {
        HashMap<String, String>  message = new HashMap<>();
        message.put("message", messageBody);
        message.put("title", username);
        sendPushToSingleInstance(message, token);
    }

    private void sendPushToSingleInstance(HashMap<String, String> message, String token) {
        String FCM_URL = "https://fcm.googleapis.com/fcm/send";
        StringRequest request = new StringRequest(Request.Method.POST, FCM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        FirebaseMessaging.getInstance().subscribeToTopic("global_chat");
                        Toast.makeText(context, "Message Send", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.getMessage());
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, Object> raw = new Hashtable<>();
                raw.put("data", new JSONObject(message));
                raw.put("to", "/topics/" + token);
                return new JSONObject(raw).toString().getBytes();
            }

            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Authorization","key=" + KEY);
                header.put("Content-Type", "applicaton/json");
                return header;
            }
        };

        Volley.newRequestQueue(context).add(request);
    }
}
