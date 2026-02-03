package com.example.practica5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "Formulario", R.drawable.ic_form);
        adapter.addFragment(new SecondFragment(), "Calculadora", R.drawable.ic_calc);
        adapter.addFragment(new ThirdFragment(), "Bar", R.drawable.ic_bar);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(adapter.getIcon(i));
        }

        fab.setImageResource(R.drawable.ic_form);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fab.setImageResource(R.drawable.ic_form);
                        break;
                    case 1:
                        fab.setImageResource(R.drawable.ic_calc);
                        break;
                    case 2:
                        fab.setImageResource(R.drawable.ic_bar);
                        break;
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Acción del FAB → Snackbar
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Acción confirmada", Snackbar.LENGTH_LONG)
                        .setAction("OK", v -> {})
                        .show()
        );
    }
}
