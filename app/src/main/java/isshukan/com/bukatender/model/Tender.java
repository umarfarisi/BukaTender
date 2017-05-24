package isshukan.com.bukatender.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class Tender {
    private int tenderId;
    private String userId;
    private String title;
    private long validityPeriod;
    private double startingPrice;
    private String imageResource;
    private List<String> tag;

    public Tender(int tenderId, String userId, String title, long validityPeriod, double startingPrice, String imageResource) {
        this.tenderId = tenderId;
        this.userId = userId;
        this.title = title;
        this.validityPeriod = validityPeriod;
        this.startingPrice = startingPrice;
        this.imageResource = imageResource;
        this.tag = new ArrayList<>();
    }

    public Tender() {
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(long validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tender tender = (Tender) o;

        if (tenderId != tender.tenderId) return false;
        return userId.equals(tender.userId);

    }

    @Override
    public int hashCode() {
        int result = tenderId;
        result = 31 * result + userId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "tenderId='" + tenderId + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", validityPeriod=" + validityPeriod +
                ", startingPrice=" + startingPrice +
                ", imageResource='" + imageResource + '\'' +
                '}';
    }
}
