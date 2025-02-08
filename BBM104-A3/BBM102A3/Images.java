import javafx.scene.image.Image;
/**
 * Subclass of Image representing images with fixed width and height.
 */
public class Images extends Image {
    static final int width = 50; // Fixed width for images.
    static final int height = 50; // Fixed height for images.
    /**
     * Constructor for Images class.
     * Initializes the image with the specified URL.
     * 
     * @param url The URL of the image file.
     */
    public Images(String url) {
        super(url);
    }
}