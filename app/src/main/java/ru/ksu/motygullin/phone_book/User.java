package ru.ksu.motygullin.phone_book;

import java.io.Serializable;

/**
 * Created by UseR7 on 21.10.2016.
 */

public class User implements Serializable {
    String userName;
    String number;
    boolean isDeleted;

    public User(String userName, String number, boolean isDeleted) {
        this.userName = userName;
        this.number = number;
        this.isDeleted = isDeleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
