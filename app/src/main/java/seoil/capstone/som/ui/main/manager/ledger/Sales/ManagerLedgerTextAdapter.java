package seoil.capstone.som.ui.main.manager.ledger.Sales;

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

public class ManagerLedgerTextAdapter extends RecyclerView.Adapter<ManagerLedgerTextAdapter.ViewHolder> {

    public final int ADAPTER_EDIT = 1003;
    public final int ADAPTER_DELETE = 1004;

    private ArrayList<String> mDataName;
    private ArrayList<Integer> mDataAmount;
    private ArrayList<Integer> mAutoInc;
    private Context mContext;
    private String mShopId;
    private String mDateQuery;
    private ManagerLedgerSalesPresenter mPresenter;
    private AlertDialog mAlertDialog;
    private Boolean isCost;



    ManagerLedgerTextAdapter(ArrayList<String> listName, ArrayList<Integer> listAmount, ArrayList<Integer> autoInc, Context context, String shopId,
                             ManagerLedgerSalesPresenter presenter, String dateQuery) {

        mDataName = listName;
        mDataAmount = listAmount;
        mAutoInc = autoInc;
        mContext = context;
        mShopId = shopId;
        mPresenter = presenter;
        mDateQuery = dateQuery;
        isCost = false;
    }

    @NonNull
    @Override
    public ManagerLedgerTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_manager_ledger_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerLedgerTextAdapter.ViewHolder holder, int position) {

        TextView textView = holder.textViewItemName;
        textView.setText(mDataName.get(position));
        TextView textView1 = holder.textViewItemAmount;
        textView1.setText(mPresenter.getDetailedSale(mDataAmount.get(position)));
    }

    @Override
    public int getItemCount() {
        if (mDataName == null) {
            return 0;
        }
        return mDataName.size();
    }

    public void setData(ArrayList<String> listName, ArrayList<Integer> listAmount, ArrayList<Integer> autoInc) {

        mDataName = listName;
        mDataAmount = listAmount;
        mAutoInc = autoInc;
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mDataName.size();

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                mDataName.remove(0);
                mDataAmount.remove(0);
                mAutoInc.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewItemName;
        TextView textViewItemAmount;

        final MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                if (id == ADAPTER_DELETE) {

                    mPresenter.deleteSpendingSales(mShopId, mAutoInc.get(getAdapterPosition()), mDateQuery, isCost);
                    return true;
                } else if (id == ADAPTER_EDIT) {

                    if (mAlertDialog != null) {

                        mAlertDialog.dismiss();
                    }
                    //다이얼로그로 데이터 추가창 생성
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_manager_ledger_update_sales, null, false);

                    builder.setView(view);

                    Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitUpdate);
                    TextView textViewName = view.findViewById(R.id.textViewMLedgerNameUpdate);
                    EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountUpdate);

                    textViewName.setText(mDataName.get(getAdapterPosition()));
                    editTextAmount.setText(String.valueOf(mDataAmount.get(getAdapterPosition())));

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

                                int Iamount = Integer.parseInt(amount);
                                if (isCost) {
                                    Iamount = Iamount * -1;
                                }
                                mPresenter.updateSpendingSales(mAutoInc.get(getAdapterPosition()),mDateQuery, mShopId, mDataName.get(getAdapterPosition()),
                                        Iamount, isCost);
                                notifyItemChanged(getAdapterPosition());
                                mAlertDialog.dismiss();
                            }

                        }

                    });
                    return true;
                }

                return false;
            }
        };

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemName = itemView.findViewById(R.id.textView_ledger_item_name);
            textViewItemAmount = itemView.findViewById(R.id.textView_ledger_item_amount);

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                    MenuItem Edit = menu.add(Menu.NONE, ADAPTER_EDIT, 1, "편집");
                    MenuItem Delete = menu.add(Menu.NONE, ADAPTER_DELETE, 2, "삭제");
                    Edit.setOnMenuItemClickListener(onMenuItemClickListener);
                    Delete.setOnMenuItemClickListener(onMenuItemClickListener);
                }
            });
        }
    }

    public void setIsCost(Boolean isCost) {

        this.isCost = isCost;
    }

}

