package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.Iistener;
import com.zeroo.lib.Zobject;

/**
 * Created by Zero on 2017/7/21.
 */

public class ExampleFaceshot extends Zobject implements Iistener {
	public ExampleFaceshot() {
		super(Zobject.Zactivity, null);
	}

	@Override
	public int Listen(String event, Zobject speaker, double[] args) {
		Print(event);
		return 0;
	}
}
