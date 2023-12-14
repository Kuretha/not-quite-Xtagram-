package com.slavery.xtag.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.slavery.xtag.Model.Chat;
import com.slavery.xtag.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatAdapter extends FirestoreRecyclerAdapter<Chat, ChatAdapter.ChatViewHolder> {

    private  Context mContext;

    public ChatAdapter(@NonNull FirestoreRecyclerOptions<Chat> options, Context context) {
        super(options);
        this.mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position, @NonNull Chat model) {
        Log.i("Chat", "aughawuht");
        if (model.getSenderId().equals(FirebaseAuth.getInstance().getUid())){
          holder.leftChatLayout.setVisibility(View.GONE);
          holder.rightChatLayout.setVisibility(View.VISIBLE);
          holder.rightChatTextview.setText(model.getMessage());
        } else{
           holder.rightChatLayout.setVisibility(View.GONE);
           holder.leftChatLayout.setVisibility(View.VISIBLE);
           holder.leftChatTextview.setText(model.getMessage());
       }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_message_recycler_row, parent,false);
        return new ChatViewHolder(view);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout leftChatLayout,rightChatLayout;
        public TextView leftChatTextview,rightChatTextview;


        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_layout);
            rightChatLayout = itemView.findViewById(R.id.right_chat_layout);
            leftChatTextview = itemView.findViewById(R.id.left_chat_textview);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}
