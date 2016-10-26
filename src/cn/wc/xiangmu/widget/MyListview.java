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
 * ����ˢ��ListView
 * 
 * @param <MyOnRefreshListener>
 * 
 * 
 */
public class MyListview extends ListView {

	/** �ײ���ʾ���ڼ��ص�ҳ�� */
	private View footerView;
	/** �洢������ */
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
		// getHeight()Ҫ��ؼ��Ѿ���ʾ������
		// height=headerview.getHeight();
		// �ؼ�û����ʾ��������
		// �ؼ��ĸ߶����ó���match_parent,wrap_content,60dp
		// UNSPECIFIED ��ָ����С��
		// �����߶�
		footerView.measure(0, 0);
		Log.v("info", "info----3  ");
		// �õ�������ĸ߶�
		height = footerView.getMeasuredHeight();
		

		footerView.setPadding(0, 0, 0, -height);
		
		// ���ó�ʼ״̬
		currentState = STATE_DONE;

		a1 = (ImageView) footerView.findViewById(R.id.pullup);
		a2 = (ImageView) footerView.findViewById(R.id.refreshing);
		a3 = (ImageView) footerView.findViewById(R.id.succeed);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// �ж��¼��ǰ��£������ɿ�
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:// ����
			if (currentState == STATE_DONE) {
				currentState = STATE_PULL;
				downY = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:// �ƶ�
			if (currentState == STATE_PULL) {
				int currentY = (int) ev.getY();
				
				if (downY - currentY > (height + height * 1)) {
					// ����״̬
					currentState = STATE_RELEASE;
					a1.setVisibility(View.GONE);
					a2.setVisibility(View.VISIBLE);
				}
				int top = downY - currentY - height;
				
				footerView.setPadding(0, 0, 0, top);
				
			}
			break;
		case MotionEvent.ACTION_UP:// �ɿ�
			if (currentState == STATE_RELEASE) {
				currentState = STATE_REFRESHING;
				
					a1.setVisibility(View.GONE);
					a2.setVisibility(View.VISIBLE);

				// 4,ͨ���ӿڵ�ʵ����
				// �ÿ�ܵ��˴�ʵ����
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

	// 1����ӿ�
	public interface OnRefreshListener {
		void onRefresh(MyListview myListview);
	}

	// 2 �����ӿ�
	MyOnRefreshListener onRefreshListener;

	// 3,����ʵ����
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
