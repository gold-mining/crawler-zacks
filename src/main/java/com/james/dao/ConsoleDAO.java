package com.james.dao;

import com.james.modal.ZackData;

public class ConsoleDAO {
	public static void output(ZackData data, boolean brief) {
		if (brief) System.out.println(data.ticker); 
		else System.out.println(data);
	}
}
