package seoil.capstone.som.ui.main.manager.event;

import android.content.Intent;
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

    private ManagerEventPresenter mPresenter;           //Fragment에서 발생하는 데이터를 처리 및 View 와 Interactor 연결
    private RecyclerView mRecyclerViewInProgress;       //진행중 이벤트를 보여줄 리사이클러뷰
    private RecyclerView mRecyclerViewEnd;              //종료된 이벤트를 보여줄 리사이클러뷰
    private ManagerEventAdapter mAdapterInProgress;     //진행중 이벤트의 어댑터
    private ManagerEventAdapter mAdapterEnd;            //종료된 이벤트의 어댑터

    private ArrayList<String> mEventName;               //어댑터 초기화용 변수
    private ArrayList<String> mEventStartDate;          //
    private ArrayList<String> mEventEndDate;            //
    private ArrayList<Integer> mEventCode;              //

    private String mShopId;                             //점주의 아이디

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
        mAdapterInProgress = new ManagerEventAdapter(mEventName, mEventCode, mEventStartDate, mEventEndDate, mPresenter);
        mRecyclerViewInProgress.setAdapter(mAdapterInProgress);

        mRecyclerViewEnd.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterEnd = new ManagerEventAdapter(mEventName, mEventCode, mEventStartDate, mEventEndDate, mPresenter);
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

    //ManagerEventFragment 뷰 초기화
    private void initView(View view) {

        mRecyclerViewInProgress = view.findViewById(R.id.recyclerViewMEventInProgress);
        mRecyclerViewEnd = view.findViewById(R.id.recyclerViewMEventEnd);
    }

    //현재 진행중 이벤트, 종료된 이벤트를 Event Adapter에 갱신
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

    //상세 정보 액티비티로 이동
    @Override
    public void startDetailedEvent(Intent intent) {

        this.startActivityForResult(intent, 10);
    }


}