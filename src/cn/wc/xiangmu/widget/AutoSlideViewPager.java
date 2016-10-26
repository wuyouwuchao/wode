package cn.wc.xiangmu.widget;


import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import cn.wc.xiangmu.adapter.SlidePageAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
/**
 * 
 * @author MaoJiqing
 *
 */
public class AutoSlideViewPager extends ViewPager {
	private Handler handler; // ���currentItem
	public static final int SLIDE_MSG = 0x0000;// �û�����
	public static final int AUTO_SLIDE_MSG = 0x0001;// �Զ�����
	public static final int SLIDE_DELAY = 1000; // handler��ʱ
	public static final int AUTO_SLIDE_DELAY = 3000; // handler��ʱ
	private static boolean touchStopAutoSlide = false;// ���ƻ���ʱֹͣ�Զ�����
	private int adapterCount;// ͼƬ����
	private boolean isClick = false;// �жϵ���¼�
	private SlideDurationScroller scroller = null; // �����������ʱ��
	private static long SLIDE_DURATION = 3; // �����������ʱ��
	private OnPageSlideSelected onPageSlideSelected;
	private static int duration;// �Զ�������ʱ Ĭ��3��

	public interface OnPageSlideSelected {
		public void onSlideSelected(int position);

		public void onClickSelected(int position);
	}

	public AutoSlideViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		setViewPagerScroller();
	}

	public AutoSlideViewPager(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		// TODO Auto-generated constructor stub
		init();
		setViewPagerScroller();
	}

	private void setViewPagerScroller() {
		try {
			Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
			scrollerField.setAccessible(true);
			Field interpolatorField = ViewPager.class
					.getDeclaredField("sInterpolator");
			interpolatorField.setAccessible(true);
			scroller = new SlideDurationScroller(getContext(),
					(Interpolator) interpolatorField.get(null));
			scroller.setSlideDurationFactor(SLIDE_DURATION);
			scrollerField.set(this, scroller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ĭ��3�� �����Զ�������ʱʱ��
	 * 
	 * @param duration
	 */
	@SuppressWarnings("static-access")
	public void setAutoSlideDuration(int duration) {
		this.duration = duration;
	}
	
    	
	
	@Override
	public void setAdapter(PagerAdapter arg0) {
		// TODO Auto-generated method stub
		super.setAdapter(arg0);
		PagerAdapter adapter = getAdapter();
		adapterCount = adapter == null ? 0 : adapter.getCount();
		if (adapterCount > 1) {
			setCurrentItem(1, false);
		}
		startAutoSlide(duration);
	}
	
		

	public void setOnPageSlideSelected(OnPageSlideSelected onPageSlideSelected) {
		this.onPageSlideSelected = onPageSlideSelected;
	}

	private void init() {
		duration = AUTO_SLIDE_DELAY;
		handler = new MyHandler(this);// ��ʼ��handler
		this.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (onPageSlideSelected != null) {
					onPageSlideSelected.onSlideSelected(arg0);
				}
			}

		});
	}

	private float firstX = 0;// ����ʱx����
	private float slideX = 0;// ����ʱx����

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		slideX = ev.getX();
		final int currentItem = getCurrentItem();
		int action = MotionEventCompat.getActionMasked(ev);
		if (action == MotionEvent.ACTION_DOWN) {
			firstX = slideX;
			touchStopAutoSlide = true;
			isClick = true;
		} else if (action == MotionEvent.ACTION_UP) {
			startSlide((SLIDE_DURATION / 2) * SLIDE_DELAY);
			touchStopAutoSlide = false;
			startAutoSlide(duration);
			if (isClick) {
				if (onPageSlideSelected != null) {
					int position;
					if (currentItem == 0) {
						position = adapterCount - 2;
					} else if (currentItem == adapterCount - 1) {
						position = 1;
					} else {
						position = currentItem;
					}
					onPageSlideSelected.onClickSelected(position);
				}
			}
		}
		if (Math.abs(firstX - slideX) >= 5) {
			isClick = false;
		}
		if (adapterCount > 1) {
			if ((currentItem == 0 && firstX < slideX)
					|| (currentItem == adapterCount - 1 && firstX > slideX)) {
				startSlideNow();
			}
		}
		
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * �û�������β����
	 */
	private void slideChange() {
		int currentItem = getCurrentItem();
		if (currentItem == 0) {
			setCurrentItem(adapterCount - 2, false);
		} else if (currentItem == adapterCount - 1) {
			setCurrentItem(1, false);
		}
	}

	/**
	 * �Զ�������β����
	 */
	private void autoSlideChange() {
		int currentItem = getCurrentItem();
		if (currentItem == 0) {
			setCurrentItem(adapterCount - 2, false);
		} else if (currentItem == adapterCount - 1) {
			setCurrentItem(1, false);
		} else {
			int item = currentItem++ >= adapterCount - 2 ? 1 : currentItem;
			setCurrentItem(item, true);
		}
	}

	/**
	 * �������ƻ�������
	 * 
	 * @param delay
	 */
	private void startSlide(long delay) {
		sendSlideMessage(delay);
	}

	/**
	 * �����Զ���������
	 * 
	 * @param delay
	 */
	private void startAutoSlide(long delay) {
		sendAutoSlideMessage(delay);
	}

	/**
	 * ����ʱ���Ͽ����û���������
	 */
	private void startSlideNow() {
		handler.removeMessages(SLIDE_MSG);
		handler.sendEmptyMessage(SLIDE_MSG);
	}

	private void sendSlideMessage(long delayTimeInMills) {
		handler.removeMessages(SLIDE_MSG);
		handler.sendEmptyMessageDelayed(SLIDE_MSG, delayTimeInMills);
	}

	private void sendAutoSlideMessage(long delayTimeInMills) {
		handler.removeMessages(AUTO_SLIDE_MSG);
		handler.sendEmptyMessageDelayed(AUTO_SLIDE_MSG, delayTimeInMills);
	}

	private static class MyHandler extends Handler {

		private final WeakReference<AutoSlideViewPager> autoSlideViewPager;

		public MyHandler(AutoSlideViewPager autoSlideViewPager) {
			this.autoSlideViewPager = new WeakReference<AutoSlideViewPager>(
					autoSlideViewPager);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			AutoSlideViewPager pager = this.autoSlideViewPager.get();
			switch (msg.what) {
			case SLIDE_MSG:
				if (pager != null) {
					pager.slideChange();
					pager.scroller.setSlideDurationFactor(SLIDE_DURATION);
				}
				break;
			case AUTO_SLIDE_MSG:
				if (pager != null) {
					if (!touchStopAutoSlide) {
						pager.autoSlideChange();
						pager.startAutoSlide(duration);
						pager.scroller.setSlideDurationFactor(SLIDE_DURATION);
					}
				}
				break;
			default:
				break;
			}
		}
	}

	
}
