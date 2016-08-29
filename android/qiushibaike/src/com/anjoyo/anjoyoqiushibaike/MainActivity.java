package com.anjoyo.anjoyoqiushibaike;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjoyo.fragment.CrossFragment;
import com.anjoyo.fragment.CrossFragment.CrossFragmentCallBack;
import com.anjoyo.fragment.HotFragment;
import com.anjoyo.fragment.HotFragment.HotFragmentCallBack;
import com.anjoyo.fragment.NearFragment;
import com.anjoyo.fragment.NearFragment.NearFragmentCallBack;
import com.anjoyo.fragment.NiceFragment;
import com.anjoyo.fragment.NiceFragment.NiceFragmentCallBack;
import com.anjoyo.fragment.PictureFragment;
import com.anjoyo.fragment.PictureFragment.PictureFragmentCallBack;
import com.anjoyo.model.Model;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {
	//www.javaapk.com
	private View mLeftView;
	// 第三方的抽屉菜单管理工具类
	private SlidingMenu mSlidingMenu;
	// 热门的碎片
	private HotFragment mHotFragment;
	// 精华的碎片
	private NiceFragment mNiceFragment;
	// 有图有真相的碎片
	private PictureFragment mPictureFragment;
	// 穿越的碎片
	private CrossFragment mCrossFragment;
	// 附近的碎片
	private NearFragment mNearFragment;
	// 定义fragment管理器:向界面增加修改删除fragment
	private FragmentManager mFragmentManager;
	// 获取fragment栈
	private FragmentTransaction mFragmentTransaction;
	private List<Fragment> myFragmentList = new ArrayList<Fragment>();
	// leftView里面的控件
	private LinearLayout mLoginThisAPP;// 登录糗百按钮
	private TextView myUserName;
	private ImageView mSettingBtn;// 设置按钮
	// leftview中下面的按钮
	private RelativeLayout mLeftHot, mLeftNice, mLeftPicture, mLeftCross,
			mLeftScrip, mLeftNear, mLeftCheck; // leftview中下面的按钮
	private int fragmentFlag = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mLeftView = View.inflate(MainActivity.this, R.layout.leftview, null);
		mLoginThisAPP = (LinearLayout) mLeftView
				.findViewById(R.id.LoginThisAPP);
		mSettingBtn = (ImageView) mLeftView.findViewById(R.id.SettingBtn);
		mLeftHot = (RelativeLayout) mLeftView.findViewById(R.id.LeftHot);
		mLeftNice = (RelativeLayout) mLeftView.findViewById(R.id.LeftNice);
		mLeftPicture = (RelativeLayout) mLeftView
				.findViewById(R.id.LeftPicture);
		mLeftScrip = (RelativeLayout) mLeftView.findViewById(R.id.LeftScrip);
		mLeftNear = (RelativeLayout) mLeftView.findViewById(R.id.LeftNear);
		mLeftCross = (RelativeLayout) mLeftView.findViewById(R.id.LeftCross);
		mLeftCheck = (RelativeLayout) mLeftView.findViewById(R.id.LeftCheck);
		myUserName = (TextView) mLeftView.findViewById(R.id.myUserName);

		mLoginThisAPP.setOnClickListener(MainActivity.this);
		mSettingBtn.setOnClickListener(MainActivity.this);
		mLeftHot.setOnClickListener(MainActivity.this);
		mLeftNice.setOnClickListener(MainActivity.this);
		mLeftPicture.setOnClickListener(MainActivity.this);
		mLeftScrip.setOnClickListener(MainActivity.this);
		mLeftNear.setOnClickListener(MainActivity.this);
		mLeftCross.setOnClickListener(MainActivity.this);
		mLeftCheck.setOnClickListener(MainActivity.this);
		mLeftHot.setBackgroundResource(R.drawable.side_menu_background_active);
		mHotFragment = new HotFragment();
		myFragmentList.add(mHotFragment);
		mNiceFragment = new NiceFragment();
		myFragmentList.add(mNiceFragment);
		mPictureFragment = new PictureFragment();
		myFragmentList.add(mPictureFragment);
		mCrossFragment = new CrossFragment();
		myFragmentList.add(mCrossFragment);
		mNearFragment = new NearFragment();
		myFragmentList.add(mNearFragment);
		mSlidingMenu = this.getSlidingMenu();
		this.setBehindContentView(mLeftView);
		mSlidingMenu.setShadowDrawable(R.drawable.drawer_shadow);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mFragmentManager = MainActivity.this.getSupportFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		createFargment(1);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		mFragmentTransaction.replace(R.id.main, mHotFragment);
		mFragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {
		int mID = v.getId();
		// MainActivity.this.toggle();
		switch (mID) {
		case R.id.LoginThisAPP:
			if (Model.MYUSERINFO != null) {
				Intent intent = new Intent(MainActivity.this,
						UserInfoActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.SettingBtn:
			break;
		case R.id.LeftHot:
			createleftviewbg();
			mLeftHot.setBackgroundResource(R.drawable.side_menu_background_active);
			createFargment(1);
			break;
		case R.id.LeftNice:
			createleftviewbg();
			mLeftNice
					.setBackgroundResource(R.drawable.side_menu_background_active);
			createFargment(2);
			break;
		case R.id.LeftPicture:
			createleftviewbg();
			mLeftPicture
					.setBackgroundResource(R.drawable.side_menu_background_active);
			createFargment(3);
			break;
		case R.id.LeftCross:
			createleftviewbg();
			mLeftCross
					.setBackgroundResource(R.drawable.side_menu_background_active);
			createFargment(4);
			break;
		case R.id.LeftNear:
			createleftviewbg();
			mLeftNear
					.setBackgroundResource(R.drawable.side_menu_background_active);
			createFargment(5);
			break;
		case R.id.LeftScrip:
			createleftviewbg();
			mLeftScrip
					.setBackgroundResource(R.drawable.side_menu_background_active);
			// createFargment(6);
			break;
		case R.id.LeftCheck:
			createleftviewbg();
			mLeftCheck
					.setBackgroundResource(R.drawable.side_menu_background_active);
			// 这里是跳转到审核糗事的activity中
			Intent intent1 = new Intent(MainActivity.this, AuditActivity.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
	}

	// 设置右边的fragment加载的控件
	private void createFargment(int flag) {
		// 如果正在加载的fragment是传过来的 那么就不操作否则就去加载
		MainActivity.this.toggle();
		if (fragmentFlag != flag) {
			switch (flag) {
			case 1:// 热门的Fragment
				hotFragmentCallBack();
				break;
			case 2:// 精华的Fragment
				niceFragmentCallBack();
				break;
			case 3:// 有图有真相的Fragment
				pictureFragmentCallBack();
				break;
			case 4:// 穿越的Fragment
				crossFragmentCallBack();
				break;
			case 5:// 小纸条的Fragment
				nearfragmentCallBack();
				break;
			case 6:// 附近的Fragment
				break;
			}
			if (fragmentFlag != 0) {
				mFragmentTransaction.remove(myFragmentList
						.get(fragmentFlag - 1));
			}
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.main,
					myFragmentList.get(flag - 1));
			// 提交保存杠杆替换或者添加的fragment
			mFragmentTransaction.commit();
			fragmentFlag = flag;
		}
	}

	// 从picturefragment里面回调回来的事件监听设置方法
	private void crossFragmentCallBack() {
		mCrossFragment.setCallBack(new MyCrossFragmentCallBack());
	}

	// 从picturefragment里面回调回来的事件监听设置方法
	private void pictureFragmentCallBack() {
		mPictureFragment.setCallBack(new MyPictureFragmentCallBack());
	}

	// 从nicefragment里面回调回来的事件监听设置方法
	private void niceFragmentCallBack() {
		mNiceFragment.setCallBack(new MyNiceFragmentCallBack());
	}

	// 从hotfragment里面回调回来的事件监听设置方法
	private void hotFragmentCallBack() {
		mHotFragment.setCallBack(new MyHotFragmentCallBack());
	}

	// 从nearfragment里面回调回来的事件监听设置方法
	private void nearfragmentCallBack() {
		mNearFragment.setCallBack(new MyNearFragmentCallBack());
	}

	private class MyHotFragmentCallBack implements HotFragmentCallBack {
		@Override
		public void callback(int flag) {
			switch (flag) {
			case R.id.Menu:
				MainActivity.this.toggle();
				break;
			case R.id.SendAshamed:
				if (Model.MYUSERINFO != null) {
					Intent intent = new Intent(MainActivity.this,
							UploadActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	}

	// 实现nicefragment里面接口的子类
	private class MyCrossFragmentCallBack implements CrossFragmentCallBack {
		public void callback(int flag) {
			switch (flag) {
			case R.id.Menu:// 用户点击hotfragment上面的导航按钮
				// 设置开启或关闭抽屉
				MainActivity.this.toggle();
				break;
			case R.id.SendAshamed:
				if (Model.MYUSERINFO != null) {
					Intent intent = new Intent(MainActivity.this,
							UploadActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	}

	// 实现nicefragment里面接口的子类
	private class MyPictureFragmentCallBack implements PictureFragmentCallBack {
		public void callback(int flag) {
			switch (flag) {
			case R.id.Menu:// 用户点击hotfragment上面的导航按钮
				// 设置开启或关闭抽屉
				MainActivity.this.toggle();
				break;
			case R.id.SendAshamed:
				if (Model.MYUSERINFO != null) {
					Intent intent = new Intent(MainActivity.this,
							UploadActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	}

	// 实现nicefragment里面接口的子类
	private class MyNiceFragmentCallBack implements NiceFragmentCallBack {
		public void callback(int flag) {
			switch (flag) {
			case R.id.Menu:// 用户点击hotfragment上面的导航按钮
				// 设置开启或关闭抽屉
				MainActivity.this.toggle();
				break;
			case R.id.SendAshamed:
				if (Model.MYUSERINFO != null) {
					Intent intent = new Intent(MainActivity.this,
							UploadActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	}

	private class MyNearFragmentCallBack implements NearFragmentCallBack {
		@Override
		public void callback(int flag) {
			switch (flag) {
			case R.id.Near_Back:
				MainActivity.this.toggle();
				break;
			case R.id.Near_Seting:
				break;
			case R.id.Near_More:
				break;
			default:
				break;
			}
		}
	}

	// 设置leftview控件的默认背景色
	private void createleftviewbg() {
		mLeftHot.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftNice.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftPicture.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftScrip.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftNear.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftCross.setBackgroundResource(R.drawable.leftview_list_bg);
		mLeftCheck.setBackgroundResource(R.drawable.leftview_list_bg);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (Model.MYUSERINFO != null) {
			myUserName.setText(Model.MYUSERINFO.getUname());
		}
		// createFargment(fragmentFlag);
	}

	@Override
	protected void onStart() {
		if (Model.MYUSERINFO != null) {
			myUserName.setText(Model.MYUSERINFO.getUname());
		}
		super.onStart();
	}

}
