package com.illumlg.book_catalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.illumlg.book_catalog.databinding.FragmentMainBinding;
import com.illumlg.book_catalog.persistence.model.Book;
import com.illumlg.book_catalog.viewmodel.BookViewModel;
import com.illumlg.book_catalog.viewmodel.CounterViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CounterViewModel counterVM;
    BookViewModel viewModel;
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Book word = new Book(data.getStringExtra("book name"), data.getStringExtra("book author"));
            viewModel.insert(word);
        } else {
            Toast.makeText(
                    requireActivity().getApplicationContext(),
                    "Book can't be saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("MainFragment onCreate called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        counterVM = new ViewModelProvider(this).get(CounterViewModel.class);
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMainBinding binding = FragmentMainBinding
                .inflate(inflater, container, false);
        ViewGroup root = (ViewGroup)binding.getRoot();
        binding.setCounterVM(counterVM);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.share_fragment));
        setHasOptionsMenu(true);
        Button inc = root.findViewById(R.id.button_increment);
        Vibrator v = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
        inc.setOnClickListener((view) -> {
            Integer value = counterVM.getCounter().getValue();
            if(value != null)
                counterVM.getCounter().setValue(value+1);
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        });
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        viewModel.getBooks().observe(this.getViewLifecycleOwner(), adapter::submitList);
        FloatingActionButton addBook = root.findViewById(R.id.floatingActionButton4);
        addBook.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), AddBookActivity.class);
            startActivityForResult(intent, NEW_BOOK_ACTIVITY_REQUEST_CODE);
        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this.requireActivity(), R.id.navHostFragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("MainFragment onStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("MainFragment onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("MainFragment onPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i("MainFragment onStop called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("MainFragment onDestroy called");
    }

}