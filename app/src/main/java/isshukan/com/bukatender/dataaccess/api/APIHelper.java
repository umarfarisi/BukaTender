package isshukan.com.bukatender.dataaccess.api;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import isshukan.com.bukatender.support.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class APIHelper {
    public static void post(String url , Response.Listener<String> listener, Response.ErrorListener errorListener, final Map<String, String> params){
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(GlobalVariable.APP_CONTEXT).add(request);
    }


    public static void get(String url ,Response.Listener<String> listener, Response.ErrorListener errorListener){
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        Volley.newRequestQueue(GlobalVariable.APP_CONTEXT).add(request);
    }
}
