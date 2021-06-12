package seoil.capstone.som.ui.main.customer.search;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.model.ShopDTO;

public class CustomerSearchFragment extends Fragment implements CustomerSearchContract.View{

    private CustomerSearchPresenter mPresenter;

    private ArrayList<String> mShopName;
    private ArrayList<String> mShopCategory;
    private String mSearchQuery;
    private String mShopId;
    private String mInputText;
    private int mCategoryPage;
    private int mShopNamepage;

    private ProgressBar mProgress;
    private Button mBtnSearch;
    private Spinner mSpinner;
    private ScrollView mScrollview;
    private TextInputLayout mTextInputLayout;
    private boolean mIsShopCategorySelected;
    private RecyclerView mRecyclerview;
    private Dialog mDialog;


    private CustomerSearchAdapter mAdapterShop;
    private TextInputEditText mEditText;


    public CustomerSearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mPresenter = new CustomerSearchPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mShopName = new ArrayList<>();
        mShopCategory = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_search, container, false);

        initView(view);
        initListener();


        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mShopId = bundle.getString("id");

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterShop = new CustomerSearchAdapter(mShopName, mShopCategory, mPresenter, mShopId, getContext());
        mRecyclerview.setAdapter(mAdapterShop);


        return view;
    }

    private void initView(View view) {

        mEditText = view.findViewById(R.id.edittextCSearchShop);
        mBtnSearch = view.findViewById(R.id.btnCSearchInformation);
        mSpinner = view.findViewById(R.id.spinnerCShopCategory);
        mTextInputLayout = view.findViewById(R.id.textFieldCShopSearchField);
        mRecyclerview = view.findViewById(R.id.recyclerviewCShoplist);

    }

    private void initListener( ) {


        mEditText.addTextChangedListener(new TextWatcher() {

            //글자가 바뀌기전
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mRecyclerview.scrollBy(0, -200);

            }

            //글자가 변경될 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            //글자가 바뀐 뒤
            @Override
            public void afterTextChanged(Editable s) {



                if(mEditText.length() > 0){

                    mSpinner.setVisibility(View.INVISIBLE);
                    mBtnSearch.setVisibility(View.VISIBLE);
                    mTextInputLayout.setHelperText(" ");


                }else{
                    mSpinner.setVisibility(View.VISIBLE);
                    mBtnSearch.setVisibility(View.INVISIBLE);
                }
                if(mEditText.length() == 15){
                    Toast.makeText(getContext(),"15자 까지만 입력 가능합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Spinner 입력
        @SuppressLint("ResourceType")
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinnerArray, R.layout.spinner_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setFocusable(true);
        mSpinner.setFocusableInTouchMode(true);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override //Spinner 선택
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (mSpinner.getSelectedItemPosition() > 0) {

                    mIsShopCategorySelected = true;

                    mEditText.setText(parent.getItemAtPosition(position).toString());


                } else {

                    mIsShopCategorySelected = false;
                }
            }

            @Override //아무것도 선택안 할 시
            public void onNothingSelected(AdapterView<?> parent) {

                mIsShopCategorySelected = false;
            }
        });

        //버튼 클릭 시
        mBtnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mInputText = mEditText.getText().toString();

                if(mInputText.length() <= 1){

                    Toast.makeText(getContext(), "입력할수가없습니다.", Toast.LENGTH_SHORT).show();

                }
                else {

                    mPresenter.getShopKeyword(mInputText, mShopNamepage);
                    mPresenter.getShopKeywordCategory(mInputText, mCategoryPage);
                    mRecyclerview.setVisibility(View.VISIBLE);
                    mSpinner.setVisibility(View.GONE);
                    mBtnSearch.setVisibility(View.GONE);

                }
            }

        });
    }


    @Override
    public String getQueryString() {
        return null;
    }

    @Override
    public boolean isListEmpty() {
        return false;
    }

    //어댑터 데이터 정보
    @Override
    public void setLayoutAdapterShop(ArrayList<String> listShopName, ArrayList<String> listShopCategory) {
        mAdapterShop.setData(listShopName, listShopCategory);
    }

    @Override
    public void initShop() {
        mPresenter.getShop(mInputText);
    }

    @Override
    public void initShopKeyword() {

        mPresenter.getShopKeyword(mInputText, mShopNamepage);
    }

    @Override
    public void initShopKeywordCategory() {

        mPresenter.getShopKeywordCategory(mInputText, mCategoryPage);
    }

    @Override
    public void showProgress() {

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showDialog(String msg) {

        if (mDialog == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (mDialog != null) {

                                mDialog = null;
                            }
                        }
                    });

            mDialog = builder.create();
            mDialog.show();
        }

    }


}