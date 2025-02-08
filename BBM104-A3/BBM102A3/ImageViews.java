import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Subclass of ImageView providing additional functionalities.
 */
public class ImageViews extends ImageView {
    /**
     * Constructor for ImageViews class.
     * Initializes the ImageView with the specified image.
     * 
     * @param image The image to be displayed.
     */
    public ImageViews(Image image) {
        super(image);
    }
    /**
     * Constructor for ImageViews class.
     * Initializes the ImageView with the specified image, position, and size.
     * 
     * @param image  The image to be displayed.
     * @param x      The x-coordinate of the top-left corner of the ImageView.
     * @param y      The y-coordinate of the top-left corner of the ImageView.
     * @param height The height of the ImageView.
     * @param width  The width of the ImageView.
     */
    public ImageViews(Image image, int x, int y, int height, int width) {
        super(image);
        setFitHeight(height);
        setFitWidth(width);
        setX(x);
        setY(y);
    }
}