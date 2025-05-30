/**
 * Class representing different types of minerals.
 */
public class Minerals {
    Images lavaImages; // Image for lava
    Images goldImages; // Image for gold
    Images diamondImages; // Image for diamond
    Images obstaclImages; // Image for obstacle
    Images soilImages; // Image for soil
    Images silverImages; // Image for silver
    Images platınuImages; // Image for platinum
    Images ıronıumImages; // Image for ironium
    /**
     * Constructor for Minerals class.
     * Initializes the images for different minerals.
     */
    public Minerals() {
        this.lavaImages = new Images("assets/underground/lava_03.png");
        this.goldImages = new Images("assets/underground/valuable_goldium.png");
        this.diamondImages = new Images("assets/underground/valuable_diamond.png");
        this.obstaclImages = new Images("assets/underground/obstacle_02.png");
        this.soilImages = new Images("assets/underground/soil_03.png");
        this.silverImages = new Images("assets/underground/valuable_silverium.png");
        this.platınuImages = new Images("assets/underground/valuable_platinum.png");
        this.ıronıumImages = new Images("assets/underground/valuable_ironium.png");
    }
}
