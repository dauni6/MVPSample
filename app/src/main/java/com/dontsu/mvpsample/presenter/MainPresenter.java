package com.dontsu.mvpsample.presenter;

import com.dontsu.mvpsample.MainContract;
import com.dontsu.mvpsample.model.Database;
import com.dontsu.mvpsample.model.Person;

public class MainPresenter implements MainContract.Presenter {

    private Database database;
    private MainContract.View view;

    public MainPresenter(Database database, MainContract.View view) {
        this.database = database;
        this.view = view;
        this.database.setOnDatabaseListener(new Database.DatabaseListener() {
            @Override
            public void onChanged() {
                MainPresenter.this.view.notifyDataChanged();
            }
        });
    }

    @Override
    public void load() {
        view.showPersonList(database.getPersonList());
    }

    @Override
    public void addPerson(Person person) {
        database.add(person);
    }

    @Override
    public void removePerson(Person person) {
        database.remove(person);
    }
}
