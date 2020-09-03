package com.swordfish.paysystem.users;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.swordfish.db.room.LogItem;
import com.swordfish.paysystem.R;

import java.util.List;


public abstract class LogListFragment extends Fragment implements AdapterView.OnItemClickListener {

    protected int mIndex;

    private DataBaseViewModel mViewModel;

    public LogListFragment(int index, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        bundle.putString("title",title);
        this.setArguments(bundle);
    }

    public String getTitle() {
        return getArguments().getString("title");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt("index");
        mViewModel = DataBaseViewModel.INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logs_fragment_layout,null);
        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(new ListAdapter(mViewModel.getLogs(mIndex)));
        listView.setOnItemClickListener(this);
        return view;
    }



    class ListAdapter extends BaseAdapter {

        private List<LogItem> mList;

        public ListAdapter(LiveData<List<LogItem>> data) {
            data.observe(LogListFragment.this, new Observer<List<LogItem>>() {
                @Override
                public void onChanged(List<LogItem> logItems) {
                    mList = logItems;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }



        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view.getTag() == null) {
                ViewHolder holder = new ViewHolder(viewGroup.getContext());
                view.setTag(holder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
            holder.updateView((LogItem) getItem(i));

            return holder.view;
        }


        class ViewHolder {

            View view;

            TextView id;
            TextView name;
            TextView tel;

            TextView paidAmount;

            TextView startedTime;
            TextView leftTime;

            TextView premium;

            ViewHolder(Context context) {
                view = LayoutInflater.from(context).inflate(R.layout.log_list_item_layout,null);

                id = view.findViewById(R.id.id);
                name = view.findViewById(R.id.name);
                tel = view.findViewById(R.id.telephone);
                premium = view.findViewById(R.id.premium);
            }


            void updateView(LogItem item) {
                id.setText(item.log.logId + "");
                name.setText(item.user.name);
                tel.setText(item.user.telephone);
                premium.setText(item.user.premium == null ? "否" : "是");
            }

        }
    }
}
