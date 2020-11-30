package com.illumlg.book_catalog.persistence.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.illumlg.book_catalog.persistence.dao.BookDao;
import com.illumlg.book_catalog.persistence.model.Book;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookCatalogDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    private static volatile BookCatalogDatabase instance;
    private static final ExecutorService dbExecutor = Executors.newFixedThreadPool(4);

    public static ExecutorService getDbExecutor() {
        return dbExecutor;
    }

    public static BookCatalogDatabase getDatabase(final Context context) {
        if(instance == null)
            synchronized (BookCatalogDatabase.class) {
                if(instance == null)
                    instance = Room.inMemoryDatabaseBuilder(
                                    context.getApplicationContext(),
                                    BookCatalogDatabase.class).addCallback(dbCallback).build();
            }
        return instance;
    }

    private static final BookCatalogDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbExecutor.execute(() -> {
                BookDao dao = instance.bookDao();
                dao.deleteAll();
//                Book book = new Book("Harry Potter", "J.K. Rowling");
//                dao.insert(book);
//                book = new Book("The Lord of the Rings", "J.R.R. Tolkien");
//                dao.insert(book);
            });
        }
    };
}
