package isshukan.com.bukatender.model;

import java.io.Serializable;

/**
 * Created by - on 25/05/2017.
 */

public class Mylapak implements Serializable {
    private String mylapakId;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String imageSmallURL;
    private String imageURL;
    private String condition;
    private int stock;
    private double avg_rate;
    private int user_count_rate;
    private int view_count;
    private int interest_count;

    public Mylapak(String mylapakId, String title, Double price, String category, String description, String imageSmallURL, String imageURL, String condition, int stock, double avg_rate, int user_count_rate, int view_count, int interest_count) {
        this.mylapakId = mylapakId;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imageSmallURL = imageSmallURL;
        this.imageURL = imageURL;
        this.condition = condition;
        this.stock = stock;
        this.avg_rate = avg_rate;
        this.user_count_rate = user_count_rate;
        this.view_count = view_count;
        this.interest_count = interest_count;
    }

    public String getMylapakId() {
        return mylapakId;
    }

    public void setMylapakId(String mylapakId) {
        this.mylapakId = mylapakId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSmallURL() {
        return imageSmallURL;
    }

    public void setImageSmallURL(String imageSmallURL) {
        this.imageSmallURL = imageSmallURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(double avg_rate) {
        this.avg_rate = avg_rate;
    }

    public int getUser_count_rate() {
        return user_count_rate;
    }

    public void setUser_count_rate(int user_count_rate) {
        this.user_count_rate = user_count_rate;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getInterest_count() {
        return interest_count;
    }

    public void setInterest_count(int interest_count) {
        this.interest_count = interest_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mylapak mylapak = (Mylapak) o;

        return mylapakId.equals(mylapak.mylapakId);

    }

    @Override
    public int hashCode() {
        return mylapakId.hashCode();
    }

    @Override
    public String toString() {
        return "Mylapak{" +
                "mylapakId='" + mylapakId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", imageSmallURL='" + imageSmallURL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", condition='" + condition + '\'' +
                ", stock=" + stock +
                ", avg_rate=" + avg_rate +
                ", user_count_rate=" + user_count_rate +
                ", view_count=" + view_count +
                ", interest_count=" + interest_count +
                '}';
    }
}
