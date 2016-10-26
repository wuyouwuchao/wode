package cn.wc.xiangmu.presenter.impl;

import java.util.List;

import cn.wc.xiangmu.entity.Movieb;
import cn.wc.xiangmu.entity.Yugaopian;
import cn.wc.xiangmu.model.IModel.AsyncCallback;
import cn.wc.xiangmu.model.impl.YuModelImpl;
import cn.wc.xiangmu.presenter.Yupresenter;
import cn.wc.xiangmu.view.HotView;
import cn.wc.xiangmu.view.YuView;

public class YupresenterImpl implements Yupresenter{
	private HotView hotView;
	private YuView yuView;
	private YuModelImpl model;
	
	public YupresenterImpl(HotView hotView, YuView yuView) {
		super();
		this.hotView = hotView;
		this.yuView = yuView;
		model=new YuModelImpl();
	}

	@Override
	public void loadHot() {
		model.LoadHotlist(new AsyncCallback() {
			public void onSuccess(Object success) {
				List<Movieb> moviebs=(List<Movieb>)success;
				hotView.showHotList(moviebs);
			}
			public void onError(Object error) {
			}
		});
	}

	@Override
	public void loadYu(String url) {
		model.LoadYulist(url, new AsyncCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<Yugaopian> yugaopians=(List<Yugaopian>) success;
				yuView.showYulist(yugaopians);
			}
			@Override
			public void onError(Object error) {
				
			}
		});
	}

}
