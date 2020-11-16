package com.illumlg.book_catalog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.illumlg.book_catalog.databinding.BookItemBinding;
import com.illumlg.book_catalog.persistence.model.Book;

public class BookListAdapter extends ListAdapter<Book, BookViewHolder> {

    public BookListAdapter(@NonNull DiffUtil.ItemCallback<Book> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding binding = BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book currentBook = getItem(position);
        holder.getBinding().setBook(currentBook);
    }

    static class BookDiff extends DiffUtil.ItemCallback<Book> {

        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getAuthor().equals(newItem.getAuthor());
        }
    }
}
