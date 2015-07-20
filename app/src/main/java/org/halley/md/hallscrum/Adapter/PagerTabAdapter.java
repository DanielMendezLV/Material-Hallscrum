package org.halley.md.hallscrum.Adapter;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import org.halley.md.hallscrum.Fragment.Fragment_Meta;
import org.halley.md.hallscrum.Fragment.Fragment_Proyect;
import org.halley.md.hallscrum.Fragment.Fragment_Team;
import org.halley.md.hallscrum.R;
import android.content.Context;


/**
 * Created by Mendez Diaz on 07/06/2015.
 */
public class PagerTabAdapter extends FragmentStatePagerAdapter {
    //array de tabass
    private String[] tabs;
    private int[] icons = {R.drawable.ic_action_proyect,R.drawable.ic_action_team};
    private Context context;

    //El adaptador neceista de un fragmentManager
    public PagerTabAdapter(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        tabs= context.getResources().getStringArray(R.array.tabs);

    }

    //Cuando se obtiene el elemento se retorna el fragment
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment= null;

        switch (position){
            case 0:
                fragment  = new Fragment_Proyect();
                break;
            case 1:
                fragment  = new Fragment_Team();
                break;

        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position){
        Drawable drawable = context.getResources().getDrawable(icons[position]);
        drawable.setBounds(0,0,36,36);
        ImageSpan imageSpan= new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");

        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getCount() {
        return 2;
    }
}







