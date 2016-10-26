package cn.wc.xiangmu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.wc.xiangmu.R;

public class PreMoviebListView extends ListView implements OnScrollListener {

	private View footer;

	private boolean isLoading = false;
	private ILoadListener iLoadListener;
	private TextView textView;

	
	
	public PreMoviebListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	footer=inflate(context, R.layout.footer_pre, null);
		textView=(TextView) findViewById(R.id.a_tv);
		this.addFooterView(footer);
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (view.getLastVisiblePosition() == view.getCount() - 1
				&& scrollState == SCROLL_STATE_IDLE) {// �ж��Ƿ񻬵����һ��
			if (!isLoading) {
				footer.findViewById(R.id.a).setVisibility(
						View.VISIBLE);
				textView.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						iLoadListener.onLoad();
					}
				});
			}
		}
	}

	
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (isLoading) {
			footer.findViewById(R.id.a).setVisibility(
					View.VISIBLE);
		}
	}

	/**
	 * �����ݼ������ʱ���ô˷���
	 */
	public void loadComplete() {
		isLoading = false;
		footer.findViewById(R.id.a).setVisibility(View.VISIBLE);
	}

	/**
	 * �������ݼ���ʱ�Ľӿڶ���
	 * 
	 * @param iLoadListener
	 */
	public void setInterface(ILoadListener iLoadListener) {
		this.iLoadListener = iLoadListener;
	}

	// ���ݼ��ؽӿ�
	public interface ILoadListener {
		public void onLoad();
	}
}
