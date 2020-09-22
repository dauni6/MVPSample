package com.dontsu.mvpsample;

import com.dontsu.mvpsample.model.Person;

import java.util.List;

public class MainContract {

    //View 인터페이스의 메ㅓ드를 통해 데이터를 화면에 나타낼 것 임
    public interface View {
        void showPersonList(List<Person> personList);
        void notifyDataChanged();
    }

    //생명주기 또는 클릭 이벤트에 대한 내용을 알려줌
    public interface Presenter {
        void load();
        void addPerson(Person person);
        void removePerson(Person person);
    }
}
