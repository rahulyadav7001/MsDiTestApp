package com.ryandro.msditestapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ryandro.msditestapp.R;
import com.ryandro.msditestapp.appconstants.AppConstant;
import com.ryandro.msditestapp.dataobject.ChatMessageDO;
import com.ryandro.msditestapp.widget.ReactionView;

import java.util.List;

/**
 * Created by WIN 1O on 15-03-2018.
 */

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.DataViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<ChatMessageDO> vecChatMessage;
    private boolean isSelected = false;
    private PopupWindow popupWindow;

    public MessageRecyclerViewAdapter(Context context, List<ChatMessageDO> vecChatMessage) {
        this.vecChatMessage = vecChatMessage;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        ReactionView rvl = new ReactionView(context);
        popupWindow = new PopupWindow(rvl, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chat_message_cell, parent, false);
        DataViewHolder viewHolder = new DataViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        ChatMessageDO messageDO = vecChatMessage.get(position);
        // I put this condition here for display the self and frnd message alternatively

        //Note:- In the given screen message width and heigth you have mentioned but if the message is big then
        // it will give problem on the UI so here I had keep width of message as same what you had mentioned but
        // height of message box is varying here based on message text size.
        if (position % 2 == 0) {
            holder.ll_selfMessage.setVisibility(View.VISIBLE);
            holder.ll_frnd_message.setVisibility(View.GONE);
            holder.tv_timeStampself.setText(messageDO.getMessageTime());
            holder.tv_messageSelf.setText(messageDO.getMessage());
        } else {
            holder.ll_selfMessage.setVisibility(View.GONE);
            holder.ll_frnd_message.setVisibility(View.VISIBLE);
            holder.tv_timeStampFrnd.setText(messageDO.getMessageTime());
            holder.tv_messageFrnd.setText(messageDO.getMessage());
            holder.tv_messageBy.setText(messageDO.getMessageBy() + ",");
            String strFinal = "";
            if (messageDO.getMessageBy() != null && !(messageDO.getMessageBy().equalsIgnoreCase(""))) {
                String strFrndShortName = messageDO.getMessageBy();
                String[] str = strFrndShortName.split(" ");

                for (int i = 0; i < str.length; i++) {
                    strFinal += Character.toString(str[i].charAt(0));
                }
            }
            // FOr temporery purpose I put this condition here for display different background  color of Bubble
            if (isSelected) {
                isSelected = false;
                holder.tv_bubbleName.setText("" + strFinal.toUpperCase());
                holder.tv_bubbleName.setTextColor(context.getResources().getColor(R.color.white));
                holder.ll_bubbleName.setBackground(context.getResources().getDrawable(R.drawable.circle_bg_orange));
            } else {
                isSelected = true;
                holder.tv_bubbleName.setText("" + strFinal.toUpperCase());
                holder.tv_bubbleName.setTextColor(context.getResources().getColor(R.color.charcoal_grey));
                holder.ll_bubbleName.setBackground(context.getResources().getDrawable(R.drawable.circle_bg_gray));
            }

            /************** setting all the attributes Typeface****************/
            holder.tv_messageSelf.setTypeface(AppConstant.REGULAR);
            holder.tv_timeStampself.setTypeface(AppConstant.REGULAR);
            holder.tv_bubbleName.setTypeface(AppConstant.BOLD);
            holder.tv_messageFrnd.setTypeface(AppConstant.REGULAR);
            holder.tv_timeStampFrnd.setTypeface(AppConstant.REGULAR);
            holder.tv_messageBy.setTypeface(AppConstant.REGULAR);
            /*******************************************************************/

        }
        holder.iv_smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
                popupWindow.showAsDropDown(holder.iv_smiley, 100, -490);
            }
        });
        holder.tv_messageFrnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });
        holder.tv_messageSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv_messageFrnd.performClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (vecChatMessage != null)
            return vecChatMessage.size();
        return 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_timeStampself, tv_messageSelf, tv_messageBy, tv_bubbleName, tv_messageFrnd, tv_timeStampFrnd;
        private RelativeLayout ll_frnd_message;
        private LinearLayout ll_selfMessage, ll_bubbleName;
        private ImageView iv_smiley;

        public DataViewHolder(View itemView) {
            super(itemView);
            tv_timeStampself = (TextView) itemView.findViewById(R.id.tv_selfTimeStamp);
            tv_messageSelf = (TextView) itemView.findViewById(R.id.tv_selfMessage);
            tv_bubbleName = (TextView) itemView.findViewById(R.id.tv_bubbleName);
            tv_messageBy = (TextView) itemView.findViewById(R.id.tv_frndName);
            tv_timeStampFrnd = (TextView) itemView.findViewById(R.id.tv_frndTimeStamp);
            tv_messageFrnd = (TextView) itemView.findViewById(R.id.tv_frndMessage);
            ll_frnd_message = (RelativeLayout) itemView.findViewById(R.id.ll_frnd_message);
            ll_selfMessage = (LinearLayout) itemView.findViewById(R.id.ll_selfMessage);
            ll_bubbleName = (LinearLayout) itemView.findViewById(R.id.ll_bubbleName);
            iv_smiley = (ImageView) itemView.findViewById(R.id.iv_smiley);


        }
    }
}
