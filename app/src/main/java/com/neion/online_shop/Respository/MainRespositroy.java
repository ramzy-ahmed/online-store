package com.neion.online_shop.Respository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.neion.online_shop.Domain.BannerModel;
import com.neion.online_shop.Domain.CategroyModel;
import com.neion.online_shop.Domain.ItemsModel;

import java.util.ArrayList;

public class MainRespositroy {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public LiveData<ArrayList<CategroyModel>> loadCategory(){
        MutableLiveData<ArrayList<CategroyModel>> listData = new MutableLiveData<>();
        DatabaseReference ref=firebaseDatabase.getReference("Category");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <CategroyModel> list = new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    CategroyModel item = dataSnapshot.getValue(CategroyModel.class);
                    if(item != null)list.add(item);
                }
                listData.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listData;
    }

    public LiveData<ArrayList<BannerModel>> loadBanner(){
        MutableLiveData<ArrayList<BannerModel>> listData = new MutableLiveData<>();
        DatabaseReference ref=firebaseDatabase.getReference("Banner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <BannerModel> list = new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    BannerModel item = dataSnapshot.getValue(BannerModel.class);
                    if(item != null)list.add(item);
                }
                listData.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listData;
    }

    public LiveData<ArrayList<ItemsModel>> loadPopular(){
        MutableLiveData<ArrayList<ItemsModel>> listData = new MutableLiveData<>();
        DatabaseReference ref=firebaseDatabase.getReference("Items");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <ItemsModel> list = new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ItemsModel item = dataSnapshot.getValue(ItemsModel.class);
                    if(item != null)list.add(item);
                }
                listData.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listData;
    }
}
