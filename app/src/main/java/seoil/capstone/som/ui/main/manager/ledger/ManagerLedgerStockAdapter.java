package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

// 가계부 재고 어댑터
public class ManagerLedgerStockAdapter extends RecyclerView.Adapter<ManagerLedgerStockAdapter.ViewHolder> {

    public final int ADAPTER_EDIT = 1001;
    public final int ADAPTER_DELETE = 1002;

    private ArrayList<String> mDataName;            //재고명
    private ArrayList<Integer> mDataAmount;          //재고 수량
    private ArrayList<Integer> mDataCode;
    private final ManagerLedgerPresenter mPresenter;
    private final String mShopId;                         //점주 아이디
    private final Context mContext;
    private AlertDialog mAlertDialog;               //재고 추가창

    public ManagerLedgerStockAdapter(ArrayList<String> listName,
                                     ArrayList<Integer> listCode,
                                     ArrayList<Integer> listAmount,
                                     ManagerLedgerPresenter presenter,
                                     String shopId, Context context
    ) {

        mDataName = listName;
        mDataCode = listCode;
        mDataAmount = listAmount;
        mPresenter = presenter;
        mShopId = shopId;
        mContext = context;
    }

    @NonNull
    @Override
    public ManagerLedgerStockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_manager_ledger_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerLedgerStockAdapter.ViewHolder holder, int position) {

        TextView textView = holder.textViewItemName;
        textView.setText(mDataName.get(position));
        TextView textView1 = holder.textViewItemAmount;
        final String temp = mDataAmount.get(position) + "개";
        textView1.setText(temp);
    }

    @Override
    public int getItemCount() {
        if (mDataName == null) {
            return 0;
        }
        return mDataName.size();
    }

    //데이터 설정
    public void setData(ArrayList<String> listName, ArrayList<Integer> listCode, ArrayList<Integer> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
        mDataCode = listCode;
        notifyDataSetChanged();
    }

    //데이터 초기화
    public void clear() {

        if (mDataName == null) {

            return;
        }

        int size = mDataName.size();

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                mDataName.remove(0);
                mDataAmount.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  MenuItem.OnMenuItemClickListener{

        TextView textViewItemName;
        TextView textViewItemAmount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewItemName = itemView.findViewById(R.id.textViewMLedgerItemName);
            textViewItemAmount = itemView.findViewById(R.id.textViewMLedgerItemAmount);

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    MenuItem Edit = menu.add(Menu.NONE, ADAPTER_EDIT, 1, "편집");
                    MenuItem Delete = menu.add(Menu.NONE, ADAPTER_DELETE, 2, "삭제");
                    Edit.setOnMenuItemClickListener(ViewHolder.this::onMenuItemClick);
                    Delete.setOnMenuItemClickListener(ViewHolder.this::onMenuItemClick);
                }
            });

        }

        //편집 또는 삭제 버튼 클릭시 처리
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            int id = item.getItemId();

            if (id == ADAPTER_DELETE) {

                mPresenter.deleteStock(mShopId, mDataCode.get(getAdapterPosition()), mDataName.get(getAdapterPosition()));
            } else if (id == ADAPTER_EDIT) {

                if (mAlertDialog != null) {

                    mAlertDialog.dismiss();
                }
                //다이얼로그로 재고 추가창 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_manager_ledger_stock, null, false);

                builder.setView(view);

                Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitDialogStock);
                EditText editTextName = view.findViewById(R.id.editTextMLedgerNameDialogStock);
                EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountDialogStock);

                btnSubmit.setText("확인");

                editTextName.setText(mDataName.get(getAdapterPosition()));
                editTextAmount.setText(String.valueOf(mDataAmount.get(getAdapterPosition())));

                mAlertDialog = builder.create();
                mAlertDialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String amount = editTextAmount.getText().toString();
                        String newName = editTextName.getText().toString();

                        if (mPresenter.isTextSet(amount) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextAmount.setError("11자 이하로 입력해주세요");
                            editTextAmount.requestFocus();
                        } else if (!mPresenter.isNumeric(amount)) {

                            editTextAmount.setError("숫자만 입력해주세요");
                            editTextAmount.requestFocus();
                        } else {
                            int position = getAdapterPosition();

                            mPresenter.updateStock(mDataCode.get(position), mShopId, mDataName.get(position), newName, Integer.parseInt(amount));
                            notifyItemChanged(getAdapterPosition());
                            mAlertDialog.dismiss();
                        }
                    }
                });
            }

            return true;
        }
    }
}

