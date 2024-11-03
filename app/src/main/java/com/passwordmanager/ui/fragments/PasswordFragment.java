package com.passwordmanager.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.passwordmanager.R;
import com.passwordmanager.databinding.FragmentPasswordBinding;
import com.passwordmanager.models.PasswordModel;
import com.passwordmanager.ui.adapter.PasswordAdapter;
import java.util.ArrayList;
import java.util.List;

public class PasswordFragment extends Fragment {

  private FragmentPasswordBinding binding;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    // Menggunakan View Binding
    binding = FragmentPasswordBinding.inflate(inflater, container, false);
    View view = binding.getRoot();

    // Gunakan view binding untuk mengakses searchView
    EditText searchView = binding.searchView;

    // Mendapatkan ikon pencarian dari drawable
    Drawable searchIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_search);
    if (searchIcon != null) {
      searchIcon.setBounds(0, 0, searchIcon.getIntrinsicWidth(), searchIcon.getIntrinsicHeight());
    }

    // Set ikon pencarian secara default
    searchView.setCompoundDrawables(searchIcon, null, null, null);

    List<PasswordModel> passwordItems = new ArrayList<>();
    // Tambahkan contoh data (ganti dengan data yang didapatkan dari database atau sumber lainnya)
    passwordItems.add(new PasswordModel("Google", "example@gmail.com", "password1", "Notes1"));
    passwordItems.add(new PasswordModel("Google", "another@gmail.com", "password2", "Notes2"));

    PasswordAdapter adapter = new PasswordAdapter(requireContext(), passwordItems);
    binding.passwordListView.setAdapter(adapter);

    // Menghilangkan divider
    binding.passwordListView.setDivider(null);

    // Tombol tambah
    binding.addButton.setOnClickListener(
        v -> Toast.makeText(requireContext(), "Add Password clicked", Toast.LENGTH_SHORT).show());

    // Fungsi EditText untuk pencarian
    binding.searchView.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            // adapter.getFilter().filter(s); // Filter hasil pencarian

            // Sembunyikan ikon ketika teks tidak kosong, tampilkan lagi jika kosong
            if (s.length() > 0) {
              searchView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else {
              searchView.setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
            }
          }

          @Override
          public void afterTextChanged(Editable s) {}
        });

    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null; // Menghindari memory leak
  }
}
