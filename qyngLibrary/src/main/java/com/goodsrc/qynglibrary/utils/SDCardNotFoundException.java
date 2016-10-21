package com.goodsrc.qynglibrary.utils;

public class SDCardNotFoundException extends SystemException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 813780173383242143L;

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public String getMessage() {

		return "SDCardNotFound!";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
