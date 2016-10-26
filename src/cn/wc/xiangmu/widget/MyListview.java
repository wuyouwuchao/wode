package cn.wc.xiangmu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import cn.wc.xiangmu.R;
import cn.wc.xiangmu.fragment.ZFragment.MyOnRefreshListener;




/**
 * 上拉刷新ListView
 * 
 * @param <MyOnRefreshListener>
 * 
 * 
 */
public class MyListview extends ListView {

	/** 底部显示正在加载的页面 */
	private View footerView;
	/** 存储上下文 */
	private Context context;

	int currentState;
	final static int STATE_DONE = 1;
	final static int STATE_PULL = 2;
	final static int STATE_RELEASE = 3;
	final static int STATE_REFRESHING = 4;

	int downY;
	private int height;

	private ImageView a1;
	private ImageView a2;
	private ImageView a3;
	
	
	public MyListview(Context context) {
		super(context);
		this.context = context;

	}

	public MyListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		footerView = View.inflate(context,R.layout.footer_review, null);
		Log.v("info", "info----1  ");
		this.addFooterView(footerView);
		Log.v("info", "info----2  ");
		// getHeight()要求控件已经显示出来了
		// height=headerview.getHeight();
		// 控件没有显示出来，用
		// 控件的高度设置成了match_parent,wrap_content,60dp
		// UNSPECIFIED 不指定大小。
		// 测量高度
		footerView.measure(0, 0);
		Log.v("info", "info----3  ");
		// 得到测量后的高度
		height = footerView.getMeasuredHeight();
		

		footerView.setPadding(0, 0, 0, -height);
		
		// 设置初始状态
		currentState = STATE_DONE;

		a1 = (ImageView) footerView.findViewById(R.id.pullup);
		a2 = (ImageView) footerView.findViewById(R.id.refreshing);
		a3 = (ImageView) footerView.findViewById(R.id.succeed);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 判断事件是按下，还是松开
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:// 按下
			if (currentState == STATE_DONE) {
				currentState = STATE_PULL;
				downY = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:// 移动
			if (currentState == STATE_PULL) {
				int currentY = (int) ev.getY();
				
				if (downY - currentY > (height + height * 1)) {
					// 更改状态
					currentState = STATE_RELEASE;
					a1.setVisibility(View.GONE);
					a2.setVisibility(View.VISIBLE);
				}
				int top = downY - currentY - height;
				
				footerView.setPadding(0, 0, 0, top);
				
			}
			break;
		case MotionEvent.ACTION_UP:// 松开
			if (currentState == STATE_RELEASE) {
				currentState = STATE_REFRESHING;
				
					a1.setVisibility(View.GONE);
					a2.setVisibility(View.VISIBLE);

				// 4,通过接口调实现类
				// 用框架的人传实现类
				if (this.onRefreshListener != null) {
					this.onRefreshListener.onRefresh(this);
				}
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	// 1定义接口
	public interface OnRefreshListener {
		void onRefresh(MyListview myListview);
	}

	// 2 申明接口
	MyOnRefreshListener onRefreshListener;

	// 3,接收实现类
	public void setOnRefreshListener(MyOnRefreshListener myOnRefreshListener) {
		this.onRefreshListener = myOnRefreshListener;
	}

	public void completeRefresh() {
			footerView.setPadding(0, 0, 0, -height);
			currentState = STATE_DONE;
			a3.setVisibility(View.VISIBLE);
			a2.setVisibility(View.GONE);
	}

}
