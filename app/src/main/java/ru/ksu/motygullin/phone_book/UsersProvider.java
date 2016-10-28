package ru.ksu.motygullin.phone_book;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by UseR7 on 28.10.2016.
 */

public class UsersProvider {

    private static final String PREFERENCES = "users";
    private static final String NAMES = "users";
    private static final String DELETED_PREFERENCES = "deletedUsers";

    private Context context;

    private  static  UsersProvider sInstance;

    public static UsersProvider getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UsersProvider(context.getApplicationContext());
        }
        return sInstance;
    }

    private UsersProvider(Context applicationContext) {
        this.context = applicationContext;
    }

    public void saveContacts(List<User> contactList){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>(){}.getType();
        String jsonText = gson.toJson(contactList, listType);
        editor.putString(NAMES, jsonText);
        editor.commit();
    }

    public void saveDeletedContacts(List<User> contactList){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>(){}.getType();
        String jsonText = gson.toJson(contactList, listType);
        editor.putString(DELETED_PREFERENCES, jsonText);
        editor.commit();
    }

    private List<User> fillUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            User user = new User("Username" + i, Integer.toString(8000000 +i * 1234567), false);
            users.add(user);
        }
        return users;
    }

    public List<User> getContactsList(){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(NAMES)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(NAMES, "");
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        } else {
            List<User> contacts;
            contacts = fillUsers();
            saveContacts(contacts);
            return contacts;
        }
    }

    public List<User> getDeletedContactsList(){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(DELETED_PREFERENCES)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(DELETED_PREFERENCES, "");
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        } else {
            List<User> contacts;
            contacts = new LinkedList<>();
            saveDeletedContacts(contacts);
            return contacts;
        }
    }
}
