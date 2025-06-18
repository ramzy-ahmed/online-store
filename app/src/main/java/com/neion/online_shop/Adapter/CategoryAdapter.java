package com.neion.online_shop.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.neion.online_shop.Domain.CategroyModel;
import com.neion.online_shop.R;
import com.neion.online_shop.databinding.ViewhoderCategroyBinding;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategroyModel> items;
    private Context context;
    private int selectedPosition=-1;
    private int lastSelectedPosition =-1;

    public CategoryAdapter(ArrayList<CategroyModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewhoderCategroyBinding binding = ViewhoderCategroyBinding.inflate(LayoutInflater.from(context));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.getRoot().setOnClickListener(v->{
            lastSelectedPosition= selectedPosition;
            selectedPosition = position;
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);
        });
        if(selectedPosition==position){
            holder.binding.titleTxt.setBackgroundResource(R.drawable.button_bg);
            holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            holder.binding.titleTxt.setBackgroundResource(R.drawable.stroke);
            holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewhoderCategroyBinding binding;
        public ViewHolder(ViewhoderCategroyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

