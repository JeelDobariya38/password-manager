package com.jeeldobariya.passcodes.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.jeeldobariya.passcodes.models.PasswordModel;
import com.jeeldobariya.passcodes.databinding.PasswordListItemBinding;

import java.util.List;

public class PasswordAdapter extends BaseAdapter {

    private Context context;
    private List<PasswordModel> passwordList;

    public PasswordAdapter(Context context, List<PasswordModel> passwordList) {
        this.context = context;
        this.passwordList = passwordList;
    }

    @Override
    public int getCount() {
        return passwordList.size();
    }

    @Override
    public Object getItem(int position) {
        return passwordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PasswordListItemBinding binding;
        
        if (convertView == null) {
            binding = PasswordListItemBinding.inflate(LayoutInflater.from(context), parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (PasswordListItemBinding) convertView.getTag();
        }

        PasswordModel password = passwordList.get(position);
        binding.tvDomain.setText(password.getDomain());
        binding.tvUsername.setText(password.getUsername());

        return convertView;
    }
}
