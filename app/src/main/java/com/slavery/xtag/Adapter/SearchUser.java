package com.slavery.xtag.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.slavery.xtag.ChatActivity;
import com.slavery.xtag.Model.User;
import com.slavery.xtag.Utils.AndroidUtil;
import com.slavery.xtag.Utils.FirebaseUtils;

public class SearchUser extends FirestoreRecyclerAdapter<User, SearchUser.UserViewHolder> {
    private Context context;

    public SearchUser(@NonNull FirestoreRecyclerOptions<User> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchUser.UserViewHolder holder, int position, @NonNull User model) {
        holder.usernameText.setText(model.getUsername());
        if(model.getId().equals(FirebaseUtils.currentId())){
            holder.usernameText.setText(model.getUsername()+" (Me)");
        }

        FirebaseUtils.getOtherProfilePicStorageRef(model.getId()).getDownloadUrl()
                .addOnCompleteListener(t -> {
                    if(t.isSuccessful()){
                        Uri uri  = t.getResult();
                        AndroidUtil.setProfilePic(context,uri,holder.profilePic);
                    }
                });

        holder.itemView.setOnClickListener(v -> {
            //navigate to chat activity
            Intent intent = new Intent(context, ChatActivity.class);
            AndroidUtil.passUserAsIntent(intent,model);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public SearchUser.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameText;
        private TextView phoneText;
        private ImageView profilePic;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
