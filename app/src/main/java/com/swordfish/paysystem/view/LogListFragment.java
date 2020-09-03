package com.swordfish.paysystem.view;

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

import com.swordfish.db.room.LogItem;
import com.swordfish.paysystem.R;

import java.util.List;


public abstract class LogListFragment extends Fragment implements AdapterView.OnItemClickListener {

    protected int mIndex;

    protected DataBaseViewModel mViewModel;

    public LogListFragment(int index, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        bundle.putString("title", title);
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
        View view = inflater.inflate(R.layout.logs_fragment_layout, null);
        ListView listView = view.findViewById(R.id.listview);

        LiveData<List<LogItem>> liveData = mViewModel.getLogs(mIndex);
        listView.setAdapter(new ListAdapter(liveData));
        listView.setOnItemClickListener(this);

        TextView emptyView = view.findViewById(R.id.empty_text);
        emptyView.setText("暂无数据");
        liveData.observe(getActivity(), new Observer<List<LogItem>>() {
            @Override
            public void onChanged(List<LogItem> logItems) {
                if (logItems == null || logItems.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        });

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
            LogItemListItemView itemView;
            if (view == null) {
                itemView = new LogItemListItemView(viewGroup.getContext(), null);
            } else {
                itemView = (LogItemListItemView) view;
            }
            itemView.updateView((LogItem) getItem(i));
            return itemView;
        }


    }
}
