package com.illumlg.book_catalog;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.illumlg.book_catalog.databinding.BookItemBinding;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private BookItemBinding binding;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public BookItemBinding getBinding() {
        return binding;
    }
}
