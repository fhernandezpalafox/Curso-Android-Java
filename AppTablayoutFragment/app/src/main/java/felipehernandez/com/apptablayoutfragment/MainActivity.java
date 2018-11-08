package felipehernandez.com.apptablayoutfragment;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Adaptadores.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar appbar;
    private TabLayout tabLayout;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolbarInicializador();
        TabLayoutInicializador();
        ViewPagerInicialializador();
    }


    public void ToolbarInicializador(){

        appbar = findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        //getSupportActionBar().setTitle("Principal");

    }

    public void TabLayoutInicializador(){

        tabLayout = findViewById(R.id.appbartabs);
        tabLayout.addTab(tabLayout.newTab().setText("Aplicación 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Aplicación 6"));
        tabLayout.addTab(tabLayout.newTab().setText("Aplicación Retrofit"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    public void  ViewPagerInicialializador(){

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),
                                                      tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu =  menu;
        getMenuInflater().inflate(R.menu.menutoolbar1, menu);
        return true;
    }
    */


   /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.mnu1:
                return true;
            case  R.id.mnu2:
                return true;
            case R.id.menu4:
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
    */
}
