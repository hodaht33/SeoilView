package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.util.Log;
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

public class ManagerLedgerStockAdapter extends RecyclerView.Adapter<ManagerLedgerStockAdapter.ViewHolder> {

    private ArrayList<String> mDataName;            //재고명
    private ArrayList<String> mDataAmount;          //재고 수량

    private ManagerLedgerPresenter mPresenter;
    private String mShopId;                         //점주 아이디
    private Context mContext;
    public final int ADAPTER_EDIT = 1001;
    public final int ADAPTER_DELETE = 1002;
    private AlertDialog mAlertDialog;               //재고 추가창 생성

    ManagerLedgerStockAdapter(ArrayList<String> listName, ArrayList<String> listAmount, ManagerLedgerPresenter presenter, String shopId, Context context) {

        mDataName = listName;
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
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerLedgerStockAdapter.ViewHolder holder, int position) {

        TextView textView = holder.textViewItemName;
        textView.setText(mDataName.get(position));
        TextView textView1 = holder.textViewItemAmount;
        textView1.setText(mDataAmount.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataName == null) {
            return 0;
        }
        return mDataName.size();
    }

    //데이터 설정
    public void setData(ArrayList<String> listName, ArrayList<String> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
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


            textViewItemName = itemView.findViewById(R.id.textView_ledger_item_name);
            textViewItemAmount = itemView.findViewById(R.id.textView_ledger_item_amount);

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

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            int id = item.getItemId();

            if (id == ADAPTER_DELETE) {

                mPresenter.deleteStock(mShopId, mDataName.get(getAdapterPosition()));
            } else if (id == ADAPTER_EDIT) {

                if (mAlertDialog != null) {

                    mAlertDialog.dismiss();
                }
                //다이얼로그로 재고 추가창 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_manager_ledger_stock, null, false);

                builder.setView(view);

                Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitDialogStock);
                TextView textViewName = view.findViewById(R.id.textViewMLedgerNameDialogStock);
                EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountDialogStock);

                btnSubmit.setText("확인");

                textViewName.setText(mDataName.get(getAdapterPosition()));
                editTextAmount.setText(mDataAmount.get(getAdapterPosition()).substring(0, mDataAmount.get(getAdapterPosition()).length() - 1));

                mAlertDialog = builder.create();
                mAlertDialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String amount = editTextAmount.getText().toString();

                        if (mPresenter.isTextSet(amount) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextAmount.setError("11자 이하로 입력해주세요");
                            editTextAmount.requestFocus();
                        } else if (!mPresenter.isNumeric(amount)) {

                            editTextAmount.setError("숫자만 입력해주세요");
                            editTextAmount.requestFocus();
                        } else {

                            mPresenter.updateStock(mShopId, mDataName.get(getAdapterPosition()), Integer.parseInt(amount));
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

