package cn.wc.xiangmu.model;


public interface IModel {
	public interface AsyncCallback {

		void onSuccess(Object success);

		void onError(Object error);

	}

}
