package youten.redo.jarserver.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import youten.redo.jarserver.core.DataStoreListener;
import youten.redo.jarserver.server.JServer;

public class JarServerSampleActivity extends Activity implements DataStoreListener {
    private static final String TAG = "JarServerSample";
    private static final int PORT = 8888;
    private JServer mServer = null;

    @Override
    public void onRead(String key, String value) {
        Log.d(TAG, "onRead key=" + key + " value=" + value);
    }

    @Override
    public void onChange(String key, String value) {
        Log.d(TAG, "onChange key=" + key + " value=" + value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServer = new JServer(PORT);
        mServer.start(getApplicationContext(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mServer.stop();
        mServer = null;
    }
}
