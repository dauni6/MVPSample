package com.dontsu.mvpsample.presenter;

import com.dontsu.mvpsample.MainContract;
import com.dontsu.mvpsample.model.Database;
import com.dontsu.mvpsample.model.Person;

/**
 * 1. present의 역할은 model과의 소통으로 데이터를 불러오거나 데이터 저장/수정/삭제를 요청할 수 있다.
 * 2. 또한 present는 사용자의 이벤트를 전달받고 데이터를 view에 갱신해줄 수 있다.
 * 3. view는 오로지 사용자와 UI 상호작용만 담당한다.
 *
  */

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
