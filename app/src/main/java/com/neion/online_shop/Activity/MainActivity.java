package com.neion.online_shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.neion.online_shop.Adapter.CategoryAdapter;
import com.neion.online_shop.Adapter.PopularAdapter;
import com.neion.online_shop.Adapter.SliderAdapter;
import com.neion.online_shop.Domain.BannerModel;
import com.neion.online_shop.R;
import com.neion.online_shop.ViewModel.MainViewModel;
import com.neion.online_shop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        viewModel = new MainViewModel();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        initCategroy();
        initSlider();
        initPopular();
        bottomNavigation();

        binding.cartBtn.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this,CardActivity.class));
        });
    }

    private void bottomNavigation() {
        binding.bottomNavigation.setItemSelected(R.id.home,true);
        binding.bottomNavigation.setOnItemSelectedListener(i -> {
        });
    }

    private void initPopular() {
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        viewModel.loadPopular().observeForever(itemsModels-> {
            if (!itemsModels.isEmpty()){
                binding.popularView.setLayoutManager(new LinearLayoutManager(
                        MainActivity.this,LinearLayoutManager.HORIZONTAL,false ));
                binding.popularView.setAdapter(new PopularAdapter(itemsModels));
                binding.popularView.setNestedScrollingEnabled(true);
            }
            binding.progressBarPopular.setVisibility(View.GONE);
        });
        viewModel.loadPopular();
    }

    private void initSlider() {
        binding.progressBarSlider.setVisibility(View.VISIBLE);
        viewModel.loadBanner().observeForever(bannerModels -> {
            if (!bannerModels.isEmpty()){
               banners(bannerModels);
                binding.progressBarSlider.setVisibility(View.GONE);
            }
        });
        viewModel.loadBanner();
    }

    private void banners(ArrayList<BannerModel> bannerModels) {
        binding.viewPageSlider.setAdapter(new SliderAdapter(bannerModels,binding.viewPageSlider));
        binding.viewPageSlider.setClipToPadding(false);
        binding.viewPageSlider.setClipChildren(false);
        binding.viewPageSlider.setOffscreenPageLimit(3);
        binding.viewPageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        binding.viewPageSlider.setPageTransformer(compositePageTransformer);
    }

    private void initCategroy() {
        binding.progressBarCategroy.setVisibility(View.VISIBLE);
        viewModel.loadCategroy().observeForever(categroyModels -> {
            binding.categoryView.setLayoutManager(new LinearLayoutManager(
                    MainActivity.this,LinearLayoutManager.HORIZONTAL,false ));
            binding.categoryView.setAdapter(new CategoryAdapter(categroyModels));
            binding.categoryView.setNestedScrollingEnabled(true);
            binding.progressBarCategroy.setVisibility(View.GONE);
        });
    }
}