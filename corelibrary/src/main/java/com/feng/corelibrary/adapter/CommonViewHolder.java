package com.feng.corelibrary.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * 通用视图持有类
 * 
 * @author WuRS
 */
public class CommonViewHolder {

	private View convertView;

	private SparseArray<View> mViews; // 每个ViewHolder持有的view集合：<id,view>

	private CommonViewHolder(Context context, int resourceId, View convertView,
			ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(resourceId, parent,
				false);
		convertView.setTag(this);
		this.convertView = convertView;
		mViews = new SparseArray<View>();
	}

	/**
	 * @param context
	 *            上下文对象
	 * @param layoutId
	 *            适配器布局id
	 * @param convertView
	 *            缓存视图
	 * @param parent
	 *            父视图
	 * @return 获取视图持有类
	 */
	public static CommonViewHolder getHolder(Context context, int layoutId,
			View convertView, ViewGroup parent) {
		CommonViewHolder holder = null;
		if (convertView == null) {
			holder = new CommonViewHolder(context, layoutId, convertView,
					parent);
		} else {
			holder = (CommonViewHolder) convertView.getTag();
		}
		return holder;
	}

	/**
	 * @return 获取convertView
	 */
	public View getConvertView() {
		return convertView;
	}

	/**
	 * @param viewId
	 * @return 根据id获取View对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * @param viewId
	 *            控件id
	 * @return 根据viewId获取TextView
	 */
	public TextView getTextView(int viewId) {
		return getView(viewId);
	}

	/**
	 * 找到对应viewId的TextView并设置文本内容
	 * 
	 * @param viewId
	 *            控件id
	 * @param content
	 *            文本内容
	 */
	public void setTextViewContent(int viewId, String content) {
		getTextView(viewId).setText(content);
	}

	/**
	 * @param viewId
	 * @return 根据ivewId获取ImageView
	 */
	public ImageView getImageView(int viewId) {
		return getView(viewId);
	}

	/**
	 * 根据对应的viewId获取ImageView，并设置图片内容
	 * 
	 * @param viewId
	 *            控件Id
	 * @param resId
	 *            图片资源Id
	 */
	public void setImageSrc(int viewId, int resId) {
		getImageView(viewId).setImageResource(resId);
	}

	/**
	 * 根据对应的viewId获取ImageView，并设置图片内容
	 * 
	 * @param viewId
	 *            控件Id
	 * @param imgFile
	 *            图片文件
	 */
	public void setImageSrc(int viewId, File imgFile) {
		getImageView(viewId).setImageURI(Uri.fromFile(imgFile));
	}

}
