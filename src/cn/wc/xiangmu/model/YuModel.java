package cn.wc.xiangmu.model;

import java.util.List;

import cn.wc.xiangmu.entity.Movieb;
import cn.wc.xiangmu.entity.Yugaopian;

public interface YuModel extends IModel{
	public List<Yugaopian> LoadYulist(String url,AsyncCallback callback);
	public List<Movieb> LoadHotlist(AsyncCallback callback);
}
