package org.halley.md.hallscrum.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.halley.md.hallscrum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Proyect extends android.support.v4.app.Fragment {


    public Fragment_Proyect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__proyect, container, false);
    }

    public static void getInstance(int position){

    }

}
