package com.example.lab2_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Hero> mHeros;
    private RecyclerView mRecyclerHero;
    private HeroAdapter mHeroAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerHero = findViewById(R.id.recycleHero);
        mHeros = new ArrayList<>();
        creatHeroList();
        mHeroAdapter = new HeroAdapter(this, mHeros);
        mRecyclerHero.setAdapter(mHeroAdapter);
        mRecyclerHero.setLayoutManager(new LinearLayoutManager(this));
    }

    private void creatHeroList() {
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
    }
}