package seoil.capstone.som.ui.main.customer.bookmark;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.event.detail.DetailEventActivity;

// 손님 즐겨찾기 뷰(프레그먼트)
public class CustomerBookmarkFragment extends Fragment implements CustomerBookmarkContract.View{

    private CustomerBookmarkPresenter mPresenter;               // view의 데이터 처리
    private CustomerBookmarkMarketInfoAdapter mAdapterMarket;   // 즐겨찾기 매장 정보 어댑터
    private CustomerBookmarkEventAdapter mAdapterEvent;         // 즐겨찾기 매장 진행중 이벤트 어댑터
    private RecyclerView mRecyclerView;                         // 매장 정보 리사이클러뷰
    private ArrayList<String> mShopName;                        // 매장 이름
    private ArrayList<String> mShopCategory;                    // 매장 카테고리
    private ArrayList<String> mUserBookmarkShopId;              // 즐겨찾기된 매장 아이디
    private String mUserId;                                     // 사용자 아이디
    private TabLayout mTabLayoutMain;                           // 이벤트, 즐겨찾기 구분
    private ItemTouchHelper mSwipeDelete;                       // 스와이프 하여 삭제

    // 프레젠터 생성, 필요 변수 및 데이터 불러오기
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerBookmarkPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mShopName = new ArrayList<>();
        mShopCategory = new ArrayList<>();
        mUserBookmarkShopId = new ArrayList<>();

        mAdapterEvent = new CustomerBookmarkEventAdapter(mPresenter, null, null, null, null);

        mUserId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();
    }

    @Override
    public void onDestroy() {

        mUserId = null;
        mAdapterEvent = null;
        mUserBookmarkShopId = null;
        mShopCategory = null;
        mShopName = null;
        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    // UI, 리스너 초기화
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_bookmark, container, false);

        initView(view);
        initListener();

        mSwipeDelete = new ItemTouchHelper(simpleItemTouchCallback);
        mSwipeDelete.attachToRecyclerView(mRecyclerView);

        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("즐겨찾기"), 0);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("이벤트"), 1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterMarket = new CustomerBookmarkMarketInfoAdapter(mShopName, mShopCategory);
        mRecyclerView.setAdapter(mAdapterMarket);

        mPresenter.getBookmarkShopInfo(mUserId);
        mPresenter.getOnGoingEvent(mUserId);

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog(String msg) {

    }

    // 뷰 초기화
    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerViewCPointBookmark);
        mTabLayoutMain = view.findViewById(R.id.tabLayoutCBookMarkSelectView);
    }

    // 리스너 정의 및 초기화
    private void initListener() {

        mTabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                
                if (position == 0) {            //즐겨찾기일 때

                    mSwipeDelete.attachToRecyclerView(mRecyclerView);
                    mRecyclerView.setAdapter(mAdapterMarket);

                } else if (position == 1) {     //이벤트 일 때

                    mSwipeDelete.attachToRecyclerView(null);
                    mRecyclerView.setAdapter(mAdapterEvent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 조회된 매장 정보 adapter에 전달
    @Override
    public void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory, ArrayList<String> shopId) {

        mShopName = shopName;
        mShopCategory = shopCategory;
        mUserBookmarkShopId = shopId;
        mAdapterMarket.setData(shopName, shopCategory);
    }

    // 즐겨찾기 삭제
    @Override
    public void deleteBookmark(int position) {

        mShopName.remove(position);
        mShopCategory.remove(position);
        mUserBookmarkShopId.remove(position);

        mAdapterMarket.setData(mShopName, mShopCategory);

        mPresenter.getOnGoingEvent(mUserId);
    }

    // 클릭된 이벤트의 상세정보로 이동
    @Override
    public void intentDetailEvent(int eventCode) {

        Intent intent = new Intent(getActivity(), DetailEventActivity.class);
        intent.putExtra("eventCode", eventCode);

        this.startActivity(intent);
    }

    // 이벤트 어댑터의 데이터 변경
    @Override
    public void setAdapterEvent(ArrayList<String> marketName, ArrayList<String> eventName, ArrayList<String> eventDate, ArrayList<Integer> eventCode) {

        mAdapterEvent.setData(marketName, eventName, eventDate, eventCode);
    }

    // 스와이프 삭제 정의
    private final ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();

            createAlert(position);
        }
    };

    // 즐겨찾기 삭제 확인창
    private void createAlert(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("즐겨찾기 삭제 확인")
                .setMessage("즐겨찾기한 매장을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override 
                    public void onClick(DialogInterface dialog, int which) {

                        mPresenter.deleteBookmark(mUserId, mUserBookmarkShopId.get(position), position);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override 
                    public void onClick(DialogInterface dialog, int which) {
                        
                        mAdapterMarket.setData(mShopName, mShopCategory);
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create(); dialog.show();
    }
}
