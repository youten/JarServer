package youten.redo.jarserver.server;

import android.util.Log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youten.redo.jarserver.core.DataStore;
import youten.redo.jarserver.core.DataStoreListener;
import youten.redo.jarserver.http.Charset;
import youten.redo.jarserver.http.ContentType;

public class DataStoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TAG = DataStoreServlet.class.getSimpleName();

    private static final String KEY_KEY = "key";
    private DataStoreListener mListener;

    public DataStoreServlet(DataStoreListener listener) {
        mListener = listener;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY_KEY);
        Log.d(TAG, "doGet key=" + key);

        String json = DataStore.get(key);
        Log.d(TAG, " json=" + json);
        ServletUtil.responseJSON(resp, json);

        if (mListener != null) {
            mListener.onRead(key, json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY_KEY);
        Log.d(TAG, "doPost key=" + key);
        String json = null;
        req.setCharacterEncoding(Charset.UTF_8);
        resp.setStatus(HttpServletResponse.SC_OK);

        if ((key == null) || (key.length() == 0) || !req.getContentType().startsWith(ContentType.APPLICATION_JSON)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            json = IOUtil.toString(req.getReader());
        }
        Log.d(TAG, " json=" + json);

        if ((json == null) || (json.length() == 0)) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } else {
            DataStore.set(key, json);
        }

        json = DataStore.get(key);
        ServletUtil.responseJSON(resp, json);

        if (mListener != null) {
            mListener.onChange(key, json);
        }
    }

}
