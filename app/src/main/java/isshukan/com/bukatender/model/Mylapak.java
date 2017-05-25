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

    public Mylapak(String mylapakId, String title, Double price, String category, String description, String imageSmallURL, String imageURL) {
        this.mylapakId = mylapakId;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imageSmallURL = imageSmallURL;
        this.imageURL = imageURL;
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
                '}';
    }
}
