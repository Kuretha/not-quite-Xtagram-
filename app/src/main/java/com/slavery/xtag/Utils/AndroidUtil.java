package com.slavery.xtag.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.slavery.xtag.Model.User;

public class AndroidUtil {

   public static  void showToast(Context context, String message){
       Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    public static void passUserAsIntent(Intent intent, User model){
       intent.putExtra("username",model.getUsername());
       intent.putExtra("userId",model.getId());


    }

    public static User getUserFromIntent(Intent intent){
        User userModel = new User();
        userModel.setUsername(intent.getStringExtra("username"));
        userModel.setId(intent.getStringExtra("id"));
        return userModel;
    }

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }


}
