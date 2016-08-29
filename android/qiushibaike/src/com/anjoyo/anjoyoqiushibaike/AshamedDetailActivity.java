package com.anjoyo.anjoyoqiushibaike;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.adapter.DetailListAdapter;
import com.anjoyo.info.AshamedInfo;
import com.anjoyo.info.CommentsInfo;
import com.anjoyo.model.Model;
import com.anjoyo.myview.MyDetailsListView;
import com.anjoyo.net.ThreadPoolUtils;
import com.anjoyo.thread.HttpGetThread;
import com.anjoyo.utils.LoadImg;
import com.anjoyo.utils.LoadImg.ImageDownloadCallBack;
import com.anjoyo.utils.MyJson;
//www.javaapk.com
public class AshamedDetailActivity extends Activity {

	private AshamedInfo info = null;
	private LoadImg loadImg;
	private MyJson myJson = new MyJson();
	private ImageView Detail_Back, Detail_SenComment;
	private TextView Detail_AshameID;
	private ImageView Detail_UserHead;
	private TextView Detail_UserName;
	private TextView Detail_MainText;
	private ImageView Detail_MainImg;
	private LinearLayout Detail_Up;
	private ImageView Detail_Up_Img;
	private TextView Detail_Up_text;
	private LinearLayout Detail_Down;
	private ImageView Detail_Down_Img;
	private TextView Detail_Down_text;
	private TextView Detail_ShareNum;
	private LinearLayout Detail_Share;
	private ImageView Detail_Share_Img;
	private MyDetailsListView Detail_List;
	private LinearLayout Detail__progressBar;
	private TextView Detail_CommentsNum;
	private int commentsFlag = 0;
	private boolean upFlag = false;
	private boolean downFlag = false;
	private List<CommentsInfo> list = new ArrayList<CommentsInfo>();
	private DetailListAdapter mAdapter = null;
	private Button ListBottem = null;
	private int mStart = 0;
	private int mEnd = 5;
	private String url = null;
	private boolean flag = true;
	private boolean listBottemFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ashamed_detail);
		Intent intent = getIntent();
		Bundle bund = intent.getBundleExtra("value");
		info = (AshamedInfo) bund.getSerializable("AshamedInfo");
		loadImg = new LoadImg(AshamedDetailActivity.this);
		initView();
		addInformation();
		addImg();

		mAdapter = new DetailListAdapter(AshamedDetailActivity.this, list);
		ListBottem = new Button(AshamedDetailActivity.this);
		ListBottem.setText("点击加载更多");
		ListBottem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag && listBottemFlag) {
					url = Model.COMMENTS + "qid=" + info.getQid() + "&start="
							+ mStart + "&end=" + mEnd;
					ThreadPoolUtils.execute(new HttpGetThread(hand, url));
					listBottemFlag = false;
				} else if (!listBottemFlag)
					Toast.makeText(AshamedDetailActivity.this, "正在加载中...", 1)
							.show();
			}
		});
		Detail_List.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		Detail_List.setAdapter(mAdapter);
		String endParames = Model.COMMENTS + "qid=" + info.getQid() + "&start="
				+ mStart + "&end=" + mEnd;
		ThreadPoolUtils.execute(new HttpGetThread(hand, endParames));
	}

	private void initView() {
		MyOnClickListner myOnclick = new MyOnClickListner();
		Detail_Back = (ImageView) findViewById(R.id.Detail_Back);
		Detail_SenComment = (ImageView) findViewById(R.id.Detail_SenComment);
		Detail_AshameID = (TextView) findViewById(R.id.Detail_AshameID);
		Detail_UserHead = (ImageView) findViewById(R.id.Detail_UserHead);
		Detail_UserName = (TextView) findViewById(R.id.Detail_UserName);
		Detail_MainText = (TextView) findViewById(R.id.Detail_MainText);
		Detail_MainImg = (ImageView) findViewById(R.id.Detail_MainImg);
		Detail_Up = (LinearLayout) findViewById(R.id.Detail_Up);
		Detail_Up_Img = (ImageView) findViewById(R.id.Detail_Up_Img);
		Detail_Up_text = (TextView) findViewById(R.id.Detail_Up_text);
		Detail_Down = (LinearLayout) findViewById(R.id.Detail_Down);
		Detail_Down_Img = (ImageView) findViewById(R.id.Detail_Down_Img);
		Detail_Down_text = (TextView) findViewById(R.id.Detail_Down_text);
		Detail_ShareNum = (TextView) findViewById(R.id.Detail_ShareNum);
		Detail_Share = (LinearLayout) findViewById(R.id.Detail_Share);
		Detail_Share_Img = (ImageView) findViewById(R.id.Detail_Share_Img);
		Detail_List = (MyDetailsListView) findViewById(R.id.Detail_List);
		Detail__progressBar = (LinearLayout) findViewById(R.id.Detail__progressBar);
		Detail_CommentsNum = (TextView) findViewById(R.id.Detail_CommentsNum);
		Detail_Back.setOnClickListener(myOnclick);
		Detail_SenComment.setOnClickListener(myOnclick);
		Detail_UserHead.setOnClickListener(myOnclick);
		Detail_MainImg.setOnClickListener(myOnclick);
		Detail_Up.setOnClickListener(myOnclick);
		Detail_Down.setOnClickListener(myOnclick);
		Detail_Share.setOnClickListener(myOnclick);
	}

	// ����¼��ļ�����Ӧ
	private class MyOnClickListner implements View.OnClickListener {
		public void onClick(View arg0) {
			int mID = arg0.getId();
			switch (mID) {
			case R.id.Detail_Back:
				AshamedDetailActivity.this.finish();
				break;
			case R.id.Detail_SenComment:
				if (Model.MYUSERINFO != null) {
					Intent intent = new Intent(AshamedDetailActivity.this,
							SendCommentActivity.class);
					Bundle bund = new Bundle();
					bund.putSerializable("AshamedInfo", info);
					intent.putExtra("value", bund);
					startActivity(intent);
				} else {
					Intent intent = new Intent(AshamedDetailActivity.this,
							LoginActivity.class);
					startActivity(intent);
				}

				break;
			case R.id.Detail_UserHead:
				Toast.makeText(AshamedDetailActivity.this, "点击发送小纸条", 1).show();
				break;
			case R.id.Detail_MainImg:
				Toast.makeText(AshamedDetailActivity.this, "点击查看大图", 1).show();
				break;
			case R.id.Detail_Up:
				downFlag = false;
				upFlag = true;
				upOrDown();
				break;
			case R.id.Detail_Down:
				upFlag = false;
				downFlag = true;
				upOrDown();
				break;
			case R.id.Detail_Share:
				info.setQshare(String.valueOf(Integer.parseInt(info.getQshare()) + 1));
				Detail_ShareNum.setText(String.valueOf(info.getQshare()));
				Toast.makeText(AshamedDetailActivity.this, "分享被点击", 1).show();
				break;
			default:
				break;
			}
		}

		private void upOrDown() {
			String upNum = info.getQlike();
			String downNum = info.getQunlike();
			int num1 = Integer.parseInt(upNum);
			int num2 = Integer.parseInt(downNum);
			Detail_Up.setBackgroundResource(R.drawable.button_vote_enable);
			Detail_Up_Img.setImageResource(R.drawable.icon_for_enable);
			Detail_Up_text.setTextColor(Color.parseColor("#815F3D"));
			Detail_Down.setBackgroundResource(R.drawable.button_vote_enable);
			Detail_Down_Img.setImageResource(R.drawable.icon_against_enable);
			Detail_Down_text.setTextColor(Color.parseColor("#815F3D"));

			if (upFlag) {
				Detail_Up.setBackgroundResource(R.drawable.button_vote_active);
				Detail_Up_Img.setImageResource(R.drawable.icon_for_active);
				Detail_Up_text.setTextColor(Color.RED);
				if (commentsFlag == 0) {
					upNum = String.valueOf(num1 + 1);
					commentsFlag = 1;
				} else if (commentsFlag == 2) {
					upNum = String.valueOf(num1 + 1);
					downNum = String.valueOf(num2 - 1);
					commentsFlag = 1;
				}
			} else if (downFlag) {
				Detail_Down
						.setBackgroundResource(R.drawable.button_vote_active);
				Detail_Down_Img
						.setImageResource(R.drawable.icon_against_active);
				Detail_Down_text.setTextColor(Color.RED);
				if (commentsFlag == 0) {
					downNum = String.valueOf(num2 + 1);
					commentsFlag = 2;
				} else if (commentsFlag == 1) {
					downNum = String.valueOf(num2 + 1);
					upNum = String.valueOf(num1 - 1);
					commentsFlag = 2;
				}
			}
			info.setQlike(upNum);
			info.setQunlike(downNum);
			Detail_Up_text.setText(upNum);
			if (!downNum.endsWith("0")) {
				Detail_Down_text.setText("-" + downNum);
			} else {
				Detail_Down_text.setText(downNum);
			}
		}
	}

	@SuppressLint("HandlerLeak")
	Handler hand = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(AshamedDetailActivity.this, "请求失败，服务器故障", 1)
						.show();
				listBottemFlag = true;
			} else if (msg.what == 100) {
				Toast.makeText(AshamedDetailActivity.this, "服务器无响应", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if (result != null) {
					List<CommentsInfo> newList = myJson
							.getAhamedCommentsList(result);
					if (newList != null) {
						if (newList.size() == 5) {
							Detail_List.setVisibility(View.VISIBLE);
							ListBottem.setVisibility(View.VISIBLE);
							mStart += 5;
							mEnd += 5;
						} else if (newList.size() == 0) {
							if (list.size() == 0)
								Detail_CommentsNum.setVisibility(View.VISIBLE);
							ListBottem.setVisibility(View.GONE);
							Toast.makeText(AshamedDetailActivity.this,
									"已经没有了...", 1).show();
						} else {
							Detail_List.setVisibility(View.VISIBLE);
							ListBottem.setVisibility(View.GONE);
						}
						for (CommentsInfo info : newList) {
							list.add(info);
						}
						listBottemFlag = true;
					} else {
						Detail_CommentsNum.setVisibility(View.VISIBLE);
					}
				}
				Detail__progressBar.setVisibility(View.GONE);
				mAdapter.notifyDataSetChanged();
			}
		};

	};

	private void addImg() {
		Bitmap bit = null;
		if (info.getUhead().equals("null")) {
			Detail_UserHead.setTag(Model.USERHEADURL + info.getUhead());
			bit = loadImg.loadImage(Detail_UserHead,
					Model.USERHEADURL + info.getUhead(),
					new ImageDownloadCallBack() {
						public void onImageDownload(ImageView imageView,
								Bitmap bitmap) {
							Detail_UserHead.setImageBitmap(bitmap);
						}
					});
			if (bit != null) {
				Detail_UserHead.setImageBitmap(bit);
			}
		} else {
			Detail_UserHead.setImageResource(R.drawable.default_users_avatar);
		}
		if (info.getQimg().equals("null")) {
			Detail_MainImg.setTag(Model.QIMGURL + info.getQimg());
			bit = loadImg.loadImage(Detail_MainImg,
					Model.QIMGURL + info.getQimg(),
					new ImageDownloadCallBack() {
						public void onImageDownload(ImageView imageView,
								Bitmap bitmap) {
							Detail_MainImg.setImageBitmap(bitmap);
						}
					});
			if (bit != null) {
				Detail_MainImg.setImageBitmap(bit);
			}
		} else {
			Detail_MainImg.setVisibility(View.GONE);
		}

	}

	private void addInformation() {
		Detail_AshameID.setText("糗事ID" + info.getQid());
		Detail_UserName.setText(info.getUname());
		Detail_MainText.setText(info.getQvalue());
		Detail_Up_text.setText(info.getQlike());
		Detail_Down_text.setText("-" + info.getQunlike());
		Detail_ShareNum.setText(info.getQshare());
	}
}
