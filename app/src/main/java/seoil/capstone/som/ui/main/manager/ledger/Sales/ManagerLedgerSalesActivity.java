package seoil.capstone.som.ui.main.manager.ledger.Sales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerLedgerSalesActivity extends AppCompatActivity implements ManagerLedgerSalesContract.View{

    public final int SELECTED_SALE = 0;
    public final int SELECTED_COST = 1;


    private ManagerLedgerSalesPresenter mPresenter;
    private ArrayList<String> mDataName;                    //매출 이름
    private ArrayList<Integer> mDataAmount;                 //매출 데이터
    private ArrayList<Integer> mAutoInc;                    //매출 구분코드
    private int selectedIndex;

    private RecyclerView mRecyclerView;                     //매출 정보 리사이클러뷰
    private String mShopId;                                 //점주 아이디
    private String mDateQuery;                              //날짜 쿼리

    private ImageView mImageViewAdd;                        //지출 추가 버튼
    private ManagerLedgerTextAdapter mAdapter;              //매출 어댑터
    private TabLayout mTabLayout;                           //Appbar

    private TextView mTextViewDate;                         //날짜 보여줄 텍스트뷰
    private TextView mTextViewTitle;                        //매출, 지출 텍스트뷰
    private AlertDialog mAlertDialogInsert;                 //지출 추가 다이얼로그
    private Button mBtnClose;                               //종료 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_ledger_sales);

        mPresenter = new ManagerLedgerSalesPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        initView();

        mDataName = new ArrayList<>();
        mDataAmount = new ArrayList<>();
        mAutoInc = new ArrayList<>();
        selectedIndex = SELECTED_SALE;

        Intent intent = getIntent();
        mShopId = intent.getStringExtra("id");
        mDateQuery = intent.getStringExtra("dateQuery");

        mTabLayout.addTab(mTabLayout.newTab().setText("매출"), SELECTED_SALE);
        mTabLayout.addTab(mTabLayout.newTab().setText("지출"), SELECTED_COST);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ManagerLedgerTextAdapter(mDataName, mDataAmount, mAutoInc, this, mShopId, mPresenter, mDateQuery);
        mRecyclerView.setAdapter(mAdapter);

        initListener();

        mPresenter.getSales(mShopId, mDateQuery);
    }

    @Override
    public void setLayoutAdapterSales(ArrayList<String> listName, ArrayList<Integer> listAmount, ArrayList<Integer> autoInc) {

        mAdapter.setData(listName, listAmount, autoInc);
    }

    @Override
    public void initCost() {

        mPresenter.getCost(mShopId, mDateQuery);
    }

    @Override
    public void initSales() {

        mPresenter.getSales(mShopId, mDateQuery);
    }


    private void initListener() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if (position == SELECTED_SALE) {

                    mImageViewAdd.setVisibility(View.INVISIBLE);
                    selectedIndex = SELECTED_SALE;
                    mTextViewTitle.setText("매출");
                    mAdapter.clear();
                    mAdapter.setIsCost(false);
                    mPresenter.getSales(mShopId, mDateQuery);
                } else if (position == SELECTED_COST) {

                    mImageViewAdd.setVisibility(View.VISIBLE);
                    selectedIndex = SELECTED_COST;
                    mTextViewTitle.setText("지출");
                    mAdapter.setIsCost(true);
                    mAdapter.clear();
                    mPresenter.getCost(mShopId, mDateQuery);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAlertDialogInsert != null) {

                    mAlertDialogInsert.dismiss();
                }
                //다이얼로그로 데이터 추가창 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagerLedgerSalesActivity.this);

                View view = LayoutInflater.from(ManagerLedgerSalesActivity.this).inflate(R.layout.dialog_manager_ledger_insert_sales, null, false);

                builder.setView(view);

                Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitDialogSales);
                EditText editTextName = view.findViewById(R.id.editTextMLedgerNameDialogInsert);
                EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountDialogInsert);

                mAlertDialogInsert = builder.create();
                mAlertDialogInsert.show();

                btnSubmit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        String name = editTextName.getText().toString();
                        String amount = editTextAmount.getText().toString();

                        if (mPresenter.isTextSet(name) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextName.setError("11자 이하로 입력해주세요");
                            editTextName.requestFocus();
                        } else if (mPresenter.isTextSet(amount) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextAmount.setError("11자 이하로 입력해주세요");
                            editTextAmount.requestFocus();
                        } else if (!mPresenter.isNumeric(amount)) {

                            editTextAmount.setError("숫자만 입력해주세요");
                            editTextAmount.requestFocus();
                        } else {

                            if (selectedIndex == SELECTED_COST) {

                                mPresenter.insertSalesWithDate(mShopId, name, Integer.parseInt(amount) * -1, mDateQuery);
                            }

                            mAlertDialogInsert.dismiss();
                        }

                    }
                });
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    //뷰 초기화
    private void initView() {

        mRecyclerView = this.findViewById(R.id.recyclerViewMLedgerSalesAmountSales);
        mTabLayout = this.findViewById(R.id.tabLayoutMLedgerSalesTop);
        mTextViewDate = this.findViewById(R.id.textViewMLedgerDateSales);
        mImageViewAdd = this.findViewById(R.id.imageViewMLedgerInsert);
        mTextViewTitle = this.findViewById(R.id.textViewMLedgerShowTitleSales);
        mBtnClose = this.findViewById(R.id.btnMLedgerFinishSales);
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
}