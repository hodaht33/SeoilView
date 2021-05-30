package seoil.capstone.som.ui.main.manager.event;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.event.detail.DetailEventActivity;

public class ManagerEventFragment extends Fragment implements ManagerEventContract.View {

    private ManagerEventPresenter mPresenter;           //Fragment에서 발생하는 데이터를 처리 및 View 와 Interactor 연결
    private RecyclerView mRecyclerViewMain;             //진행중 이벤트를 보여줄 리사이클러뷰
    private ManagerEventAdapter mAdapterMain;           //진행중 이벤트의 어댑터

    private ArrayList<ManagerEventAdapter.Item> mEventName;                 //어댑터 초기화용 변수
    private ArrayList<String> mEventDate;                                   //
    private ArrayList<Integer> mEventCode;                                  //

    private String mShopId;                                                 //점주의 아이디

    public ManagerEventFragment() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.getEvent(mShopId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerEventPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mEventName = new ArrayList<>();
        mEventCode = new ArrayList<>();
        mEventDate = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_event, container, false);

        initView(view);

        mShopId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterMain = new ManagerEventAdapter(mEventName, mEventCode, mEventDate, mPresenter);
        mRecyclerViewMain.setAdapter(mAdapterMain);

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

        mRecyclerViewMain = view.findViewById(R.id.recyclerViewMEventMain);
    }

    //현재 진행중 이벤트, 종료된 이벤트를 Event Adapter에 갱신
    @Override
    public synchronized void setAdapter(ArrayList<ManagerEventAdapter.Item> eventName, ArrayList<Integer> eventCode, ArrayList<String> eventDate) {

        mAdapterMain.setData(eventName, eventCode, eventDate);
        mAdapterMain.notifyDataSetChanged();
    }

    //상세 정보 액티비티로 이동
    @Override
    public void startDetailedEvent(int eventCode) {

        Intent intent = new Intent(getActivity(), DetailEventActivity.class);
        intent.putExtra("eventCode", eventCode);
        this.startActivityForResult(intent, 10);
    }
}