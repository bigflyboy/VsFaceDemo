package com.visionin.wangzhiyuan.vsfacedemo;

import android.app.Activity;
import android.view.SurfaceView;

import com.zeroo.lib.Zobject;
import com.zeroo.lib.gpu.Greators;
import com.zeroo.lib.gpu.Iraphix;
import com.zeroo.lib.gpu.Iamera;
import com.zeroo.lib.media.Mreators;
import com.zeroo.lib.media.Iapturer;

/**
 * Created by wangzhiyuan on 2017/5/17.
 */

public class ExampleControl{
	protected Iamera camera = null;
	protected Iraphix gpu = null;

	protected Iapturer snapshot = null;
	protected Iapturer capture  = null;
	//protected ExampleHandler handler = null;

	public ExampleControl(Activity activity) {
		Zobject.Zactivity = activity;
		Zobject.Zebug = true;                           /// 输出日志文件，不必要
	}

	public void Init(SurfaceView surface) {
		gpu = Greators.Graphix("G+");                   /// 首先创建 gpu 对象
		camera = Greators.Camera();                     /// 再创建相机对象

		camera.Mode("facer");                           /// 图像美化+人脸检测
		camera.Preview(surface);                        /// 必须设置预览窗口
		gpu.Display(true);                              /// 开启预览

		snapshot = Mreators.Snapshoter("snapshot.jpg"); /// 创建截图器
		snapshot.Quality(.5f);                          /// 设置截图质量：0~1
		gpu.Snapshoter();                               /// 使用截图器

	///	handler = new ExampleHandler();                 /// 如需自定义的录屏处理，比如推流
	///	capture = Mreators.Nv12Capturer(handler);       /// 自定义 NV12 帧数据处理器
	///	capture = Mreators.H264Capturer(handler);       /// 自定义 H264 帧数据处理器（硬编码输出）
		capture = Mreators.Capturer("capture.mp4");     /// 创建录像机
		capture.Quality(.5f, 5);                        /// 设置录像质量：0~1 和关键帧间隔（秒数）
		gpu.Capturer(480, 480);                         /// 使用录像机（设置录像尺寸）
	}

	public void Display(boolean enable) {
		gpu.Display(enable);                            /// 开启、关闭预览
	}

	public int[] Open() {
		return camera.Open(1280, 720, 1);               /// 打开摄像头，输入参考尺寸和机位（0/1 代表 后置/前置）
	}

	public void Close() {
		camera.Close();                                 /// 关闭摄像头
	}

	public int[] Switch() {
		return camera.Switch();                         /// 切换摄像头
	}

	public void Snapshot() {
		gpu.Snapshot();                                 /// 截一张图
	}

	public void CaptureOn() {
		capture.Resume();                               /// 开始录像
		gpu.Capture();
	}

	public void CaptureOff() {
		gpu.Pause();                                    /// 停止录像
		capture.Stop();
	}

	public void FacerOn() {
		gpu.FacerStart();                               /// 开始人脸检测
		gpu.FacerMarking(true);                         /// 打开人脸关键点标注，不必要
	}

	/// 停止人脸检测
	public void FacerOff(){
		gpu.FacerStop();                                /// 停止人脸检测
		gpu.FacerMarking(false);                        /// 关闭人脸关键点标注，不必要
	}

	public void Brightening(float strength) {
		gpu.Parameter("brightening", strength);         /// 改变美白程度：0~1
	}

	public void Smoothing(float strength) {
		gpu.Parameter("smoothing", strength);           /// 改变磨皮程度：0~1
	}

	public void Sharpening(float strength) {
		gpu.Parameter("sharpening", strength);          /// 改变锐化程度：0~1
	}
}
