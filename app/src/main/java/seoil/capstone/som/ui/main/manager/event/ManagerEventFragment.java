package seoil.capstone.som.ui.main.manager.event;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerEventFragment extends Fragment implements ManagerEventContract.View {

    private ManagerEventPresenter mPresenter;
    private RecyclerView mRecyclerViewInProgress;
    private RecyclerView mRecyclerViewEnd;
    private ManagerEventAdapter mAdapterInProgress;
    private ManagerEventAdapter mAdapterEnd;

    private ArrayList<String> mEventName;
    private ArrayList<String> mEventStartDate;
    private ArrayList<String> mEventEndDate;
    private ArrayList<Integer> mEventCode;

    private String mShopId;

    public ManagerEventFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerEventPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mEventName = new ArrayList<>();
        mEventCode = new ArrayList<>();
        mEventStartDate = new ArrayList<>();
        mEventEndDate = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_event, container, false);

        initView(view);

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mShopId = bundle.getString("id");

        mRecyclerViewInProgress.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterInProgress = new ManagerEventAdapter(mEventName, mEventCode, mEventStartDate, mEventEndDate);
        mRecyclerViewInProgress.setAdapter(mAdapterInProgress);

        mRecyclerViewEnd.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterEnd = new ManagerEventAdapter(mEventName, mEventCode, mEventStartDate, mEventEndDate);
        mRecyclerViewEnd.setAdapter(mAdapterEnd);


        mPresenter.getEvent(mShopId);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void initView(View view) {

        mRecyclerViewInProgress = view.findViewById(R.id.recyclerViewMEventInProgress);
        mRecyclerViewEnd = view.findViewById(R.id.recyclerViewMEventEnd);
    }

    @Override
    public synchronized void setAdapter(ArrayList<String> eventName, ArrayList<Integer> eventCode, ArrayList<String> eventStartDate, ArrayList<String> eventEndDate, Boolean isInProgress) {

        if (isInProgress) {

            mAdapterInProgress.setData(eventName, eventCode, eventStartDate, eventEndDate);
        } else {

            mAdapterEnd.setData(eventName, eventCode, eventStartDate, eventEndDate);

            mAdapterInProgress.notifyDataSetChanged();
            mAdapterEnd.notifyDataSetChanged();
        }
    }


}