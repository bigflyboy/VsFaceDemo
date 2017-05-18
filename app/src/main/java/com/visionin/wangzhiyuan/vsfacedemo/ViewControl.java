package com.visionin.wangzhiyuan.vsfacedemo;

import android.app.Activity;
import android.view.SurfaceView;

import com.zeroo.lib.Zobject;
import com.zeroo.lib.gpu.Greators;
import com.zeroo.lib.gpu.Iamera;
import com.zeroo.lib.gpu.Iraphix;
import com.zeroo.lib.media.ZpegEnc;

/**
 * Created by wangzhiyuan on 2017/5/17.
 */

public class ViewControl{
    protected Iamera camera = null;
    protected Iraphix gpu = null;

    protected ZpegEnc snapshot = null;
    protected ZpegEnc capture  = null;

    public void Init(Activity activity) {
        Zobject.Zactivity = activity;
    /// Zobject.Zebug = true;
    }

    //打开摄像头
    public void openCamera(SurfaceView surface){
        if(gpu == null) {
            gpu = Greators.Graphix("G+");
            gpu.Display(true);
        }
        if(camera == null) {
            camera = Greators.Camera();
            camera.Mode("facer");           /// 图像美化+人脸检测
            camera.Preview(surface);
        }
        if(snapshot == null) {
            snapshot = new ZpegEnc(1, "snapshot.jpg");
            snapshot.fp.Path(null);                     /// use default path: Android/data/com.zeroo.???/files/
            snapshot.fp.Indexing(".%d", 0);             /// snapshot.1.jpg (*.2.jpg,*.3.jpg,...)
            snapshot.Quality(.5f);
            snapshot.async = false;                     /// async=true to encode jpeg in thread
            //gpu.Snapshoter(snapshot);
        }
        if(capture == null) {
        ///	capture = new CustomizedCapturer();
            capture = new ZpegEnc(4, "capture.mp4");
            capture.fp.Path(null);                      /// use default path: Android/data/com.zeroo.???/files/
            capture.fp.Indexing(".%d", 0);              /// capture.1.mp4 (*.2.mp4,*.3.mp4,...)
            capture.Quality(.5f, 5);
            capture.async = true;                       /// async=true to encode mp4 in thread
            capture.live = true;
            capture.Pause();
            //gpu.Capturer(capture, 480, 480);
        }
        camera.Open(1280, 720, 1);
    }

    //关闭并释放摄像头
    public void closeCamera(){
        camera.Close();
    }

    //切换前后摄像头
    public void switchCamera(){
        gpu.Snapshot();
        camera.Switch();
    }

    //截图
    public void capturePicture(){
        gpu.Snapshot();
    }

    //开始录像
    public void captureVideo(){
        capture.Resume();
        gpu.Capture();
    }

    //停止录像
    public void stopVideo(){
        gpu.Pause();
        capture.Stop();
    }

    //开始人脸检测
    public void startFaceDetect(){
        gpu.FacerStart();
        gpu.FacerMarking(true);
    }

    //停止人脸检测
    public void stopFaceDetect(){
        gpu.FacerStop();
        gpu.FacerMarking(false);
    }

    //美白指数改变
    public void brighteningChange(float strength){
        gpu.Parameter("brightening", strength);
    }

    //磨皮指数改变
    public void smoothingChange(float strength){
        gpu.Parameter("smoothing", strength);
    }

    //锐化指数改变
    public void sharpeningChange(float strength){
        gpu.Parameter("sharpening", strength);
    }
}
