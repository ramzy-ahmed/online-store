package com.neion.online_shop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neion.online_shop.Domain.ItemsModel;
import com.neion.online_shop.Helper.ChangeNumberItemsListener;
import com.neion.online_shop.Helper.ManagmentCart;
import com.neion.online_shop.R;
import com.neion.online_shop.databinding.ViewholderCartBinding;
import com.neion.online_shop.databinding.ViewholderSizeBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder>{

private ArrayList<ItemsModel> listItemSelected;
private ChangeNumberItemsListener changeNumberItemsListener;
private ManagmentCart managmentCart;

    public CartAdapter(ArrayList<ItemsModel> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.managmentCart = new ManagmentCart(context);
    }

    @NonNull
@Override
public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new Viewholder(binding);
}

@SuppressLint("SetTextI18n")
@Override
public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
    holder.binding.titleTxt.setText(listItemSelected.get(position).getTitle());
    holder.binding.FeeEachItem.setText("$"+listItemSelected.get(position).getPrice());
    holder.binding.totalEachItem.setText("$"+Math.round(listItemSelected.get(position).getNumberinCart()*listItemSelected.get(position).getPrice()));
    holder.binding.numberItemTxt.setText(String.valueOf(listItemSelected.get(position).getNumberinCart()));

    Glide.with(holder.itemView.getContext()).load(listItemSelected.get(position).getPicUrl().get(0)).into(holder.binding.pic);

    holder.binding.plsuCartBtn.setOnClickListener(v->{
        managmentCart.plusItem(listItemSelected,position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        });
    });
    holder.binding.minsCartBtn.setOnClickListener(v->{
        managmentCart.minusItem(listItemSelected,position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        });
    });

}

@Override
public int getItemCount() {
    return listItemSelected.size();
}

public class Viewholder extends RecyclerView.ViewHolder {
    ViewholderCartBinding binding;

    public Viewholder(ViewholderCartBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
}