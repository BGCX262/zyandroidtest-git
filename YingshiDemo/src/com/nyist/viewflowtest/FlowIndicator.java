package com.nyist.viewflowtest;

import com.nyist.viewflowtest.ViewFlow.ViewSwitchListener;

public interface FlowIndicator extends ViewSwitchListener {

	/*
	 * ���õ�ǰViewFlow��������������õ�ViewFlow��FlowIndicator����������
	 */
	public void setViewFlow(ViewFlow view);

	/**
	 * 
	 *	����λ���Ѿ����ı��ˡ�һ��FlowIndicator����ʵ���������,�Է�ӳ��ǰ��λ��
	 */
	public void onScrolled(int h, int v, int oldh, int oldv);
}

