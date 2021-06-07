package seoil.capstone.som.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.customer.bookmark.CustomerBookmarkFragment;
import seoil.capstone.som.ui.main.customer.info.CustomerInfoFragment;
import seoil.capstone.som.ui.main.customer.point.CustomerPointFragment;
import seoil.capstone.som.ui.main.customer.search.CustomerSearchFragment;
import seoil.capstone.som.ui.main.manager.event.ManagerEventFragment;
import seoil.capstone.som.ui.main.manager.info.ManagerInfoFragment;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerFragment;
import seoil.capstone.som.ui.main.manager.statistics.ManagerStatisticsFragment;

// 로그인 후 프레그먼트들을 제어하여 화면을 보여주는 액티비티
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mNavView;
    private String mUserCode;
    private long mLastTimeBackPressed = 0;
    private int mFragmentLayoutId;
    private int currentSelectedFragmentNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserCode = ((GlobalApplication) getApplicationContext()).getUserCode();

        mNavView = findViewById(R.id.bottomNavMain);
        mFragmentLayoutId = R.id.fragmentLayoutMain;

        if (mUserCode.equals("C")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(mFragmentLayoutId, new CustomerBookmarkFragment())
                    .commit();
        } else if (mUserCode.equals("M")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(mFragmentLayoutId, new ManagerLedgerFragment())
                    .commit();
        }

        inflateBottomNavMenu();

        mNavView.setOnNavigationItemSelectedListener(this);
    }

    // 뒤로가기 버튼 빠르게 두번 클릭하여 종료
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

                ((GlobalApplication) getApplicationContext()).logout();

                finish();
        }
    }

    // 네비게이션 아이템 선택하여 프레그먼트 뷰 변경
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment selectedFragment = null;

        if (mUserCode.equals("C")) {

            switch (item.getItemId()) {

                case R.id.item_customer_bookmark_fragment:

                    if (currentSelectedFragmentNum != R.id.item_customer_bookmark_fragment) {

                        currentSelectedFragmentNum = R.id.item_customer_bookmark_fragment;
                        selectedFragment = new CustomerBookmarkFragment();
                    }

                    break;
                case R.id.item_customer_point_fragment:

                    if (currentSelectedFragmentNum != R.id.item_customer_point_fragment) {

                        currentSelectedFragmentNum = R.id.item_customer_point_fragment;
                        selectedFragment = new CustomerPointFragment();
                    }

                    break;
                case R.id.item_customer_search_fragment:

                    if (currentSelectedFragmentNum != R.id.item_customer_search_fragment) {

                        currentSelectedFragmentNum = R.id.item_customer_search_fragment;
                        selectedFragment = new CustomerSearchFragment();
                    }

                    break;
                case R.id.item_customer_info_fragment:

                    if (currentSelectedFragmentNum != R.id.item_customer_info_fragment) {

                        currentSelectedFragmentNum = R.id.item_customer_info_fragment;
                        selectedFragment = new CustomerInfoFragment();
                    }

                    break;
                default:

                    return false;
            }
        } else if (mUserCode.equals("M")) {

            switch (item.getItemId()) {

                case R.id.item_manager_ledger_fragment:

                    if (currentSelectedFragmentNum != R.id.item_manager_ledger_fragment) {

                        currentSelectedFragmentNum = R.id.item_manager_ledger_fragment;
                        selectedFragment = new ManagerLedgerFragment();
                    }

                    break;
                case R.id.item_manager_event_fragment:

                    if (currentSelectedFragmentNum != R.id.item_manager_event_fragment) {

                        currentSelectedFragmentNum = R.id.item_manager_event_fragment;
                        selectedFragment = new ManagerEventFragment();
                    }

                    break;
                case R.id.item_manager_statistics_fragment:

                    if (currentSelectedFragmentNum != R.id.item_manager_statistics_fragment) {

                        currentSelectedFragmentNum = R.id.item_manager_statistics_fragment;
                        selectedFragment = new ManagerStatisticsFragment();
                    }

                    break;
                case R.id.item_manager_info_fragment:

                    if (currentSelectedFragmentNum != R.id.item_manager_info_fragment) {

                        currentSelectedFragmentNum = R.id.item_manager_info_fragment;
                        selectedFragment = new ManagerInfoFragment();
                    }

                    break;
                default:

                    return false;
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(mFragmentLayoutId, selectedFragment)
                .commit();

        return true;
    }
}