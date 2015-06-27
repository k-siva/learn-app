package com.example.asiablr.graphicsbasics;


import android.content.Context;
import android.opengl.GLSurfaceView;

public class SimpleSurfaceView extends GLSurfaceView {

    private final SimpleRenderer mRender;

    public SimpleSurfaceView(Context context) {
        super(context);

        mRender = new SimpleRenderer();
        setRenderer(mRender);

        // will reduce graphics activity
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

}
