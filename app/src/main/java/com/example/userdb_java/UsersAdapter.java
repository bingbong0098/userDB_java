package com.example.userdb_java;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdb_java.databinding.UserItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private final Context context;

    private UserItemLayoutBinding binding;
    private List<Integer> list = new ArrayList<>();
    private UserClickListiner listener;

    private final AsyncListDiffer<UserEntity> differ;


    public UsersAdapter(Context context) {
        this.context = context;

        differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<UserEntity>() {
            @Override
            public boolean areItemsTheSame(UserEntity oldItem, UserEntity newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(UserEntity oldItem, UserEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = UserItemLayoutBinding.bind(itemView);
        }

        public void bind(UserEntity item) {
            binding.nameTxt.setText(item.getName());
            binding.familyTxt.setText(item.getFamily());
            binding.ageTxt.setText(String.valueOf(item.getAge()));
            binding.nationalNumberTxt.setText(item.getNationalNumber());

            binding.checkBox2.setOnCheckedChangeListener((checkBox, isChecked) -> {
                if (isChecked) {
                    list.add(item.getId());
                }
                if (listener != null) {
                    listener.users(list);
                }
            });

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("bundle_user_id", item.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            });
        }
    }

    public interface UserClickListiner {
        void users(List<Integer> users);
    }

    public void setListener(UserClickListiner listener) {
        this.listener = listener;
    }

    public void submitList(List<UserEntity> userList) {
        differ.submitList(userList);
    }
}