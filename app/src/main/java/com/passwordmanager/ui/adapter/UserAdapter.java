package com.passwordmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.passwordmanager.R;
import com.passwordmanager.database.user.UserModel;
import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
  private final ArrayList<UserModel> userList;
  private final LayoutInflater inflater;

  public UserAdapter(Context context, ArrayList<UserModel> userList) {
    this.userList = userList;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return userList.size();
  }

  @Override
  public Object getItem(int position) {
    return userList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return userList.get(position).getId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_user_layout, parent, false);
      holder = new ViewHolder();
      holder.tvUsername = convertView.findViewById(R.id.tvUsername);
      holder.tvType = convertView.findViewById(R.id.tvType);
      holder.tvCreatedAt = convertView.findViewById(R.id.tvCreatedAt);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    UserModel user = userList.get(position);
    holder.tvUsername.setText(user.getUsername());
    holder.tvType.setText(user.getType().toString());
    holder.tvCreatedAt.setText(user.getCreateAt());

    return convertView;
  }

  static class ViewHolder {
    TextView tvUsername;
    TextView tvType;
    TextView tvCreatedAt;
  }
}
