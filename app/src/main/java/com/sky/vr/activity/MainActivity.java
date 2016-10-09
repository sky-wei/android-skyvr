package com.sky.vr.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;
import com.sky.vr.fragment.AboutFragment_;
import com.sky.vr.fragment.SettingFragment;
import com.sky.vr.fragment.VideoFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends VRBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Override
    public void initView() {

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // 切换到视频界面
        switchFragment(VideoFragment_.class);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_video) {
            // 切换到视频界面
            switchFragment(VideoFragment_.class);
        } else if (id == R.id.nav_game) {

        } else if (id == R.id.nav_picture) {

        } else if (id == R.id.nav_manager) {

        } else if (id == R.id.nav_settings) {
            // 进入设置界面
            CommonActivity_
                    .intent(getContext())
                    .title(R.string.settings)
                    .fname(SettingFragment.class.getName())
                    .supportFragment(false)
                    .start();
        } else if (id == R.id.nav_about) {
            // 进入关于界面
            CommonActivity_
                    .intent(getContext())
                    .title(R.string.about)
                    .fname(AboutFragment_.class.getName())
                    .start();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Class<? extends Fragment> classes) {

        if (classes == null) return ;

        FragmentManager manager = getSupportFragmentManager();
        Fragment curFragment = manager.findFragmentById(R.id.fl_content);

        if (curFragment != null
                && classes.equals(curFragment.getClass())) {
            // 相同的，不需要处理
            return ;
        }

        Fragment fragment = Fragment.instantiate(getContext(), classes.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
    }
}
