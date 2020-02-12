package com.example.icitieslxnp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class InternalActivity extends AppCompatActivity {

    private TabLayout contenedorTabLayout;
    private ViewPager contFragmenstVP;

    BDAdapter bdAdapter;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        contenedorTabLayout = findViewById(R.id.contenedorTabLayout);
        contFragmenstVP = findViewById(R.id.ContenedorFragmenstVP);

        contenedorTabLayout.setupWithViewPager(contFragmenstVP);

        setUpViewPager(contFragmenstVP);

        String[] from = new String[] {"nombre","pais"};
        int[] to = new int[] {R.id.ciudadTV,R.id.paisTV};

        setContentView(R.layout.activity_internal);
        bdAdapter = new BDAdapter(this);

        Ciudad ciudad = new Ciudad("Alicante" ,"España");

        Cursor cursor = bdAdapter.insertarUnDato(ciudad,sqLiteDatabase);
        if (cursor != null){
            RecyclerView desplegable = findViewById(R.id.ciudades_ReciclerView);
            MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(cursor,R.layout.fragment_fragment_ciudades,from,to);
            desplegable.setAdapter(myRecyclerAdapter);
            desplegable.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        }



    }

    private void setUpViewPager(ViewPager contFragmenstVP) {
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragment(new FragmentCiudades(),"Ciudades");
        tabViewPagerAdapter.addFragment(new FragmentPerfil(),"Perfil");

        contFragmenstVP.setAdapter(tabViewPagerAdapter);
        contFragmenstVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(contenedorTabLayout));

    }
}
