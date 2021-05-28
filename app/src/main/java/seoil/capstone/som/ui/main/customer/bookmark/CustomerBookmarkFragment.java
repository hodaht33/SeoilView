package seoil.capstone.som.ui.main.customer.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;

public class CustomerBookmarkFragment extends Fragment implements View.OnClickListener, CustomerBookmarkContract.View{

    private CustomerBookmarkPresenter mPresenter;           //view의 데이터 처린
    private CustomerBookmarkAdapter mAdapter;               //매장 정보 리사이클러뷰의 어댑터
    private RecyclerView mRecyclerView;                     //매장 정보 리사이클러뷰
    private ArrayList<String> mShopName;                    //매장 이름
    private ArrayList<String> mShopCategory;                //매장 카테고리
    private String mUserId;                                 //사용자 아이디

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerBookmarkPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mShopName = new ArrayList<>();
        mShopCategory = new ArrayList<>();

        mUserId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_bookmark, container, false);

        initView(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CustomerBookmarkAdapter(mShopName, mShopCategory);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getBookmarkShopInfo(mUserId);

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //뷰 초기화
    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerViewCPointBookmark);
    }

    //조회된 매장 정보 adapter에 전달
    @Override
    public void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory) {

        mShopName = shopName;
        mShopCategory = shopCategory;
        mAdapter.setData(shopName, shopCategory);
    }
}
