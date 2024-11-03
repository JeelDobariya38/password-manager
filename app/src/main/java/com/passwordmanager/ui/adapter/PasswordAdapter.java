package com.passwordmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.passwordmanager.R;
import com.passwordmanager.models.PasswordModel;
import java.util.List;

public class PasswordAdapter extends BaseAdapter {

  private final Context context;
  private final List<PasswordModel> passwordItems;

  public PasswordAdapter(Context context, List<PasswordModel> passwordItems) {
    this.context = context;
    this.passwordItems = passwordItems;
  }

  @Override
  public int getCount() {
    return passwordItems.size();
  }

  @Override
  public Object getItem(int position) {
    return passwordItems.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView =
          LayoutInflater.from(context).inflate(R.layout.list_item_password, parent, false);
    }

    PasswordModel item = passwordItems.get(position);

    // Domain or Website Icon
    ImageView imageIcon = convertView.findViewById(R.id.imageIcon);
    // Test URL
    String imageUrl =
        "https://parsefiles.back4app.com/JPaQcFfEEQ1ePBxbf6wvzkPMEqKYHhPYv8boI1Rc/ec30b593f00ec2808a0ee980a783dcb7_EO285b7Vao.png";
    Glide.with(context)
        .load(imageUrl)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .into(imageIcon);

    // Domain or Website
    TextView titleTextView = convertView.findViewById(R.id.titleTextView);
    titleTextView.setText(item.getDomain());

    // Account Name
    TextView subtitleTextView = convertView.findViewById(R.id.subtitleTextView);
    subtitleTextView.setText(item.getUsername());

    // Action Button
    ImageButton actionButton = convertView.findViewById(R.id.actionButton);
    actionButton.setOnClickListener(v -> {});

    return convertView;
  }
}
