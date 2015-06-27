package com.example.asiablr.graphicsbasics;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleRenderer implements GLSurfaceView.Renderer {

    private TinyBox box;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //set background to black
        gl.glClearColor(0f, 0f, 0f, 1f);
        box = new TinyBox();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // adjust viewport based on screen rotation
        gl.glViewport(0, 0, width, height);

        // make adjustments for sreen rotation
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection matrix
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // draw background colour
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // set model view
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        // when using model view, set the view point
        GLU.gluLookAt(gl,0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        box.draw(gl);

//        gl.glRotatef(0f, 0f, 0f, 1.0f);
    }
}

class TinyBox {

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;

    // number of coordinates per vertex
    static final int COORDS_PER_VERTEX = 3;
    private static final float coord[] = {
            -.125f, .125f, 0f,
            -.125f, -.125f, 0f,
            .125f, -.125f, 0f,
            .125f, .125f, 0f,
    };

    private final short drawOrder[] = {0, 1, 2, 0, 2, 3};
    private final float colour[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f}; // r, g, b, a

    public TinyBox() {
        // initialize vertex buffers
        ByteBuffer buf = ByteBuffer.allocateDirect(coord.length * 4);
        buf.order(ByteOrder.nativeOrder());
        vertexBuffer = buf.asFloatBuffer();
        vertexBuffer.put(coord);
        vertexBuffer.position(0);

        // initialize draw list buffers
        ByteBuffer lbuf = ByteBuffer.allocateDirect(drawOrder.length * 2);
        lbuf.order(ByteOrder.nativeOrder());
        drawListBuffer = lbuf.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }

    public void draw(GL10 gl) {
        //enable vertex array
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(colour[0], colour[1], colour[2], colour[3]);
        gl.glVertexPointer(COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer);

        //disable vertex to avoid conflicts with shapes that doesnt use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}