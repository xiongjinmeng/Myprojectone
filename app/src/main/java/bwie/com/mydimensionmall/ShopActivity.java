package bwie.com.mydimensionmall;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import bwie.com.mydimensionmall.adapter.MyFragmentPagerAdapter;
import bwie.com.mydimensionmall.fragment.ClassificationFragment;
import bwie.com.mydimensionmall.fragment.HomepageFragment;
import bwie.com.mydimensionmall.fragment.ListnrFragment;
import bwie.com.mydimensionmall.fragment.MymallFragment;
import bwie.com.mydimensionmall.fragment.ShoppingcartFragment;
import bwie.com.mydimensionmall.uile.CustomViewPager;

public class ShopActivity extends AppCompatActivity {

    private CustomViewPager view_pager;
    private RadioButton bun_homepage;
    private RadioButton bun_classification;
    private RadioButton bun_shoppingcart;
    private RadioButton bun_listnr;
    private RadioButton bun_mymall;
    private ArrayList<Fragment> fragment;
    private MyFragmentPagerAdapter adapter;
    private MymallFragment mymall;
    private ListnrFragment listnr;
    private HomepageFragment homepage;
    private ClassificationFragment classification;
    private ShoppingcartFragment shoppingcart;
    private RadioGroup radio_bun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initLayout();
        initData();
        initListening();
}

    private void initLayout() {
        view_pager = findViewById(R.id.view_pager);
        radio_bun = findViewById(R.id.radio_bun);
        bun_homepage = findViewById(R.id.bun_homepage);
        bun_classification = findViewById(R.id.bun_classification);
        bun_shoppingcart = findViewById(R.id.bun_shoppingcart);
        bun_listnr = findViewById(R.id.bun_listnr);
        bun_mymall = findViewById(R.id.bun_mymall);
        view_pager.setOffscreenPageLimit(4);
        view_pager.setScanScroll(false);
    }

    private void initData() {
        fragment = new ArrayList<>();
        homepage = new HomepageFragment();
        classification = new ClassificationFragment();
        shoppingcart = new ShoppingcartFragment();
        listnr = new ListnrFragment();
        mymall = new MymallFragment();
        fragment.add(homepage);
        fragment.add(classification);
        fragment.add(shoppingcart);
        fragment.add(listnr);
        fragment.add(mymall);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setList(fragment);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view_pager.setAdapter(adapter);
            }
        });
    }

    private void initListening() {
        bun_homepage.setChecked(true);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        bun_homepage.setChecked(true);
                        break;
                    case 1:
                        bun_classification.setChecked(true);
                        break;
                    case 2:
                        bun_shoppingcart.setChecked(true);
                        break;
                    case 3:
                        bun_listnr.setChecked(true);
                        break;
                    case 4:
                        bun_mymall.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radio_bun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.bun_homepage:
                        view_pager.setCurrentItem(0);
                        break;
                    case R.id.bun_classification:
                        view_pager.setCurrentItem(1);
                        break;
                    case R.id.bun_shoppingcart:
                        view_pager.setCurrentItem(2);
                        break;
                    case R.id.bun_listnr:
                        view_pager.setCurrentItem(3);
                        break;
                    case R.id.bun_mymall:
                        view_pager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}
