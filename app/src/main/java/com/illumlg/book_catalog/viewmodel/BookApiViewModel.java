package com.illumlg.book_catalog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.illumlg.book_catalog.network.BookApiService;
import com.illumlg.book_catalog.network.BookDTO;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BookApiViewModel extends ViewModel {
    private BookApiService service;
    private MutableLiveData<List<BookDTO>> books = new MutableLiveData<>();

    public BookApiViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        service = retrofit.create(BookApiService.class);
    }

    public LiveData<List<BookDTO>> getBooks() {
        service.getBooks().enqueue(new Callback<List<BookDTO>>() {
            @Override
            public void onResponse(Call<List<BookDTO>> call, Response<List<BookDTO>> response) {
                books.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookDTO>> call, Throwable t) {
                System.err.println(t.toString());
            }
        });
        return books;
    }
}
