package com.dontsu.mvpsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dontsu.mvpsample.MainContract;
import com.dontsu.mvpsample.R;
import com.dontsu.mvpsample.model.Database;
import com.dontsu.mvpsample.model.Person;
import com.dontsu.mvpsample.presenter.MainPresenter;

import java.util.List;
import java.util.Random;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainContract.View, MainViewHolder.HolderClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree()); //Timber initialize
        recyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(this); //adapter 설정
        recyclerView.setLayoutManager(linearLayoutManager); //layoutmanager 설정
        recyclerView.setAdapter(adapter); //adapter 연결
        presenter = new MainPresenter(Database.getInstance(), this);
        presenter.load();
    }

    @Override
    public void showPersonList(List<Person> personList) {
        adapter.setItems(personList);
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClick(Person person) {
        presenter.removePerson(person);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.addPerson(new Person(System.currentTimeMillis(), String.format("New Person %d", new Random().nextInt(1000))));
        return super.onOptionsItemSelected(item);

    }
}
