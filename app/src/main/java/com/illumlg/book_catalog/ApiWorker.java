package com.illumlg.book_catalog;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.illumlg.book_catalog.network.BookApiService;
import com.illumlg.book_catalog.network.BookDTO;
import com.illumlg.book_catalog.persistence.dao.BookDao;
import com.illumlg.book_catalog.persistence.model.Book;
import com.illumlg.book_catalog.persistence.repository.BookRepository;
import com.illumlg.book_catalog.viewmodel.BookApiViewModel;
import com.illumlg.book_catalog.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiWorker extends Worker {
    private BookApiService service;

    public ApiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        service = retrofit.create(BookApiService.class);
    }

    @NonNull
    @Override
    public Result doWork() {
        BookRepository repository = new BookRepository((Application)getApplicationContext());
        service.getBooks().enqueue(new Callback<List<BookDTO>>() {
            @Override
            public void onResponse(Call<List<BookDTO>> call, Response<List<BookDTO>> response) {
                response.body().forEach(bookDTO -> repository.insert(new Book(bookDTO.getName(), bookDTO.getAuthor())));
            }

            @Override
            public void onFailure(Call<List<BookDTO>> call, Throwable t) {
                System.err.println(t.toString());
            }
        });
        return Result.success();
    }
}
