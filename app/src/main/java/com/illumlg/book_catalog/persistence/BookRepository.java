package com.illumlg.book_catalog.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.illumlg.book_catalog.persistence.dao.BookDao;
import com.illumlg.book_catalog.persistence.db.BookCatalogDatabase;
import com.illumlg.book_catalog.persistence.model.Book;

import java.util.List;

public class BookRepository {
    private BookDao dao;
    private LiveData<List<Book>> allBooks;

    public BookRepository(Application app) {
        BookCatalogDatabase db = BookCatalogDatabase.getDatabase(app);
        dao = db.bookDao();
        allBooks = dao.getAll();
    }


    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public void insert(Book book) {
        BookCatalogDatabase.getDbExecutor().execute(() -> dao.insert(book));
    }
}
