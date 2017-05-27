package isshukan.com.bukatender.model;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wirabdillah on 26/05/17.
 */

public class Transaction implements Serializable{
    private int id;
    private String state;
    private String updated_at;
    private boolean unread;
    private boolean quick_trans;
    private String transaction_id;
    private int amount;
    private int quantity;
    private String courier;
    private String buyer_notes;
    private int shipping_fee;
    private int shipping_id;
    private String shipping_code;
    private String shipping_service;
    private int shipping_cost;
    private int insurance_cost;
    private int total_amount;
    private int coded_amount;
    private int uniq_code;
    private String payment_method;
    private List<Mylapak> products;
    private User buyer;
    private User seller;

    public Transaction(JSONObject transactionJSON) throws JSONException {
        this.id = transactionJSON.getInt("id");
        this.state = transactionJSON.getString("state");
        this.updated_at = transactionJSON.getString("update_at");
        this.unread = transactionJSON.getBoolean("unread");
        this.quick_trans = transactionJSON.getBoolean("quick_trans");
        this.transaction_id = transactionJSON.getString("transaction_id");
        this.amount = transactionJSON.getInt("amount");
        this.quantity = transactionJSON.getInt("quantity");
        this.courier = transactionJSON.getString("courier");
        this.buyer_notes = transactionJSON.getString("buyer_notes");
        this.shipping_fee = transactionJSON.getInt("shipping_fee");
        // TODO buat untuk semua field

        JSONArray productListJSON = transactionJSON.getJSONArray("products");
        JSONObject productJSON;
        products = new ArrayList<Mylapak>();

        for (int i = 0; i < productListJSON.length(); i++){
            products.add(new Mylapak(productListJSON.getJSONObject(i)));
        }

        buyer = new User(transactionJSON.getJSONObject("buyer"));
        seller = new User(transactionJSON.getJSONObject("seller"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public boolean isQuick_trans() {
        return quick_trans;
    }

    public void setQuick_trans(boolean quick_trans) {
        this.quick_trans = quick_trans;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getBuyer_notes() {
        return buyer_notes;
    }

    public void setBuyer_notes(String buyer_notes) {
        this.buyer_notes = buyer_notes;
    }

    public int getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(int shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public String getShipping_service() {
        return shipping_service;
    }

    public void setShipping_service(String shipping_service) {
        this.shipping_service = shipping_service;
    }

    public int getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(int shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public int getInsurance_cost() {
        return insurance_cost;
    }

    public void setInsurance_cost(int insurance_cost) {
        this.insurance_cost = insurance_cost;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getCoded_amount() {
        return coded_amount;
    }

    public void setCoded_amount(int coded_amount) {
        this.coded_amount = coded_amount;
    }

    public int getUniq_code() {
        return uniq_code;
    }

    public void setUniq_code(int uniq_code) {
        this.uniq_code = uniq_code;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public List<Mylapak> getProducts() {
        return products;
    }

    public void setProducts(List<Mylapak> products) {
        this.products = products;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
