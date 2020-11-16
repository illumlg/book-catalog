package com.illumlg.book_catalog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.illumlg.book_catalog.persistence.BookRepository;
import com.illumlg.book_catalog.persistence.model.Book;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository repository;
    private final LiveData<List<Book>> books;
    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        books = repository.getAllBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public void insert(Book book) {
        repository.insert(book);
    }
}
