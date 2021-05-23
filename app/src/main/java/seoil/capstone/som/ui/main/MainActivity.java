package seoil.capstone.som.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.customer.bookmark.CustomerBookmarkFragment;
import seoil.capstone.som.ui.main.customer.point.CustomerPointFragment;
import seoil.capstone.som.ui.main.customer.search.CustomerSearchFragment;
import seoil.capstone.som.ui.main.manager.event.ManagerEventFragment;
import seoil.capstone.som.ui.main.manager.home.ManagerHomeFragment;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerFragment;
import seoil.capstone.som.ui.main.manager.statistics.ManagerStatisticsFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View, MainCommunicator.Communicator, BottomNavigationView.OnNavigationItemSelectedListener {

    private long mLastTimeBackPressed = 0;
    private BottomNavigationView mNavView;
    private int mFragmentLayoutId;
    private String mUserCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userId = getIntent().getStringExtra("userId");

        mUserCode = getIntent().getBundleExtra("data").getString("code", "C");

        mNavView = findViewById(R.id.bottomNavMain);
        mFragmentLayoutId = R.id.fragmentLayoutMain;

        if (mUserCode.equals("C")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentLayoutMain, new CustomerPointFragment())
                    .commit();
        } else if (mUserCode.equals("M")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentLayoutMain, new ManagerHomeFragment())
                    .commit();
        }

        inflateBottomNavMenu();

        mNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() - mLastTimeBackPressed < 1000) {
            finish();

            return;
        }

        mLastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
    }

    // 하단 네비게이션 메뉴 설정
    private void inflateBottomNavMenu() {
        switch (mUserCode) {

            case "C":

                mNavView.inflateMenu(R.menu.menu_main_customer_bottom_nav);
                break;
            case "M":

                mNavView.inflateMenu(R.menu.menu_main_manager_bottom_nav);
                break;
            default:

                Toast.makeText(getBaseContext(), "알 수 없는 오류 발생", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        if (mUserCode.equals("C")) {

            switch (item.getItemId()) {

                case R.id.item_customer_point_fragment:

                    selectedFragment = new CustomerPointFragment();
                    break;
                case R.id.item_customer_search_fragment:

                    selectedFragment = new CustomerSearchFragment();
                    break;
                case R.id.item_customer_bookmark_fragment:

                    selectedFragment = new CustomerBookmarkFragment();
                    break;
                default:

                    return false;
            }
        } else if (mUserCode.equals("M")) {

            switch (item.getItemId()) {

                case R.id.item_manager_home_fragment:

                    selectedFragment = new ManagerHomeFragment();
                    break;
                case R.id.item_manager_ledger_fragment:

                    selectedFragment = new ManagerLedgerFragment();
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