package seoil.capstone.som.ui.main.manager.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerEventAdapter extends RecyclerView.Adapter<ManagerEventAdapter.ViewHolder> {

    
    private ArrayList<Integer> mEventCode = new ArrayList<>();
    private ArrayList<String> mEventName = new ArrayList<>();
    private ArrayList<String> mEventEndDate = new ArrayList<>();
    private ArrayList<String> mEventStartDate = new ArrayList<>();


    public ManagerEventAdapter(ArrayList<String> eventName, ArrayList<Integer> eventCode,
                               ArrayList<String> eventStartDate, ArrayList<String> eventEndDate) {

        mEventCode = eventCode;
        mEventName = eventName;
        mEventStartDate = eventStartDate;
        mEventEndDate = eventEndDate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_manager_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewContent.setText(mEventName.get(position));
        final String temp = mEventStartDate.get(position) + "~" + mEventEndDate.get(position);
        holder.textViewDate.setText(temp);

    }

    @Override
    public int getItemCount() {
        if (mEventName == null) {

            return 0;
        }
        return mEventName.size();
    }

    public void clear() {

        if (mEventName == null) {

            return;
        }

        int size = mEventName.size();

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                mEventName.remove(0);
                mEventCode.remove(0);
                mEventStartDate.remove(0);
                mEventEndDate.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    public void setData(ArrayList<String> eventName,ArrayList<Integer> eventCode, ArrayList<String> eventStartDate, ArrayList<String> eventEndDate) {

        clear();

        mEventName = eventName;
        mEventCode = eventCode;
        mEventStartDate = eventStartDate;
        mEventEndDate = eventEndDate;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewContent = itemView.findViewById(R.id.textViewMEventRecyclerContentName);
            textViewDate = itemView.findViewById(R.id.textViewMEventRecyclerDate);

            textViewContent.setOnClickListener(this);
            textViewDate.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //클릭시 상세정보 인텐트 처리
        }
    }
}
