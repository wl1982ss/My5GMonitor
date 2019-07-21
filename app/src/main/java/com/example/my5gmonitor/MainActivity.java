package com.example.my5gmonitor;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // 声明 ViewPager
    private ViewPager mViewpager;

    // 声明四个Tab
    private LinearLayout mTabHome;
    private LinearLayout    mTabFlow;
    private LinearLayout    mTabFinance;
    private LinearLayout mTabSetting;

    // 声明四个 ImageButton
    private ImageButton mHomeImg;
    private ImageButton mFlowImg;
    private ImageButton mFinanceImg;
    private ImageButton mSettingImg;

    // 声明 ViewPager 的适配器
    private PagerAdapter mAdapter;
    // 用于装载四个Tab的List
    private List<View> mTabs   =   new ArrayList<View>();

    private ListView listView;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();//初始化控件
        initDatas();//初始化数据
        initEvents();//初始化事件
    }

    //初始化控件
    private void initViews() {
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabFlow = (LinearLayout) findViewById(R.id.id_tab_meter);
        mTabFinance = (LinearLayout) findViewById(R.id.id_tab_camera);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        mHomeImg = (ImageButton) findViewById(R.id.id_tab_home_img);
        mFlowImg = (ImageButton) findViewById(R.id.id_tab_meter_img);
        mFinanceImg = (ImageButton) findViewById(R.id.id_tab_camera_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_setting_img);

        // 获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View    tab1    =   inflater.inflate(R.layout.tab1, null);
        View    tab2    =   inflater.inflate(R.layout.tab2, null);
        View    tab3    =   inflater.inflate(R.layout.tab3,null);
        View    tab4    =   inflater.inflate(R.layout.tab4, null);

        // 将四个Tab添加到集合中
        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);
        mTabs.add(tab4);

    }

    private void initDatas() {
        // 初始化 viewPager 的适配器
        mAdapter    =   new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup containter, int position) {
                View view = mTabs.get(position);
                containter.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup containter, int position, Object object) {
                containter.removeView(mTabs.get(position));
            }
        };

        // 设置 viewPager 的适配器
        mViewpager.setAdapter(mAdapter);
    }

    private void    initEvents() {
        // 设置四个Tab的点击事件
        mTabHome.setOnClickListener(this);
        mTabFlow.setOnClickListener(this);
        mTabFinance.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

        // 添加 viewPager 的切换 Tab 的监听事件
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 获取 ViewPager 的当前 Tab
                int currentItem = mViewpager.getCurrentItem();
                // 将所有的 ImageButton 设置成灰色
                resetImgs();
                // 将当前 Tab 对应的 ImageButton 设置成绿色
                switch(currentItem) {
                    case 0:
                        mHomeImg.setImageResource(R.mipmap.tab_home_pressed);
                        break;
                    case 1:
                        mFlowImg.setImageResource(R.mipmap.tab_meter_pressed);
                        break;
                    case 2:
                        mFinanceImg.setImageResource(R.mipmap.tab_camera_pressed);
                        break;
                    case 3:
                        mSettingImg.setImageResource(R.mipmap.tab_setting_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        // 先将四个ImageButton 都设置成灰色
        resetImgs();
        switch(v.getId()) {
            case R.id.id_tab_home:
                // 设置viewPager 的当前 Tab
                mViewpager.setCurrentItem(0);
                // 将当前 Tab 对应的 ImageButton 设置成绿色
                mHomeImg.setImageResource(R.mipmap.tab_home_pressed);
                break;
            case R.id.id_tab_meter:
                mViewpager.setCurrentItem(1);
                mFlowImg.setImageResource(R.mipmap.tab_meter_pressed);

                listView = (ListView) findViewById(R.id.listView1);
                //步骤1 一个列表项的内容，就是一个item
                Map<String, Object> item1 = new HashMap<String, Object>();
                item1.put("image", R.drawable.tab_meter_pressed);
                item1.put("name", "1号电流表");
                item1.put("value", "35mA");
                item1.put("status", "正常");

                Map<String, Object> item2 = new HashMap<String, Object>();
                item2.put("image", R.drawable.tab_meter_pressed);
                item2.put("name", "2号电流表");
                item2.put("value", "38mA");
                item2.put("status", "正常");

                Map<String, Object> item3 = new HashMap<String, Object>();
                item3.put("image", R.drawable.tab_meter_pressed);
                item3.put("name", "3号电流表");
                item3.put("value", "25mA");
                item3.put("status", "正常");

                //步骤1：一个列表项的内容，就是一个item，即一个Map
                Map<String, Object> item4 = new HashMap<String, Object>();
                item4.put("image", R.drawable.tab_meter_pressed);
                item4.put("name", "1号电压表");
                item4.put("value", "25 V");
                item4.put("status", "正常");

                Map<String, Object> item5 = new HashMap<String, Object>();
                item5.put("image", R.drawable.tab_meter_pressed);
                item5.put("name", "2号电压表");
                item5.put("value", "35 V");
                item5.put("status", "正常");

                //步骤2：把这些Map放到List当中
                List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                data.add(item1);
                data.add(item2);
                data.add(item3);
                data.add(item4);
                data.add(item5);

                //注意：第四个参数和第五个参数要一一对应
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
                        R.layout.activity_list_item, new String[] { "image", "name", "value", "status" }, new int[] { R.id.imageView1, R.id.textView1, R.id.meter_value, R.id.meter_status });

                //步骤3：将List中的内容填充到listView里面去
                listView.setAdapter(simpleAdapter);

                break;
            case R.id.id_tab_camera:
                mViewpager.setCurrentItem(2);
                mFinanceImg.setImageResource(R.mipmap.tab_camera_pressed);

                listView2 = (ListView) findViewById(R.id.listView2);
                //步骤1 一个列表项的内容，就是一个item
                Map<String, Object> deviceItem1 = new HashMap<String, Object>();
                deviceItem1.put("image", R.drawable.tab_camera_pressed);
                deviceItem1.put("name", "1号摄像头");
                deviceItem1.put("status", "正常");

                Map<String, Object> deviceItem2 = new HashMap<String, Object>();
                deviceItem2.put("image", R.drawable.tab_camera_pressed);
                deviceItem2.put("name", "2号摄像头");
                deviceItem2.put("status", "正常");

                Map<String, Object> deviceItem3 = new HashMap<String, Object>();
                deviceItem3.put("image", R.drawable.tab_camera_pressed);
                deviceItem3.put("name", "3号摄像头");
                deviceItem3.put("status", "正常");

                //步骤1：一个列表项的内容，就是一个item，即一个Map
                Map<String, Object> deviceItem4 = new HashMap<String, Object>();
                deviceItem4.put("image", R.drawable.robot);
                deviceItem4.put("name", "巡检机器人1号");
                deviceItem4.put("status", "正常");

                Map<String, Object> deviceItem5 = new HashMap<String, Object>();
                deviceItem5.put("image", R.drawable.robot);
                deviceItem5.put("name", "巡检机器人2号");
                deviceItem5.put("status", "正常");

                //步骤2：把这些Map放到List当中
                List<Map<String, Object>> deviceDatas = new ArrayList<Map<String, Object>>();
                deviceDatas.add(deviceItem1);
                deviceDatas.add(deviceItem2);
                deviceDatas.add(deviceItem3);
                deviceDatas.add(deviceItem4);
                deviceDatas.add(deviceItem5);

                //注意：第四个参数和第五个参数要一一对应
                SimpleAdapter deviceAdapter = new SimpleAdapter(this, deviceDatas,
                        R.layout.activity_device_item, new String[] { "image", "name", "status" }, new int[] { R.id.device_image, R.id.device_name, R.id.device_status });

                //步骤3：将List中的内容填充到listView里面去
                listView2.setAdapter(deviceAdapter);

                break;
            case R.id.id_tab_setting:
                mViewpager.setCurrentItem(3);
                mSettingImg.setImageResource(R.mipmap.tab_setting_pressed);
                break;
        }
    }

    // 将四个 ImageButton 设置成灰色
    private void resetImgs() {
        mHomeImg.setImageResource(R.mipmap.tab_home);
        mFlowImg.setImageResource(R.mipmap.tab_meter);
        mFinanceImg.setImageResource(R.mipmap.tab_camera);
        mSettingImg.setImageResource(R.mipmap.tab_setting);
    }
}
