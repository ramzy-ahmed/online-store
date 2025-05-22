package com.neion.online_shop.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.neion.online_shop.Domain.BannerModel;
import com.neion.online_shop.Domain.CategroyModel;
import com.neion.online_shop.Domain.ItemsModel;
import com.neion.online_shop.Respository.MainRespositroy;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private final MainRespositroy respositroy =new MainRespositroy();
    public LiveData <ArrayList<CategroyModel>> loadCategroy(){
        return respositroy.loadCategory();
    }
    public LiveData <ArrayList<BannerModel>> loadBanner(){
        return respositroy.loadBanner();
    }
    public LiveData <ArrayList<ItemsModel>> loadPopular(){
        return respositroy.loadPopular();
    }
}
