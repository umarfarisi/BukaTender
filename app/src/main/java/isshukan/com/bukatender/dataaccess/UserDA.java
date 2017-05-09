package isshukan.com.bukatender.dataaccess;

import java.util.List;
import java.util.Map;

import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.mode.User;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class UserDA implements BaseDA<User> {

    @Override
    public void insert(User user, DACallback<Boolean> callback) {
        //TODO IMPLEMENT WITH ASYNTASK
    }

    @Override
    public void update(User oldE, User newE, DACallback<Boolean> callback) {
        //TODO IMPLEMENT WITH ASYNTASK
    }

    @Override
    public void getAll(DACallback<List<User>> callback) {
        //TODO IMPLEMENT WITH ASYNTASK
    }

    @Override
    public void get(Map<String, String> whereClauses, DACallback<User> callback) {
        if(whereClauses.get(ConstantAPI.USERNAME).equals("umarfarisi") && whereClauses.get(ConstantAPI.PASSWORD).equals("123")){
            callback.onSuccess(new User("Muhammad Umar Farisi","pharese@gmail.com","085711122365","username","123"));
        }else{
            callback.onFailure("failed to get user");
        }
        //TODO IMPLEMENT WITH ASYNTASK
    }

    @Override
    public void remove(User user, DACallback<Boolean> callback) {
        //TODO IMPLEMENT WITH ASYNTASK
    }

}