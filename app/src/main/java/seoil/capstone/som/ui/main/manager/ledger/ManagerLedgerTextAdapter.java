package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerLedgerTextAdapter extends RecyclerView.Adapter<ManagerLedgerTextAdapter.ViewHolder> {

    private ArrayList<String> mDataName;
    private ArrayList<String> mDataAmount;
    public final int ADAPTER_EDIT = 1001;
    public final int ADAPTER_DELETE = 1002;

    ManagerLedgerTextAdapter(ArrayList<String> listName, ArrayList<String> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
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
        textView1.setText(mDataAmount.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataName == null) {
            return 0;
        }
        return mDataName.size();
    }

    public void setData(ArrayList<String> listName, ArrayList<String> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mDataName.size();

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                mDataName.remove(0);
                mDataAmount.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
                    Edit.setOnMenuItemClickListener(onMenuItemClickListener);
                    Delete.setOnMenuItemClickListener(onMenuItemClickListener);
                }
            });
        }
    }


    private final MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            int id = item.getItemId();
            if (id == ADAPTER_EDIT) {

            } else if (id == ADAPTER_DELETE) {

            }

            return true;
        }
    };
}