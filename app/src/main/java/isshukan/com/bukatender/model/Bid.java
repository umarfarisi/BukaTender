package isshukan.com.bukatender.model;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class Bid {
    private int tenderId;
    private String productId;
    private String userTenderId;
    private String userBidId;
    private String imageResource;
    private String titleProduct;
    private double bidPrice;
    private String shortDescription;

    public Bid(int tenderId, String productId, String userTenderId, String userBidId, String imageResource, String titleProduct, double bidPrice, String shortDescription) {
        this.tenderId = tenderId;
        this.productId = productId;
        this.userTenderId = userTenderId;
        this.userBidId = userBidId;
        this.imageResource = imageResource;
        this.titleProduct = titleProduct;
        this.bidPrice = bidPrice;
        this.shortDescription = shortDescription;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserTenderId() {
        return userTenderId;
    }

    public void setUserTenderId(String userTenderId) {
        this.userTenderId = userTenderId;
    }

    public String getUserBidId() {
        return userBidId;
    }

    public void setUserBidId(String userBidId) {
        this.userBidId = userBidId;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (tenderId != bid.tenderId) return false;
        if (!productId.equals(bid.productId)) return false;
        if (!userTenderId.equals(bid.userTenderId)) return false;
        return userBidId.equals(bid.userBidId);

    }

    @Override
    public int hashCode() {
        int result = tenderId;
        result = 31 * result + productId.hashCode();
        result = 31 * result + userTenderId.hashCode();
        result = 31 * result + userBidId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "tenderId=" + tenderId +
                ", productId='" + productId + '\'' +
                ", userTenderId='" + userTenderId + '\'' +
                ", userBidId='" + userBidId + '\'' +
                ", imageResource='" + imageResource + '\'' +
                ", titleProduct='" + titleProduct + '\'' +
                ", bidPrice=" + bidPrice +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
