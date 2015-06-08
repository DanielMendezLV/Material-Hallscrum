package org.halley.md.hallscrum;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.halley.md.hallscrum.Activity.SearchActivity;
import org.halley.md.hallscrum.Fragment.FragmentDrawer;
import org.halley.md.hallscrum.Tabs.SlidingTabLayout;
import org.halley.md.hallscrum.Adapter.PagerTabAdapter;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private FragmentDrawer mDrawerFragment;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);//
        //Yo ya no quiero usar tu toolbar, por esto yo te envio el mio para que le des soporte
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //System.out.println("Funciona aca");
        //the fragment layout fragment_navigation_drawer no funciona
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

        mPager = (ViewPager)findViewById((R.id.pager));
        //aca envi un adaptador para administrar los fragments
        //mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPager.setAdapter(new PagerTabAdapter(getSupportFragmentManager(), this.getApplicationContext()));
        mTabs=(SlidingTabLayout)findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view,R.id.tabText);
        mTabs.setViewPager(mPager);

    }

    //mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.action_search){
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
