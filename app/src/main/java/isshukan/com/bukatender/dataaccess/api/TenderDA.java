package isshukan.com.bukatender.dataaccess.api;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class TenderDA {

    public void getAllTender(Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_READ);
        APIHelper.accessAPI(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

}
