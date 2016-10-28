package ru.ksu.motygullin.phone_book;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    private TextView name;
    private TextView number;
    private Button buttonSendMessage;
    public static InfoFragment newInstance(User user) {

        Bundle args = new Bundle();
        args.putSerializable("user", user);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.activity_info, container, false);
        final User user = (User) getArguments().getSerializable("user");

        name = (TextView) v.findViewById(R.id.username);
        number = (TextView) v.findViewById(R.id.phone);
        buttonSendMessage = (Button) v.findViewById(R.id.sms);

        name.setText(user.getUserName());
        number.setText(String.valueOf(user.getNumber()));

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(user.getNumber());
            }
        });
        return v;
    }

    public void sendSMS(String number) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)).setType("vnd.android-dir/mms-sms"));
    }
}
