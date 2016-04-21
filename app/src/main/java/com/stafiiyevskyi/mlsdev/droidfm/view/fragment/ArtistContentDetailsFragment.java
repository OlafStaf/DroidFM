package com.stafiiyevskyi.mlsdev.droidfm.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.stafiiyevskyi.mlsdev.droidfm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by oleksandr on 21.04.16.
 */
public class ArtistContentDetailsFragment extends BaseFragment {

    public static final String ARTIST_MBID_BUNDLE_KEY = "artist_conten_details_mbid";

    @Bind(R.id.vp_content)
    ViewPager mVpTabContent;
    @Bind(R.id.tabs)
    TabLayout mTlTabs;


    public static BaseFragment newInstance(@NonNull String artistMbid) {

        Bundle args = new Bundle();
        args.putString(ARTIST_MBID_BUNDLE_KEY, artistMbid);
        BaseFragment fragment = new ArtistContentDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViewPager(mVpTabContent, "");
        mTlTabs.setupWithViewPager(mVpTabContent);
    }

    private void setupViewPager(ViewPager viewPager, String mbid) {

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(ArtistSearchListFragment.newInstance(), getActivity().getString(R.string.tab_title_top_albums));
        adapter.addFragment(ArtistSearchListFragment.newInstance(), getActivity().getString(R.string.tab_title_top_tracks));
        viewPager.setAdapter(adapter);
    }


    @Override
    protected int getResourceId() {
        return R.layout.fragment_artist_content_details;
    }

    class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<BaseFragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public FragmentViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(BaseFragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
