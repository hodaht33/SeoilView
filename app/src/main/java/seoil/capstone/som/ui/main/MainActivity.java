package seoil.capstone.som.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import seoil.capstone.som.R;
import seoil.capstone.som.data.db.model.User;
import seoil.capstone.som.ui.main.customer.home.CustomerHomeFragment;
import seoil.capstone.som.ui.main.customer.point.CustomerPointFragment;
import seoil.capstone.som.ui.main.customer.search.CustomerSearchFragment;
import seoil.capstone.som.ui.main.manager.event.ManagerEventFragment;
import seoil.capstone.som.ui.main.manager.home.ManagerHomeFragment;
import seoil.capstone.som.ui.main.manager.statistics.ManagerStatisticsFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View, MainCommunicator.Communicator, BottomNavigationView.OnNavigationItemSelectedListener {

    private long mLastTimeBackPressed = 0;
    private BottomNavigationView mNavView;
    private int mFragmentLayoutId;
    private int mUserCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userId = getIntent().getStringExtra("userId");
        Toast.makeText(this, "Login to " + userId, Toast.LENGTH_LONG);

        mUserCode = getIntent().getIntExtra("code", User.USER_CUSTOMER);

        mNavView = findViewById(R.id.bottomNavMain);
        mFragmentLayoutId = R.id.fragmentLayoutMain;

        if (mUserCode == User.USER_CUSTOMER) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentLayoutMain, new CustomerHomeFragment())
                    .commit();
        } else if (mUserCode == User.USER_MANAGER) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentLayoutMain, new ManagerHomeFragment())
                    .commit();
        }

        inflateBottomNavMenu();

        mNavView.setOnNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        if(System.currentTimeMillis() - mLastTimeBackPressed < 1000) {
//            finish();
//
//            return;
//        }
//
//        mLastTimeBackPressed = System.currentTimeMillis();
//        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//    }

    // 하단 네비게이션 메뉴 설정
    private void inflateBottomNavMenu() {
        switch (mUserCode) {
            case User.USER_CUSTOMER:
                mNavView.inflateMenu(R.menu.menu_main_customer_bottom_nav);

                break;
            case User.USER_MANAGER:
                mNavView.inflateMenu(R.menu.menu_main_manager_bottom_nav);

                break;
            default:
                Toast.makeText(getBaseContext(), "wrong id", Toast.LENGTH_SHORT).show();

                mNavView.inflateMenu(R.menu.menu_main_customer_bottom_nav);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        if (mUserCode == User.USER_CUSTOMER) {
            switch (item.getItemId()) {
                case R.id.item_customer_home_fragment:
                    selectedFragment = new CustomerHomeFragment();

                    break;
                case R.id.item_customer_point_fragment:
                    selectedFragment = new CustomerPointFragment();

                    break;
                case R.id.item_customer_search_fragment:
                    selectedFragment = new CustomerSearchFragment();

                    break;
                default:
                    return false;
            }
        } else if (mUserCode == User.USER_MANAGER) {
            switch (item.getItemId()) {
                case R.id.item_manager_home_fragment:
                    selectedFragment = new ManagerHomeFragment();

                    break;
                case R.id.item_manager_event_fragment:
                    selectedFragment = new ManagerEventFragment();

                    break;
                case R.id.item_manager_statistics_fragment:
                    selectedFragment = new ManagerStatisticsFragment();

                    break;
                default:
                    return false;
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentLayoutMain, selectedFragment)
                .commit();


        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public int logout() {


        return 0;
    }
}