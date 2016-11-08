package ru.ksu.motygullin.phone_book;

import java.io.Serializable;

/**
 * Created by UseR7 on 21.10.2016.
 */

public class User implements Serializable {
    String userName;
    String number;
    boolean isDeleted = false;

    public User(String userName, String number) {
        this.userName = userName;
        this.number = number;
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

    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).userName.equals(userName) && ((User) obj).number.equals(number) && ((User) obj).isDeleted == isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
