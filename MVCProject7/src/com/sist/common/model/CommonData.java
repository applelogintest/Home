package com.sist.common.model;

import javax.servlet.http.HttpServletRequest;

public class CommonData {
	public static void commonData(HttpServletRequest request)
	{
		request.setAttribute("side", "사이드 데이터");
	}
}
