package com.eum.ssrgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseExpandableAdapter extends BaseExpandableListAdapter{
	
	private ArrayList<String> groupList = null;
	private ArrayList<ArrayList<String>> childList = null;
	private LayoutInflater inflater = null;
	private ViewHolder viewHolder = null;
	
	public BaseExpandableAdapter(Context c, ArrayList<String> groupList,
			ArrayList<ArrayList<String>> childList){
		super();
		this.inflater = LayoutInflater.from(c);
		this.groupList = (ArrayList<String>) groupList;
		this.childList = childList;
	}
	
	// 그룹 포지션을 반환한다.
	@Override
	public String getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	// 그룹 사이즈를 반환한다.
	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	// 그룹 ID를 반환한다.
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 그룹뷰 각각의 ROW 
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.riding_list,parent,false);
		/*	v = inflater.inflate(R.layout.list_row, parent, false);
			viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);*/

			//그룹뷰 체크 이미지, 넘버 객체생성
/*			viewHolder.iv_image = (ImageView) v.findViewById(R.id.iv_image);*/
			viewHolder.num = (TextView) v.findViewById(R.id.riding_list_num);

			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		
		// 그룹을 펼칠때와 닫을때 아이콘을 변경해 준다.
/*		if(isExpanded){
			viewHolder.iv_image.setBackgroundColor(Color.GREEN);
		}else{
			viewHolder.iv_image.setBackgroundColor(Color.WHITE);
		}*/

		viewHolder.num.setText(getGroup(groupPosition));
		
		return v;
	}
	
	// 차일드뷰를 반환한다.
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}
	
	// 차일드뷰 사이즈를 반환한다.
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition).size();
	}

	// 차일드뷰 ID를 반환한다.
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// 차일드뷰 각각의 ROW
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.riding_list, null);

			viewHolder.riding_list_row = (LinearLayout) v.findViewById(R.id.riding_list_row);
			viewHolder.riding_date = (TextView) v.findViewById(R.id.riding_list_date);

			viewHolder.section = (LinearLayout) v.findViewById(R.id.section_layout);

			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		
		viewHolder.riding_date.setText(getChild(groupPosition, childPosition));
		
		return v;
	}

	@Override
	public boolean hasStableIds() {	return true; }

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }



	class ViewHolder {
/*		public ImageView iv_image;*/

		public TextView num;
		public LinearLayout riding_list_row;
		public TextView riding_date;
		public LinearLayout section;

	}

}




