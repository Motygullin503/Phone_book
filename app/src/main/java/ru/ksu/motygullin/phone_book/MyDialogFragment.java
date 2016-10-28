package ru.ksu.motygullin.phone_book;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by UseR7 on 21.10.2016.
 */

public class MyDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    User user;

    public static MyDialogFragment newInstance(User user) {

        Bundle args = new Bundle();
        args.putSerializable("contact", user);
        MyDialogFragment fragment = new MyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        user = (User) getArguments().getSerializable("contact");
        if(user.isDeleted()) {
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Восстановить контакт?")
                    .setPositiveButton("Да",this)
                    .setNegativeButton("Нет", this)
                    .setMessage("Вы уверены? Контакт будет восстановлен");
            return adb.create();
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Удалить контакт?")
                    .setPositiveButton("Да", this)
                    .setNegativeButton("Нет", this)
                    .setMessage("Вы уверены? Контакт будет удалён");
            return adb.create();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                if(user.isDeleted){
                    List<User> contactList = UsersProvider.getInstance(getActivity()).getContactsList();
                    List<User> deletedContactList1 = UsersProvider.getInstance(getActivity()).getDeletedContactsList();
                    deletedContactList1.remove(user);
                    user.setDeleted(false);
                    contactList.add(user);
                    UsersProvider.getInstance(getActivity()).saveContacts(contactList);
                    UsersProvider.getInstance(getActivity()).saveDeletedContacts(deletedContactList1);
                } else {
                    List<User> contactList = UsersProvider.getInstance(getActivity()).getContactsList();
                    List<User> deletedContactList1 = UsersProvider.getInstance(getActivity()).getDeletedContactsList();
                    contactList.remove(user);
                    user.setDeleted(true);
                    deletedContactList1.add(user);
                    UsersProvider.getInstance(getActivity()).saveContacts(contactList);
                    UsersProvider.getInstance(getActivity()).saveDeletedContacts(deletedContactList1);
                }
                if(getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).getPagerAdapter().notifyDataSetChanged();
                }
                break;
        }
    }
}
