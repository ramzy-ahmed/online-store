package com.neion.online_shop.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.neion.online_shop.Adapter.CartAdapter;
import com.neion.online_shop.Helper.ChangeNumberItemsListener;
import com.neion.online_shop.Helper.ManagmentCart;
import com.neion.online_shop.databinding.ActivityCardBinding;

public class CardActivity extends AppCompatActivity {
    private ActivityCardBinding binding;
    private double tax;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        managmentCart = new ManagmentCart(this);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> finish());
        calcualtorCart();
        initCardList();

    }

    private void initCardList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.mainView.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.mainView.setVisibility(View.VISIBLE);
        }

        binding.cardView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.cardView.setAdapter(new CartAdapter(managmentCart.getListCart(), this, this::calcualtorCart));
    }

    @SuppressLint("SetTextI18n")
    private void calcualtorCart() {
        double percentTax =0.02;
        double delivery= 10;
        tax=Math.round((managmentCart.getTotalFee() * percentTax *100.0));

        double total = Math.round( (managmentCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round( (managmentCart.getTotalFee() *100.0)) / 100.0;
        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$"+tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);
    }
}