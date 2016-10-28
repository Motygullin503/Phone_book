package ru.ksu.motygullin.phone_book.adapters;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.ksu.motygullin.phone_book.MyDialogFragment;
import ru.ksu.motygullin.phone_book.InfoFragment;
import ru.ksu.motygullin.phone_book.R;
import ru.ksu.motygullin.phone_book.User;
import ru.ksu.motygullin.phone_book.UsersProvider;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mUsers;
    private boolean isDeleted;
    private FragmentActivity activity;
    FragmentManager fragmentManager;


    public MyItemRecyclerViewAdapter(boolean isDeleted, FragmentActivity activity) {
       this.isDeleted = isDeleted;
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final User user = mUsers.get(position);
        ((MyViewHolder) holder).bind(user);

        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment fragment = InfoFragment.newInstance(user);
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction()
                        .replace(R.id.info_land, fragment, InfoFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
            }
        });
        ((MyViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MyDialogFragment fragment = MyDialogFragment.newInstance(user);
                fragment.show(fragmentManager, MyDialogFragment.class.getName());
                return true;
            }
        });
    }
        @Override
        public int getItemCount() {
            mUsers = getUsers(isDeleted);
            return mUsers.size();
        }

        private List<User> getUsers (boolean isDeleted){
            if(isDeleted){
                return UsersProvider.getInstance(activity).getDeletedContactsList();
            } else {
                return UsersProvider.getInstance(activity).getContactsList();
            }
        }
    }



