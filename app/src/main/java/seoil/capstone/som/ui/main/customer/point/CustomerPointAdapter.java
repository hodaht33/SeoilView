package seoil.capstone.som.ui.main.customer.point;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;


public class CustomerPointAdapter extends RecyclerView.Adapter<CustomerPointAdapter.ViewHolder> {

    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private final Context mContext;

    private final List<Item> pointList;
    private final List<String> mPointDate;

    public CustomerPointAdapter(List<Item> point, Context context, List<String> pointDate) {

        pointList = point;

        mContext = context;
        mPointDate = pointDate;
    }

    @NonNull
    @Override
    public CustomerPointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        Context context = parent.getContext();


        switch (viewType) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.recycler_customer_point_header, parent, false);
                ViewHolder header = new ViewHolder(view);
                return header;
            case CHILD:
                TextView itemTextView = new TextView(context);
                itemTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return new CustomerPointAdapter.ViewHolder(itemTextView) {
                };
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPointAdapter.ViewHolder holder, int position) {

        final Item item = pointList.get(position);
        switch (item.type) {
            case HEADER:
                final ViewHolder itemController =  holder;
                itemController.refferalItem = item;
                itemController.headerTitle.setText(item.text);
                itemController.headerTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<>();
                            int count = 0;
                            int pos = pointList.indexOf(itemController.refferalItem);
                            while (pointList.size() > pos + 1 && pointList.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(pointList.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                        } else {
                            int pos = pointList.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                pointList.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:

                float dp = mContext.getResources().getDisplayMetrics().density;
                int subItemPaddingLeft = (int) (18 * dp);
                int subItemPaddingTopAndBottom = (int) (5 * dp);
                holder.headerTitle.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);

                int count = 0;
                for (int i = 0; i < position; i ++) {

                    if (pointList.get(i).invisibleChildren != null) {
                        count = count + pointList.get(i).invisibleChildren.size();
                    }
                }

                if (position > 1) {

                    final String temp = pointList.get(position).text + " : " + mPointDate.get(position + count);
                    holder.headerTitle.setText(temp);
                } else {

                    holder.headerTitle.setText(pointList.get(position).text);
                }

                break;
        }
    }



    @Override
    public int getItemCount() {
        if (pointList == null) {

            return 0;
        }
        return pointList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView headerTitle;
        public Item refferalItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTitle = itemView.findViewById(R.id.textViewCPointHeader);
        }
    }


    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }

}

