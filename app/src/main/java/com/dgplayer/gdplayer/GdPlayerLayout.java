package com.dgplayer.gdplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

import com.dgplayer.gdplayer.player.DashRendererBuilder;
import com.dgplayer.gdplayer.player.DemoPlayer;
import com.dgplayer.gdplayer.player.ExtractorRendererBuilder;
import com.dgplayer.gdplayer.player.HlsRendererBuilder;
import com.dgplayer.gdplayer.player.SmoothStreamingRendererBuilder;
import com.google.android.exoplayer.AspectRatioFrameLayout;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioCapabilitiesReceiver;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.util.DebugTextViewHelper;
import com.google.android.exoplayer.util.Util;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by gauravdubey on 24/10/15.
 */
public class GdPlayerLayout extends AspectRatioFrameLayout implements SurfaceHolder.Callback, View.OnClickListener,
        DemoPlayer.Listener, DemoPlayer.CaptionListener, DemoPlayer.Id3MetadataListener,
        AudioCapabilitiesReceiver.Listener{

    public static final int TYPE_DASH = 0;
    public static final int TYPE_SS = 1;
    public static final int TYPE_HLS = 2;
    public static final int TYPE_OTHER = 3;

    private static final CookieManager defaultCookieManager;
    static {
        defaultCookieManager = new CookieManager();
        defaultCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private Context __context;
    private DemoPlayer __player;
    private long __playerPosition;
    private boolean __playerNeedsPrepare;
    private Uri __contentUri;
    private int __contentType;
    private String __contentId;

    public GdPlayerLayout(Context context) {
        super(context);
        initGdPlayerView(context);
    }

    public GdPlayerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGdPlayerView(context);
    }


    private void initGdPlayerView(Context context){
        this.__context=context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.gdplayer_view, this);
        audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(__context,this);
        audioCapabilitiesReceiver.register();


    }
    public void setContentType(int type){
        this.__contentType=type;
    }
    public int getContentType(){
        return this.__contentType;
    }
    public void setContentId(String Id){
        this.__contentId=Id;
    }
    public String getContentId(){
        return this.__contentId;
    }

    public void setContentUri(Uri uri){
        this.__contentUri=uri;
    }
    public Uri getContentUri(){
        return this.__contentUri;
    }

    public void start(){
        this.preparePlayer(true);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onCues(List<Cue> cues) {

    }

    @Override
    public void onId3Metadata(Map<String, Object> metadata) {

    }

    @Override
    public void onStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

    }

    @Override
    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {

    }

    @Override
    public void onClick(View v) {

    }

    private DemoPlayer.RendererBuilder getRendererBuilder() {
        String userAgent = Util.getUserAgent(__context, "GdPlayerView");
        switch (__contentType) {
            case TYPE_SS:
                return new SmoothStreamingRendererBuilder(__context, userAgent, __contentUri.toString(),
                        new SmoothStreamingTestMediaDrmCallback());
            case TYPE_DASH:
                return new DashRendererBuilder(__context, userAgent, __contentUri.toString(),
                        new WidevineTestMediaDrmCallback(__contentId));
            case TYPE_HLS:
                return new HlsRendererBuilder(__context, userAgent, __contentUri.toString());
            case TYPE_OTHER:
                return new ExtractorRendererBuilder(__context, userAgent, __contentUri);
            default:
                throw new IllegalStateException("Unsupported type: " + __contentType);
        }
    }

    private void preparePlayer(boolean playWhenReady) {
        if (__player == null) {
            __player = new DemoPlayer(getRendererBuilder());
            __player.addListener(this);
            __player.setCaptionListener(this);
            __player.setMetadataListener(this);
            __player.seekTo(__playerPosition);
            __playerNeedsPrepare = true;
            //mediaController.setMediaPlayer(__player.getPlayerControl());
            //mediaController.setEnabled(true);
            //eventLogger = new EventLogger();
            //eventLogger.startSession();
            //__player.addListener(eventLogger);
            //__player.setInfoListener(eventLogger);
            //__player.setInternalErrorListener(eventLogger);
            //debugViewHelper = new DebugTextViewHelper(player, debugTextView);
            //debugViewHelper.start();
        }
        if (__playerNeedsPrepare) {
            __player.prepare();
            __playerNeedsPrepare = false;
            //updateButtonVisibilities();
        }
        SurfaceView surfaceView=(SurfaceView)findViewById(R.id.surface_view);
        __player.setSurface(surfaceView.getHolder().getSurface());
        __player.setPlayWhenReady(playWhenReady);
    }





    private void releasePlayer() {
        if (__player != null) {
            //debugViewHelper.stop();
            //debugViewHelper = null;
            __playerPosition = __player.getCurrentPosition();
            __player.release();
            __player = null;
            //eventLogger.endSession();
            //eventLogger = null;
        }
    }
}
