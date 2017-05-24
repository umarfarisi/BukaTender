package isshukan.com.bukatender.dataaccess;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.support.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 23/05/2017
 */
public class TagDA {
    public void accessAPI(Response.Listener<String> listener, Response.ErrorListener errorListener, final Map<String, String> params){
        StringRequest request = new StringRequest(Request.Method.POST, ConstantAPI.BASE_URL+ ConstantAPI.TAG_END_POINT, listener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(GlobalVariable.APP_CONTEXT).add(request);
    }
}
