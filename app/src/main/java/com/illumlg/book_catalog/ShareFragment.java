package com.illumlg.book_catalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.illumlg.book_catalog.persistence.model.Book;
import com.illumlg.book_catalog.viewmodel.BookApiViewModel;
import com.illumlg.book_catalog.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShareFragment newInstance(String param1, String param2) {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("ShareFragment onCreate called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_share, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewShare);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        BookApiViewModel apiViewModel = new ViewModelProvider(this).get(BookApiViewModel.class);
        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        apiViewModel.getBooks()
                .observe(this.getViewLifecycleOwner(), bookDTOS -> bookDTOS
                        .forEach(bookDTO -> bookViewModel
                                .insert(new Book(bookDTO.getName(), bookDTO.getAuthor()))));
        ImageView iv = (ImageView) root.findViewById(R.id.imageView2);
        Glide.with(root)
                .load("https://assets.entrepreneur.com/content/3x2/2000/20191219170611-GettyImages-1152794789.jpeg")
                .apply(new RequestOptions().centerCrop()).into(iv);
        iv.setContentDescription("Image of the book");
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.share_menu, menu);
        if(getShareIntent().resolveActivity(requireActivity().getPackageManager()) == null)
            menu.findItem(R.id.share).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share)
            startActivity(getShareIntent());
        return super.onOptionsItemSelected(item);
    }

    public Intent getShareIntent() {
        return ShareCompat.IntentBuilder.from(requireActivity())
                .setText(getString(R.string.share_fragment))
                .setType("text/plain")
                .getIntent();
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("ShareFragment onStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("ShareFragment onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("ShareFragment onPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i("ShareFragment onStop called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("ShareFragment onDestroy called");
    }
}