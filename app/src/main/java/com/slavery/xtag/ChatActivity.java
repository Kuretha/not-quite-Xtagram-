package com.slavery.xtag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.slavery.xtag.Adapter.ChatAdapter;
import com.slavery.xtag.Model.Chat;
import com.slavery.xtag.Model.ChatRoom;
import com.slavery.xtag.Model.User;
import com.slavery.xtag.Utils.AndroidUtil;
import com.slavery.xtag.Utils.FirebaseUtils;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    User otherUser;
    String chatroomId;
    ChatRoom chatRoom;
    ChatAdapter adapter;

    EditText messageInput;
    ImageButton sendMessageBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        otherUser = AndroidUtil.getUserFromIntent(getIntent());
        chatroomId = FirebaseUtils.getChatroomId(FirebaseUtils.currentId(),otherUser.getId());

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);
        imageView = findViewById(R.id.profile_pic_image_view);

        FirebaseUtils.getOtherProfilePicStorageRef(otherUser.getId()).getDownloadUrl()
                .addOnCompleteListener(t -> {
                    if(t.isSuccessful()){
                        Uri uri  = t.getResult();
                        AndroidUtil.setProfilePic(this,uri,imageView);
                    }
                });

        backBtn.setOnClickListener((v)->{
            onBackPressed();
        });
        otherUsername.setText(otherUser.getUsername());

        sendMessageBtn.setOnClickListener((v -> {
            String message = messageInput.getText().toString().trim();
            if(message.isEmpty())
                return;
            sendMessageToUser(message);
        }));

        getOrCreateChatRoom();
        setupChatRecyclerView();
    }

    void setupChatRecyclerView(){
        Query query = FirebaseUtils.getChatroomMessageReference(chatroomId)
                .orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Chat> options = new FirestoreRecyclerOptions.Builder<Chat>()
                .setQuery(query,Chat.class).build();

        adapter = new ChatAdapter(options,getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    void sendMessageToUser(String message){

        chatRoom.setLastMessageTimeStamp(Timestamp.now());
        chatRoom.setLastMessageSenderId(FirebaseUtils.currentId());
        chatRoom.setLastMessage(message);
        FirebaseUtils.getChatroomReference(chatroomId).set(chatRoom);

        Chat chatModel = new Chat(message,FirebaseUtils.currentId(),Timestamp.now());
        FirebaseUtils.getChatroomMessageReference(chatroomId).add(chatModel)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            messageInput.setText("");
                            sendNotification(message);
                        }
                    }
                });
    }

    void getOrCreateChatRoom(){
        FirebaseUtils.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chatRoom = task.getResult().toObject(ChatRoom.class);
                if(chatRoom==null){
                    //first time chat
                    chatRoom = new ChatRoom(
                            chatroomId,
                            Arrays.asList(FirebaseUtils.currentId(),otherUser.getId()).toString(),
                            Timestamp.now(), Collections.singletonList("")
                    );
                    FirebaseUtils.getChatroomReference(chatroomId).set(chatRoom);
                }
            }
        });
    }

    void sendNotification(String message){

       FirebaseUtils.currentUserDetails().get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               User currentUser = task.getResult().toObject(User.class);
               try{
                   JSONObject jsonObject  = new JSONObject();

                   JSONObject notificationObj = new JSONObject();
                   notificationObj.put("title",currentUser.getUsername());
                   notificationObj.put("body",message);

                   JSONObject dataObj = new JSONObject();
                   dataObj.put("id",currentUser.getId());

                   jsonObject.put("notification",notificationObj);
                   jsonObject.put("data",dataObj);

                   callApi(jsonObject);


               }catch (Exception e){

               }

           }
       });

    }

    void callApi(JSONObject jsonObject){
         MediaType JSON = MediaType.get("application/json; charset=utf-8");
         OkHttpClient client = new OkHttpClient();
        String url = "https://fcm.googleapis.com/fcm/send";
        RequestBody body = RequestBody.create(jsonObject.toString(),JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization","Bearer YOUR_API_KEY")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

        });

    }
}