package com.example.practica5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        fab.setOnClickListener(view ->
                Snackbar.make(view, "Acción confirmada", Snackbar.LENGTH_LONG)
                        .setAction("OK", v -> {})
                        .show()
        );
    }
}
