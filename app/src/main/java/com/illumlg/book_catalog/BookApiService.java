package com.illumlg.book_catalog;

import com.illumlg.book_catalog.persistence.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookApiService {

    @GET("/book/list")
    Call<List<Book>> getBooks();
}
