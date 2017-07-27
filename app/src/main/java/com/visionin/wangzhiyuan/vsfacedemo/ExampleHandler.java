package com.visionin.wangzhiyuan.vsfacedemo;

import android.media.MediaCodec;
import android.media.MediaFormat;

import com.zeroo.lib.media.Mreators;

import java.nio.ByteBuffer;

/**
 * Created by Zero on 2017/5/18.
 */

public class ExampleHandler extends Mreators {
	boolean opened = false;
	@Override
	public boolean Opened() {
		return opened;
	}
	@Override
	public boolean Open(String fn) {
		/// 如果需要开启操作
		return opened = true;
	}
	@Override
	public String Close() {
		/// 如果需要关闭操作
		opened = false;
		return null;
	}
	@Override
	public int Add(MediaFormat fmt) {
		/// 如果需要处理加入视频流
		return 0;
	}
	@Override
	public int OnFrame(ByteBuffer buffer, MediaCodec.BufferInfo info) {
		/// 自定义的 h264 码流每帧混码、推流
		return 0;
	}
	@Override
	public int OnFrame(byte[] data, int width, int height) {
		/// 自定义的 NV12 帧数据处理
		return 0;
	}
}
