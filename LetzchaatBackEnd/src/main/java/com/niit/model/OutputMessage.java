package com.niit.model;

import java.util.Date;

public class OutputMessage extends Message {
	
	private Date time;
	
	public OutputMessage(Message original,Date time)
	{
		super(original.getId(),original.getMessage(),original.getName(),original.getFriend_name());
		this.time=time;
		
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	

}
