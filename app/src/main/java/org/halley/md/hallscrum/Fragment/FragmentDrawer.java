package org.halley.md.hallscrum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;

import org.halley.md.hallscrum.Activity.AboutUsActivity;
import org.halley.md.hallscrum.Activity.ConfigActivity;
import org.halley.md.hallscrum.Activity.HelpActivity;
import org.halley.md.hallscrum.Activity.LoginActivity;
import org.halley.md.hallscrum.Activity.MarkupActivity;
import org.halley.md.hallscrum.Activity.SearchActivity;
import org.halley.md.hallscrum.Adapter.TarjetAdapter;
import org.halley.md.hallscrum.Model.TarjetRecycler;
import org.halley.md.hallscrum.R;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;



public class FragmentDrawer extends Fragment implements TarjetAdapter.ClickListener {
    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_DRAWER="user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private TarjetAdapter adapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;


    public FragmentDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new TarjetAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Cuando el recycler view es a
        // ñadido a la lista debe de contar con un layout.
        return layout;
    }

    public static List<TarjetRecycler> getData(){
        List<TarjetRecycler> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_action_favorite, R.drawable.ic_action_config,R.drawable.ic_action_help,R.drawable.ic_action_about, R.drawable.ic_action_logout};
        String[] titles = {"Marcadores","Ajustes ", "Ayuda y Sugerencias", "Acerca De", "Logout"};
        for(int i=0; i<titles.length && i<icons.length;i++){
            TarjetRecycler current = new TarjetRecycler();
            current.idIcon = icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
       // int[] icons =
    }

    //int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar){
       // System.out.println("..... drawerLayout");
        mDrawerLayout = drawerLayout;
        //System.out.println("...toogle");
        System.out.println("... llego aca?");
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),
                drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveInPreferences(getActivity(), KEY_USER_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        //System.out.println("... set drawr listener");
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveInPreferences(Context context,String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor=sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context,String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        switch (position){
            case 0:
                startActivity(new Intent(getActivity(), MarkupActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), ConfigActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }

    }
}