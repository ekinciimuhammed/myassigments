/**
 * Class representing the drill object.
 */
public class Drill {
    private Images drillImages; // Images of the drill
    private Images drillImagesUP; // Upward drill images
    private Images drillImagesLEFT; // Leftward drill images
    private Images drillImagesRİGHT; // Rightward drill images
    private Images drillImagesDOWN; // Downward drill images
    ImageViews drillImageView; // ImageView for the drill image
    private static final float INITIAL_FUEL = 10000.00f;
    Texts fuelText;
    Texts scoreText;
    Texts moneyText;
    Texts weightText;
    private float fuelLevel;
    private float score;
    private float money;
    private float totalWeight;
    /**
     * Get the drill images.
     * 
     * @return Images of the drill
     */
    public Images getDrillImages() {
        return drillImages;
    }
    /**
     * Set the drill images.
     * 
     * @param drillImages Images of the drill
     */
    public void setDrillImages(Images drillImages) {
        this.drillImages = drillImages;
    }
    /**
     * Get the upward drill images.
     * 
     * @return Upward drill images
     */
    public Images getDrillImagesUP() {
        return drillImagesUP;
    }
    /**
     * Set the upward drill images.
     * 
     * @param drillImagesUP Upward drill images
     */
    public void setDrillImagesUP(Images drillImagesUP) {
        this.drillImagesUP = drillImagesUP;
    }
    /**
     * Get the leftward drill images.
     * 
     * @return Leftward drill images
     */
    public Images getDrillImagesLEFT() {
        return drillImagesLEFT;
    }
    /**
     * Set the leftward drill images.
     * 
     * @param drillImagesLEFT Leftward drill images
     */
    public void setDrillImagesLEFT(Images drillImagesLEFT) {
        this.drillImagesLEFT = drillImagesLEFT;
    }
    /**
     * Get the rightward drill images.
     * 
     * @return Rightward drill images
     */
    public Images getDrillImagesRİGHT() {
        return drillImagesRİGHT;
    }
    /**
     * Set the rightward drill images.
     * 
     * @param drillImagesRIGHT Rightward drill images
     */
    public void setDrillImagesRİGHT(Images drillImagesRİGHT) {
        this.drillImagesRİGHT = drillImagesRİGHT;
    }
    /**
     * Get the downward drill images.
     * 
     * @return Downward drill images
     */
    public Images getDrillImagesDOWN() {
        return drillImagesDOWN;
    }
    /**
     * Set the downward drill images.
     * 
     * @param drillImagesDOWN Downward drill images
     */
    public void setDrillImagesDOWN(Images drillImagesDOWN) {
        this.drillImagesDOWN = drillImagesDOWN;
    }
    /**
     * Get the ImageView for the drill image.
     * 
     * @return ImageView for the drill image
     */
    public ImageViews getDrillImageView() {
        return drillImageView;
    }
    /**
     * Set the ImageView for the drill image.
     * 
     * @param drillImageView ImageView for the drill image
     */
    public void setDrillImageView(ImageViews drillImageView) {
        this.drillImageView = drillImageView;
    }
    /**
     * Get the current fuel level.
     * 
     * @return Current fuel level
     */
    public float getFuelLevel() {
        return fuelLevel;
    }
    /**
     * Set the current fuel level.
     * 
     * @param fuelLevel Current fuel level
     */
    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
    /**
     * Get the current score.
     * 
     * @return Current score
     */
    public float getScore() {
        return score;
    }
    /**
     * Set the current score.
     * 
     * @param score Current score
     */
    public void setScore(float score) {
        this.score = score;
    }
    /**
     * Get the current money.
     * 
     * @return Current money
     */
    public float getMoney() {
        return money;
    }
    /**
     * Set the current money.
     * 
     * @param money Current money
     */
    public void setMoney(float money) {
        this.money = money;
    }
    /**
     * Get the total weight of collected resources.
     * 
     * @return Total weight of collected resources
     */
    public float getTotalWeight() {
        return totalWeight;
    }
    /**
     * Set the total weight of collected resources.
     * 
     * @param totalWeight Total weight of collected resources
     */
    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }
    /**
     * Constructor for the Drill class.
     * Sets the drill images and the position of the drill image.
     */
    public Drill() {
        this.drillImages = new Images("assets/drill/drill_16.png");
        this.drillImagesUP = new Images("assets/drill/drill_51.png");
        this.drillImagesLEFT = new Images("assets/drill/drill_52.png");
        this.drillImagesRİGHT = new Images("assets/drill/drill_58.png");
        this.drillImagesDOWN = new Images("assets/drill/drill_45.png");
        this.drillImageView = new ImageViews(drillImages, 350, 50, 50, 50);
        this.fuelLevel = INITIAL_FUEL;
        this.score = 0;
        this.money = 0;
        this.totalWeight = 0;
    }
}