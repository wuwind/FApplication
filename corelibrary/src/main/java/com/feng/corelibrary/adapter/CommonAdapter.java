package com.feng.corelibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 通用适配器
 * <p>
 * 使用时，需继承该类，指定泛型T所代表的实体类型，实现
 * {@link CommonAdapter#onBindViewHolder(CommonViewHolder, int, T)}
 * 方法，在该方法中，完成给布局中的视图设置数据的操作即可。
 * <p>
 * 
 * @author WuRS
 * @param <T>
 *            要绑定到适配器的数据实体
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	protected Context context;
	protected List<T> data;
	protected T[] dataArr;
	protected int adapterLayoutId;

	public CommonAdapter(Context context, T[] dataArr, int adapterLayoutId) {
		this(context, Arrays.asList(dataArr), adapterLayoutId);
		this.dataArr = dataArr;
	}

	public CommonAdapter(Context context, List<T> data, int adapterLayoutId) {
		super();
		this.context = context;
		this.data = data;
		this.adapterLayoutId = adapterLayoutId;
	}

	@Override
	public void notifyDataSetChanged() {
		// 数组内容发送改变
		if (dataArr != null) {
			data = Arrays.asList(dataArr);
		}
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommonViewHolder holder = CommonViewHolder.getHolder(context,
				adapterLayoutId, convertView, parent);
		onBindViewHolder(holder, position, getItem(position));
		return holder.getConvertView();
	}

	/**
	 * 该方法执行在{@linkplain BaseAdapter#getView(int, View, ViewGroup)} 中；通过参数
	 * holder可以获取视图，设置数据；通过item可以获取当前项的数据实体
	 * 
	 * @param holder
	 *            视图持有者
	 * @param position
	 *            item索引
	 * @param item
	 *            当前项的数据实体
	 */
	public abstract void onBindViewHolder(CommonViewHolder holder, int position, T item);

}
