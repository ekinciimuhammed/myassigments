import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Subclass of Text providing additional functionalities.
 */
public class Texts extends Text {
    private static final String DEFAULT_FONT = "Arial";
    /**
     * Constructor for Texts class.
     * Initializes the text with the specified content, font size, position, and
     * color.
     *
     * @param text     The content of the text.
     * @param fontSize The font size of the text.
     * @param x        The x-coordinate of the top-left corner of the text.
     * @param y        The y-coordinate of the top-left corner of the text.
     * @param color    The color of the text.
     */
    public Texts(String text, int fontSize, int x, int y, Color color) {
        super(text);
        setFont(Font.font(DEFAULT_FONT, fontSize));
        setFill(color);
        setX(x);
        setY(y);
        setVisible(true);
    }
    /**
     * Method to set font and size for the text.
     *
     * @param fontName The name of the font.
     * @param fontSize The font size.
     */
    public void setFont(String fontName, int fontSize) {
        setFont(Font.font(fontName, fontSize));
    }
}
