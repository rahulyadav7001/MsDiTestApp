package com.ryandro.msditestapp.Activties;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ryandro.msditestapp.R;
import com.ryandro.msditestapp.adapters.MessageRecyclerViewAdapter;
import com.ryandro.msditestapp.appconstants.AppConstant;
import com.ryandro.msditestapp.dataobject.ChatMessageDO;

import java.util.ArrayList;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity {
    private List<ChatMessageDO> vecChatMessage;
    private MessageRecyclerViewAdapter messageRecyclerViewAdapter;
    private RecyclerView rv_chatMessages;
    private TextView tv_gruopName, tv_memeberName;
    private EditText et_msz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        setStatusBarColor();// for setting statusbar Color
        initializeTypeFace(); // for initialize font family what we required for our app
        initializeControls();// for finding all the attributes and set initially value on that
        /*--------------------------------------------------------------------*/
        // insteadt of total static screen DI i had try to make it dymnamically so for that I ttok one list and
        // given some data to display our chatting requirnment.
        getChatMessageData();
        /*--------------------------------------------------------------------*/
        bindControls();// used this method for bind the data with relavent attributes like we
        // can give here onclick functionality and fragment initialization and so on..
    }

    private void bindControls() {

        messageRecyclerViewAdapter = new MessageRecyclerViewAdapter(GroupChatActivity.this, vecChatMessage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupChatActivity.this);
        rv_chatMessages.setLayoutManager(linearLayoutManager);
        rv_chatMessages.setAdapter(messageRecyclerViewAdapter);

    }

    private void initializeControls() {
        rv_chatMessages = (RecyclerView) findViewById(R.id.rv_chatMessages);
        tv_gruopName = (TextView) findViewById(R.id.tv_gruopName);
        tv_memeberName = (TextView) findViewById(R.id.tv_memeberName);
        et_msz = (EditText) findViewById(R.id.et_msz);

        // setting Type face for layout attributees
        et_msz.setTypeface(AppConstant.REGULAR);
        tv_gruopName.setTypeface(AppConstant.BOLD);
        tv_memeberName.setTypeface(AppConstant.REGULAR);
    }

    private void getChatMessageData() {
        vecChatMessage = new ArrayList<>();
        ChatMessageDO messageDO;
        messageDO = new ChatMessageDO("11:00 AM", "Bhavesh Mehra", "This is long message where TS will come in second line ");
        vecChatMessage.add(messageDO);
        messageDO = new ChatMessageDO("11:30 AM", "Bhavesh Mehra", "This is very very looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        vecChatMessage.add(messageDO);
        messageDO = new ChatMessageDO("12:00 AM", "Bhavesh Mehra", "This is long message where TS will come in second line ");
        vecChatMessage.add(messageDO);
        messageDO = new ChatMessageDO("12:15 AM", "Tanuj Sharma", "This is very very looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        vecChatMessage.add(messageDO);
    }

    private void setStatusBarColor() {
        Window window = GroupChatActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(GroupChatActivity.this.getResources().getColor(R.color.statusBar_color));
        }
    }

    public void initializeTypeFace() {
        try {
            AppConstant.REGULAR = Typeface.createFromAsset(getAssets(), "Roboto_Regular.ttf");
            AppConstant.BOLD = Typeface.createFromAsset(getAssets(), "Roboto_Bold.ttf");
            AppConstant.MEDIUM = Typeface.createFromAsset(getAssets(), "Roboto_Medium.ttf");
            AppConstant.LIGHT = Typeface.createFromAsset(getAssets(), "Roboto_Light.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
