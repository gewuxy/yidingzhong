package com.yidongzhong.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidingzhong.uikit.common.permission.MPermission;
import com.yidingzhong.uikit.common.permission.annotation.OnMPermissionDenied;
import com.yidingzhong.uikit.common.permission.annotation.OnMPermissionGranted;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.account.activity.LoginActivity;
import com.yidongzhong.account.util.AccountUtil;
import com.yidongzhong.main.fragment.CategoryFragment;
import com.yidongzhong.main.fragment.HomeFragment;
import com.yidongzhong.main.fragment.LatestLotteryFragment;
import com.yidongzhong.main.fragment.MyCenterFragment;
import com.yidongzhong.main.fragment.ShoppingCarFragment;

/**
 * Created by zex on 2017/8/13.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private String TAG = MainActivity.class.getSimpleName();
    private static final String EXTRA_APP_QUIT = "APP_QUIT";
    private final int BASIC_PERMISSION_REQUEST_CODE = 100;

    private View mBottomTabHome,mBottomTabCategory,mBottomTabLatestLottery,mBottomTabShoppingCar,mBottomTabMe;
    private ImageView mIvBottomTabHome,mIvBottomTabCategory,mIvBottomTabLatestLottery,
            mIvBottomTabShoppingCar,mIvBottomTabMe;
    private TextView mTvBottomTabHome,mTvBottomTabCategory,mTvBottomTabLatestLottery,mTvBottomTabShoppingCar,
            mTvBottomTabMe;

    private TFragment homeFragment,categoryFrgment,latestLotteryFragment,shoppingCarFragment,
            myCenterFragment,currentFragment;

    public static boolean isForeground = false;

    public static void start(Context context) {
        start(context,null);
    }

    public static void start(Context context,Intent extras) {
        Intent intent = new Intent(context,MainActivity.class);
        intent.setClass(context, MainActivity.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onParseIntent();
        setContentView(R.layout.activity_main);
        requestBasicPermission();
        initView();
        initListener();

        //CrashReport.setUserId(ApplicationCache.getUser().getId() + "");

        //检查版本更新
        getVersionInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.v(TAG,"onNewIntent");
        setIntent(intent);
        onParseIntent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.clear();
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
    }

    private void onParseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_APP_QUIT)) {
            onLogout();
            return;
        }
    }

    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }

    // 注销
    private void onLogout() {
        // 清理缓存&注销监听
        AccountUtil.logout();

        //JPush clear Alias
        /*JPushInterface.setAlias(MainActivity.this, "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });*/

        // 启动登录
        LoginActivity.start(this);
        finish();
    }

    private void initView() {
        mBottomTabHome = findView(R.id.bottom_tab_home);
        mBottomTabCategory = findView(R.id.bottom_tab_category);
        mBottomTabLatestLottery = findView(R.id.bottom_tab_latest_lottery);
        mBottomTabShoppingCar = findView(R.id.bottom_tab_shopping_car);
        mBottomTabMe = findView(R.id.bottom_tab_me);

        mIvBottomTabHome = findView(R.id.iv_bottom_tab_home);
        mIvBottomTabCategory = findView(R.id.iv_bottom_tab_category);
        mIvBottomTabLatestLottery = findView(R.id.iv_bottom_tab_latest_lottery);
        mIvBottomTabShoppingCar = findView(R.id.iv_bottom_tab_shopping_car);
        mIvBottomTabMe = findView(R.id.iv_bottom_tab_me);

        mTvBottomTabHome = findView(R.id.tv_bottom_tab_home);
        mTvBottomTabCategory = findView(R.id.tv_bottom_tab_category);
        mTvBottomTabLatestLottery = findView(R.id.tv_bottom_tab_latest_lottery);
        mTvBottomTabShoppingCar = findView(R.id.tv_bottom_tab_shopping_car);
        mTvBottomTabMe = findView(R.id.tv_bottom_tab_me);

        showHomeFragment();
    }

    private void initListener(){
        mBottomTabHome.setOnClickListener(this);
        mBottomTabCategory.setOnClickListener(this);
        mBottomTabLatestLottery.setOnClickListener(this);
        mBottomTabShoppingCar.setOnClickListener(this);
        mBottomTabMe.setOnClickListener(this);
    }

    private void showHomeFragment(){
        if(currentFragment == homeFragment && currentFragment != null) return;
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(currentFragment != null) ft.hide(currentFragment);
        if(homeFragment == null){
            homeFragment = (TFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());

        }
        if(homeFragment == null){
            homeFragment = new HomeFragment();
            ft.add(homeFragment.getContainerId(),homeFragment,HomeFragment.class.getSimpleName());
        }else {
            ft.show(homeFragment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = homeFragment;
        updateBottomSheet(1);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.home_label;
        options.isNeedNavigate = false;
        setToolBar(R.id.toolbar, options);
    }

    private void showCategoryFragment(){
        if(currentFragment == categoryFrgment && currentFragment != null) return;
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(currentFragment != null) ft.hide(currentFragment);
        if(categoryFrgment == null){
            categoryFrgment = (TFragment) getSupportFragmentManager().findFragmentByTag(CategoryFragment.class.getSimpleName());

        }
        if(categoryFrgment == null){
            categoryFrgment = new CategoryFragment();
            ft.add(categoryFrgment.getContainerId(),categoryFrgment,CategoryFragment.class.getSimpleName());
        }else {
            ft.show(categoryFrgment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = categoryFrgment;
        updateBottomSheet(2);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.category_label;
        options.isNeedNavigate = false;
        setToolBar(R.id.toolbar, options);
    }

    private void showLatestLotteryFragment(){
        if(currentFragment == latestLotteryFragment && currentFragment != null) return;
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(currentFragment != null) ft.hide(currentFragment);
        if(latestLotteryFragment == null){
            latestLotteryFragment = (TFragment) getSupportFragmentManager().findFragmentByTag(LatestLotteryFragment.class.getSimpleName());

        }
        if(latestLotteryFragment == null){
            latestLotteryFragment = new LatestLotteryFragment();
            ft.add(latestLotteryFragment.getContainerId(),latestLotteryFragment,LatestLotteryFragment.class.getSimpleName());
        }else {
            ft.show(latestLotteryFragment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = latestLotteryFragment;
        updateBottomSheet(3);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.latest_lottery_label;
        options.isNeedNavigate = false;
        setToolBar(R.id.toolbar, options);
    }

    private void showShoppingCarFragment(){
        if(!AccountUtil.isLogin()){
            startActivity(new Intent(this,LoginActivity.class));
            return;
        }
        if(currentFragment == shoppingCarFragment && currentFragment != null) return;
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(currentFragment != null) ft.hide(currentFragment);
        if(shoppingCarFragment == null){
            shoppingCarFragment = (TFragment) getSupportFragmentManager().findFragmentByTag(ShoppingCarFragment.class.getSimpleName());

        }
        if(shoppingCarFragment == null){
            shoppingCarFragment = new ShoppingCarFragment();
            ft.add(shoppingCarFragment.getContainerId(),shoppingCarFragment,ShoppingCarFragment.class.getSimpleName());
        }else {
            ft.show(shoppingCarFragment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = shoppingCarFragment;
        updateBottomSheet(4);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.shopping_car_label;
        options.isNeedNavigate = false;
        setToolBar(R.id.toolbar, options);
    }

    private void showMyCenterFragment(){
        if(currentFragment == myCenterFragment && currentFragment != null) return;
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(currentFragment != null) ft.hide(currentFragment);
        if(myCenterFragment == null) {
            myCenterFragment = (TFragment) getSupportFragmentManager().findFragmentByTag(MyCenterFragment.class.getSimpleName());

        }
        if(myCenterFragment == null){
            myCenterFragment= new MyCenterFragment();
            ft.add(myCenterFragment.getContainerId(),myCenterFragment,MyCenterFragment.class.getSimpleName());
        }else {
            ft.show(myCenterFragment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = myCenterFragment;
        updateBottomSheet(5);

//        ToolBarOptions options = new ToolBarOptions();
//        options.titleId = R.string.wode;
//        options.isNeedNavigate = false;
//        setToolBar(R.id.toolbar, options);
        findView(R.id.toolbar).setVisibility(View.GONE);
    }

    private void updateBottomSheet(int index){
        mIvBottomTabHome.setImageResource(R.drawable.ic_home_tab_normal);
        mTvBottomTabHome.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        mIvBottomTabCategory.setImageResource(R.drawable.ic_category_tab_normal);
        mTvBottomTabCategory.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        mIvBottomTabLatestLottery.setImageResource(R.drawable. ic_latest_lottery_tab_normal);
        mTvBottomTabLatestLottery.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        mIvBottomTabShoppingCar.setImageResource(R.drawable.ic_shopping_car_tab_normal);
        mTvBottomTabShoppingCar.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        mIvBottomTabMe.setImageResource(R.drawable.ic_personal_center_normal);
        mTvBottomTabMe.setTextColor(getResources().getColor(R.color.bottom_tab_normal));

        switch (index){
            case 1:
                //home
                mIvBottomTabHome.setImageResource(R.drawable.ic_home_tab_selected);
                mTvBottomTabHome.setTextColor(getResources().getColor(R.color.bottom_tab_selected));
                break;
            case 2:
                //category
                mIvBottomTabCategory.setImageResource(R.drawable.ic_category_tab_selected);
                mTvBottomTabCategory.setTextColor(getResources().getColor(R.color.bottom_tab_selected));
                break;
            case 3:
                //latest lottery
                mIvBottomTabLatestLottery.setImageResource(R.drawable. ic_latest_lottery_tab_selected);
                mTvBottomTabLatestLottery.setTextColor(getResources().getColor(R.color.bottom_tab_selected));
                break;
            case 4:
                //shopping car
                mIvBottomTabShoppingCar.setImageResource(R.drawable.ic_shopping_car_tab_selected);
                mTvBottomTabShoppingCar.setTextColor(getResources().getColor(R.color.bottom_tab_selected));
                break;
            case 5:
                //me
                mIvBottomTabMe.setImageResource(R.drawable.ic_personal_center_selected);
                mTvBottomTabMe.setTextColor(getResources().getColor(R.color.bottom_tab_selected));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bottom_tab_home:
                showHomeFragment();
                break;
            case R.id.bottom_tab_category:
                showCategoryFragment();
                break;
            case R.id.bottom_tab_latest_lottery:
                showLatestLotteryFragment();
                break;
            case R.id.bottom_tab_shopping_car:
                showShoppingCarFragment();
                break;
            case R.id.bottom_tab_me:
                showMyCenterFragment();
                break;
        }
    }

    private void getVersionInfo(){

    }

    /**
     * 基本权限管理
     */
    private void requestBasicPermission() {
        MPermission.with(MainActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess(){
        //Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed(){
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }
}
