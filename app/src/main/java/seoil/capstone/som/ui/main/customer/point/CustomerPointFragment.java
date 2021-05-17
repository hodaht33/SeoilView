package seoil.capstone.som.ui.main.customer.point;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.model.PointData;
import seoil.capstone.som.test.RecyclerViewAdapter;
import seoil.capstone.som.test.TestPointActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class CustomerPointFragment extends Fragment implements CustomerPointContract.View, View.OnClickListener {

    private Animation mFabopen, mFabclose;
    private boolean IsFaboepn = false;
    private FloatingActionButton mFloatingActionSetting, mFloatingActingSave, mFloatingActingUse;
    private TextView mTextSavePointView;
    private RecyclerView mRecyclerViewPoint;
    private RecyclerViewAdapter madapterPoint;
    private int mUsingPoint, mCurrentPoint;
    private ArrayList<String> mShopName;
    public CustomerPointFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*Floationg Action 부분*/
        View view = inflater.inflate(R.layout.fragment_customer_point, container, false);

        mRecyclerViewPoint = view.findViewById(R.id.RecyclerViewPointInformation);
        mRecyclerViewPoint.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewPoint.setAdapter(madapterPoint);

        mFabopen = AnimationUtils.loadAnimation(getContext(),R.anim.fabopen);
        mFabclose = AnimationUtils.loadAnimation(getContext(), R.anim.fabclose);

        mFloatingActionSetting = view.findViewById(R.id.floatingActionButtonSettingPoint);
        mFloatingActingSave = view.findViewById(R.id.floatingActionButtonSavingPoint);
        mFloatingActingUse = view.findViewById(R.id.floatingActionButtonUsingPoint);

        mFloatingActionSetting.setOnClickListener(this);
        mFloatingActingSave.setOnClickListener(this);
        mFloatingActingUse.setOnClickListener(this);
        /*Floationg Action 부분*/

//
//        OnFinishApiListener<SavePoint.GetRes> onFinishApiListener = new OnFinishApiListener<SavePoint.GetRes>() {
//            @Override
//            public void onSuccess(SavePoint.GetRes getRes) {
//                if (getRes.getStatus() == PointApi.SUCCESS) {
//
//                    List<SavePoint.GetRes.Result> list = getRes.getResults();
//
//                    int SavePoint = 0;
//                    for (SavePoint.GetRes.Result result : list) {
//                       SavePoint += result.getSavePointAmount();
//                    }
//                    setTextViewSavePoint(SavePoint);
//                } else{
//                    mTextSavePointView.setText("포인트가 없습니다.");
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//                Log.d("test", t.toString());
//            }
//        };

        return view;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    /*Floationg Action  클리시*/
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.floatingActionButtonSettingPoint:
                anim();
                break;
            case R.id.floatingActionButtonSavingPoint:
                anim();
                Toast.makeText(getActivity(),"SAVING",LENGTH_LONG).show();
                break;
            case R.id.floatingActionButtonUsingPoint:
                anim();
                Toast.makeText(getActivity(),"USING",LENGTH_LONG).show();
                break;

        }
    }
    /*Floationg Action 애니메이션*/
    public void anim() {

        if (IsFaboepn) {
            mFloatingActingSave.startAnimation(mFabclose);
            mFloatingActingUse.startAnimation(mFabclose);
            mFloatingActingSave.setClickable(false);
            mFloatingActingUse.setClickable(false);
            IsFaboepn = false;
        } else {
            mFloatingActingSave.startAnimation(mFabopen);
            mFloatingActingUse.startAnimation(mFabopen);
            mFloatingActingSave.setClickable(true);
            mFloatingActingUse.setClickable(true);
            IsFaboepn = true;
        }
    }


}
