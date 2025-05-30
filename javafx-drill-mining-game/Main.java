import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private static final int WIDTH = 800;

    private static final int HEIGHT = 775;

    /**
     * Method to start the game.
     *
     * @param stage The primary stage of the application.
     */
    public void start(Stage stage) {
        Drill drill = new Drill();
        Minerals minerals = new Minerals();
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLUE);
        Random random = new Random();
        Rectangle rect1 = new Rectangle(0, 0, 800, 100);
        rect1.setFill(Color.BLUE);
        Rectangle rect2 = new Rectangle(0, 100, 800, 675);
        rect2.setFill(Color.SADDLEBROWN);
        root.getChildren().addAll(rect1, rect2);
        int count = 0;
        ArrayList<String> myArrayListForCheck = new ArrayList<>();
        loadSceneWithImages(myArrayListForCheck, root, minerals.obstaclImages, count, random, minerals.lavaImages,
                minerals.goldImages, minerals.diamondImages, minerals.silverImages, minerals.platınuImages,
                minerals.ıronıumImages, minerals.soilImages);
        root.getChildren().add(drill.getDrillImageView());
        drill.fuelText = new Texts("Fuel: " + drill.getFuelLevel(), 20, 20, 30, Color.BLACK);
        root.getChildren().add(drill.fuelText);
        drill.scoreText = new Texts("Score: " + drill.getScore(), 20, 20, 50, Color.BLACK);
        root.getChildren().add(drill.scoreText);
        drill.moneyText = new Texts("Money: $" + drill.getMoney(), 20, 20, 90, Color.BLACK);
        root.getChildren().add(drill.moneyText);
        drill.weightText = new Texts("Total Weight: " + drill.getTotalWeight() + " kg", 20, 20, 70, Color.BLACK);
        root.getChildren().add(drill.weightText);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            decreaseFuel(root, drill);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline gravityTimeline = new Timeline(new KeyFrame(Duration.seconds(0.025), event -> {
            applyGravity(root, drill.getDrillImageView());
        }));
        gravityTimeline.setCycleCount(Timeline.INDEFINITE);
        gravityTimeline.play();

        makeMovement(scene, drill.getDrillImageView(), root, drill.getDrillImagesDOWN(), drill.getDrillImagesUP(),
                minerals.goldImages,
                minerals.lavaImages, minerals.platınuImages, minerals.obstaclImages, minerals.diamondImages,
                minerals.ıronıumImages, minerals.silverImages, drill.getDrillImagesLEFT(), drill.getDrillImagesRİGHT(),
                drill);

        stage.setScene(scene);
        stage.setTitle("HU-LOAD GAME");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to decrease the fuel level.
     *
     * @param root The root group of the scene.
     */
    private void decreaseFuel(Group root, Drill drill) {
        drill.setFuelLevel(drill.getFuelLevel() - 0.01f);
        drill.fuelText.setText("Fuel: " + drill.getFuelLevel());
        //if gas will finish.
        if (drill.getFuelLevel() <= 0) {
            Platform.runLater(() -> {
                root.getChildren().clear();
                Rectangle greenBackground = new Rectangle(WIDTH, HEIGHT, Color.GREEN);
                root.getChildren().add(greenBackground);
                Text gameOverText = new Texts("GAME OVER", 50, 200, 300, Color.BLACK);
                root.getChildren().add(gameOverText);
                Text scoreInfo = new Texts("Score: " + drill.getScore(), 40, 200, 350, Color.WHITE);
                root.getChildren().add(scoreInfo);
                Text moneyInfo = new Texts("Money: $" + drill.getMoney(), 40, 200, 400, Color.WHITE);
                root.getChildren().add(moneyInfo);
                Text weightInfo = new Texts("Total Weight: " + drill.getTotalWeight() + " kg", 40, 200, 450,
                        Color.WHITE);
                root.getChildren().add(weightInfo);
            });
        }
    }

    /**
     * Method to remove a block from the scene based on the direction of the drill.
     *
     * @param root           The root group of the scene.
     * @param drillImageView The ImageView representing the drill.
     * @param direction      The direction in which the drill is moving.
     * @param goldImages     The Images object representing gold.
     * @param lavaImages     The Images object representing lava.
     * @param platınuImages  The Images object representing platinum.
     * @param obstaclImages  The Images object representing obstacles.
     * @param diamondImages  The Images object representing diamonds.
     * @param ıronıumImages  The Images object representing ironium.
     * @param silverImages   The Images object representing silver.
     */
    private void removeBlock(Group root, ImageViews drillImageView, Direction direction, Images goldImages,
                             Images lavaImages, Images platınuImages, Images obstaclImages, Images diamondImages, Images ıronıumImages,
                             Images silverImages, Drill drill) {
        HashMap<String, Integer> mineralValues = new HashMap<>();
        mineralValues.put("ıronıumImage", 30);
        mineralValues.put("silverImage", 100);
        mineralValues.put("goldImage", 250);
        mineralValues.put("platınuImage", 750);
        mineralValues.put("diamondImage", 100000);
        HashMap<String, Integer> mineralPoints = new HashMap<>();
        mineralPoints.put("ıronıumImage", 150);
        mineralPoints.put("silverImage", 500);
        mineralPoints.put("goldImage", 1250);
        mineralPoints.put("platınuImage", 3750);
        mineralPoints.put("diamondImage", 500000);
        HashMap<String, Integer> mineralWeights = new HashMap<>();
        mineralWeights.put("ıronıumImage", 10);
        mineralWeights.put("silverImage", 10);
        mineralWeights.put("goldImage", 20);
        mineralWeights.put("platınuImage", 30);
        mineralWeights.put("diamondImage", 100);
        double drillX = drillImageView.getX() + (25);
        double drillY = drillImageView.getY() + (25);
        switch (direction) {
            case UP:
                drillY -= 50;
                break;
            case DOWN:
                drillY += 50;
                break;
            case LEFT:
                drillX -= 50;
                break;
            case RIGHT:
                drillX += 50;
                break;
            case BEGINNING:
                break;
            case END:
                break;
            default:
                break;
        }
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof ImageView && node != drillImageView) {
                ImageView block = (ImageView) node;
                boolean intersects = block.getBoundsInParent().intersects(drillX, drillY, 1, 1);
                if (intersects) {
                    if (block.getImage() != lavaImages && block.getImage() != obstaclImages) {
                       
                        String blockName = "Unknown";
                        if (block.getImage() == goldImages) {
                            blockName = "goldImage";
                        } else if (block.getImage() == diamondImages) {
                            blockName = "diamondImage";
                        } else if (block.getImage() == platınuImages) {
                            blockName = "platınuImage";
                        } else if (block.getImage() == ıronıumImages) {
                            blockName = "ıronıumImage";
                        } else if (block.getImage() == silverImages) {
                            blockName = "silverImage";
                        }
                        int points = mineralPoints.getOrDefault(blockName, 0);
                        drill.setScore(drill.getScore() + points);//change Score.
                        drill.scoreText.setText("Score: " + drill.getScore());
                        int value = mineralValues.getOrDefault(blockName, 0);
                        drill.setMoney(drill.getMoney() + value);//change money 
                        drill.moneyText.setText("Money: $" + drill.getMoney());
                        int weight = mineralWeights.getOrDefault(blockName, 0);
                        drill.setTotalWeight(weight + drill.getTotalWeight());//change total weight(it means storage,haul...)
                        drill.weightText.setText("Total Weight: " + drill.getTotalWeight() + " kg");
                        root.getChildren().remove(block);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Checks if there is a block above the drill.
     *
     * @param drillImageView The ImageView representing the drill.
     * @param root           The root group of the scene.
     * @return True if there is a block above the drill, false otherwise.
     */
    private boolean isBlockAbove(ImageView drillImageView, Group root) {
        double drillX = drillImageView.getX() + (25);
        double drillY = drillImageView.getY();
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof ImageView && node != drillImageView) {
                ImageView block = (ImageView) node;
                boolean isAbove = block.getBoundsInParent().contains(drillX, drillY);
                if (isAbove) {
                    return true; //
                }
            }
        }
        return false;
    }

    /**
     * Applies gravity to the drill, causing it to fall downwards if there is no
     * block below.
     *
     * @param root           The root group of the scene.
     * @param drillImageView The ImageView representing the drill.
     */
    private void applyGravity(Group root, ImageView drillImageView) {
        // Determine the coordinates of the bottom center of the drill
        double drillX = drillImageView.getX() + (25);
        double drillY = drillImageView.getY() + drillImageView.getFitHeight();
        boolean isBlockBelow = false;
        // Check if there is a block below the drill
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof ImageView && node != drillImageView) {
                ImageView block = (ImageView) node;
                boolean isBelow = block.getBoundsInParent().contains(drillX, drillY);
                if (isBelow) {
                    isBlockBelow = true;
                    break;
                }
            }
        }
        // If there is no block below and the drill is not at the bottom of the screen,
        // move the drill down by 1 pixel
        if (!isBlockBelow && drillY < HEIGHT - drillImageView.getFitHeight()) {
            drillImageView.setY(drillImageView.getY() + 1);
        }
    }

    /**
     * Loads the scene with various images including obstacles, minerals, and soil
     * based on certain conditions.
     *
     * @param myArrayListForCheck An ArrayList used for checking the presence of
     *                            certain items.
     * @param root                The root group of the scene.
     * @param obstaclImages       The image representing obstacles.
     * @param count               A count variable used for certain conditions.
     * @param random              A Random object used for generating random
     *                            numbers.
     * @param lavaImages          The image representing lava.
     * @param goldImages          The image representing gold.
     * @param diamondImages       The image representing diamond.
     * @param silverImages        The image representing silver.
     * @param platınuImages       The image representing platinum.
     * @param ıronıumImages       The image representing ironum.
     * @param soilImages          The image representing soil.
     */
    private void loadSceneWithImages(ArrayList<String> myArrayListForCheck, Group root, Images obstaclImages, int count,
                                     Random random, Images lavaImages, Images goldImages, Images diamondImages, Images silverImages,
                                     Images platınuImages, Images ıronıumImages, Images soilImages) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                if (i < 2) {
                } else if (i == 2) {
                    // Logic for generating soil top images
                    String vocabularyForChecking = i + "-" + j;
                    if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                        myArrayListForCheck.add(vocabularyForChecking);
                        Image soilTopImage = new Image(
                                "assets/underground/top_01.png");
                        ImageView soilTopImageView = new ImageView(soilTopImage);
                        soilTopImageView.setFitHeight(Images.height);
                        soilTopImageView.setFitWidth(Images.width);
                        soilTopImageView.setY(i * Images.width);
                        soilTopImageView.setX(j * Images.height);
                        root.getChildren().add(soilTopImageView);
                    }
                } else if (((j == 0) || (j == 15)) && i != 2) {
                    // Logic for generating obstacle images
                    String vocabularyForChecking = i + "-" + j;
                    if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                        myArrayListForCheck.add(vocabularyForChecking);
                        ImageView obstaclImageView = new ImageView(obstaclImages);
                        obstaclImageView.setFitHeight(Images.height);
                        obstaclImageView.setFitWidth(Images.width);
                        obstaclImageView.setY(i * Images.width);
                        obstaclImageView.setX(j * Images.height);
                        root.getChildren().add(obstaclImageView);
                    }
                } else if (i == 14) {
                    // Logic for generating obstacle images at the bottom row
                    String vocabularyForChecking = i + "-" + j;
                    if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                        myArrayListForCheck.add(vocabularyForChecking);
                        ImageView obstaclImageView = new ImageView(obstaclImages);
                        obstaclImageView.setFitHeight(Images.height);
                        obstaclImageView.setFitWidth(Images.width);
                        obstaclImageView.setY(i * Images.width);
                        obstaclImageView.setX(j * Images.height);
                        root.getChildren().add(obstaclImageView);
                    }
                } else {
                    // Logic for generating various minerals and obstacles
                    count++;
                    if (count < 15) { //my game contains 14 minerals. 
                        ArrayList<String> mArrayList = new ArrayList<>();
                        mArrayList.add("lava");
                        mArrayList.add("obstacle");
                        ArrayList<String> mArrayList2 = new ArrayList<>();
                        mArrayList2.add("gold");
                        mArrayList2.add("diamond");
                        mArrayList2.add("silver");
                        mArrayList2.add("platinum");
                        mArrayList2.add("ironum");
                        Collections.shuffle(mArrayList);
                        Collections.shuffle(mArrayList2);
                        String lucky = mArrayList.get(0);
                        String lucky2 = mArrayList2.get(0);
                        int xaxis = random.nextInt(1, 15);
                        int yaxis = random.nextInt(3, 14);
                        int x2axis = random.nextInt(1, 15);
                        int y2axis = random.nextInt(3, 14);
                        if (lucky.equals("lava")) {
                            String vocabularyForChecking = yaxis + "-" + xaxis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewlava = new ImageView(lavaImages);
                                imageViewlava.setFitHeight(Images.height);
                                imageViewlava.setFitWidth(Images.width);
                                imageViewlava.setX(xaxis * Images.width);
                                imageViewlava.setY(yaxis * Images.height);
                                root.getChildren().add(imageViewlava);
                            }
                        } else if (lucky.equals("obstacle")) {
                            String vocabularyForChecking = yaxis + "-" + xaxis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewobstacle = new ImageView(obstaclImages);
                                imageViewobstacle.setFitHeight(Images.height);
                                imageViewobstacle.setFitWidth(Images.width);
                                imageViewobstacle.setX(xaxis * Images.width);
                                imageViewobstacle.setY(yaxis * Images.height);
                                root.getChildren().add(imageViewobstacle);
                            }
                        }
                        if (lucky2.equals("gold")) {
                            ImageView imageViewgold = new ImageView(goldImages);
                            String vocabularyForChecking = y2axis + "-" + x2axis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                imageViewgold.setFitHeight(Images.height);
                                imageViewgold.setFitWidth(Images.width);
                                imageViewgold.setX(x2axis * Images.width);
                                imageViewgold.setY(y2axis * Images.height);
                                root.getChildren().add(imageViewgold);
                            }
                        } else if (lucky2.equals("diamond")) {
                            String vocabularyForChecking = y2axis + "-" + x2axis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewdiamond = new ImageView(diamondImages);
                                imageViewdiamond.setFitHeight(Images.height);
                                imageViewdiamond.setFitWidth(Images.width);
                                imageViewdiamond.setX(x2axis * Images.width);
                                imageViewdiamond.setY(y2axis * Images.height);
                                root.getChildren().add(imageViewdiamond);
                            }
                        } else if (lucky2.equals("silver")) {
                            String vocabularyForChecking = y2axis + "-" + x2axis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewsilver = new ImageView(silverImages);
                                imageViewsilver.setFitHeight(Images.height);
                                imageViewsilver.setFitWidth(Images.width);
                                imageViewsilver.setX(x2axis * Images.width);
                                imageViewsilver.setY(y2axis * Images.height);
                                root.getChildren().add(imageViewsilver);
                            }
                        } else if (lucky2.equals("platinum")) {
                            String vocabularyForChecking = y2axis + "-" + x2axis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewdplatinum = new ImageView(platınuImages);
                                imageViewdplatinum.setFitHeight(Images.height);
                                imageViewdplatinum.setFitWidth(Images.width);
                                imageViewdplatinum.setX(x2axis * Images.width);
                                imageViewdplatinum.setY(y2axis * Images.height);
                                root.getChildren().add(imageViewdplatinum);
                            }
                        } else if (lucky2.equals("ironum")) {
                            String vocabularyForChecking = y2axis + "-" + x2axis;
                            if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                                myArrayListForCheck.add(vocabularyForChecking);
                                ImageView imageViewironum = new ImageView(ıronıumImages);
                                imageViewironum.setFitHeight(Images.height);
                                imageViewironum.setFitWidth(Images.width);
                                imageViewironum.setX(x2axis * Images.width);
                                imageViewironum.setY(y2axis * Images.height);
                                root.getChildren().add(imageViewironum);
                            }
                        }
                    }
                }
            }
        }
        // Logic for generating soil images
        for (int i = 3; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                if (i != 14 && j != 15) {
                    String vocabularyForChecking = i + "-" + j;
                    if (!myArrayListForCheck.contains(vocabularyForChecking)) {
                        ImageView soilImageView = new ImageView(soilImages);
                        soilImageView.setFitHeight(Images.height);
                        soilImageView.setFitWidth(Images.width);
                        soilImageView.setX(j * Images.width);
                        soilImageView.setY(i * Images.height);
                        root.getChildren().add(soilImageView);
                    }
                }
            }
        }
    }

    /**
     * Handles the movement of the drill ImageView based on keyboard input.
     *
     * @param scene            The Scene object where the movement will be handled.
     * @param drillImageView   The ImageView representing the drill.
     * @param root             The root group of the scene.
     * @param drillImagesDOWN  The image for the drill facing down.
     * @param drillImagesUP    The image for the drill facing up.
     * @param goldImages       The image for gold.
     * @param lavaImages       The image for lava.
     * @param platınuImages    The image for platinum.
     * @param obstaclImages    The image for obstacles.
     * @param diamondImages    The image for diamond.
     * @param ıronıumImages    The image for ironum.
     * @param silverImages     The image for silver.
     * @param drillImagesLEFT  The image for the drill facing left.
     * @param drillImagesRİGHT The image for the drill facing right.
     */
    private void makeMovement(Scene scene, ImageView drillImageView, Group root, Images drillImagesDOWN,
                              Images drillImagesUP, Images goldImages, Images lavaImages,
                              Images platınuImages, Images obstaclImages, Images diamondImages, Images ıronıumImages, Images silverImages,
                              Images drillImagesLEFT, Images drillImagesRİGHT, Drill drill) {
        scene.setOnKeyPressed(event -> {
            double step = 50;
            double nextX = drillImageView.getX();
            double nextY = drillImageView.getY();
            switch (event.getCode()) {
                case UP:
                    if (!isBlockAbove(drillImageView, root)) {
                        nextY -= step;
                        drillImageView.setImage(drillImagesUP);
                    }
                    drill.setFuelLevel(drill.getFuelLevel() - 100);
                    break;
                case DOWN:
                    drillImageView.setImage(drillImagesDOWN);
                    drill.setFuelLevel(drill.getFuelLevel() - 100);
                    removeBlock(root, drill.getDrillImageView(), Direction.DOWN, goldImages, lavaImages, platınuImages,
                            obstaclImages, diamondImages, ıronıumImages, silverImages, drill);
                    nextY += step;
                    break;
                case LEFT:
                    drillImageView.setImage(drillImagesLEFT);
                    drill.setFuelLevel(drill.getFuelLevel() - 100);
                    removeBlock(root, drill.getDrillImageView(), Direction.LEFT, goldImages, lavaImages, platınuImages,
                            obstaclImages, diamondImages, ıronıumImages, silverImages, drill);
                    nextX -= step;
                    break;
                case RIGHT:
                    drillImageView.setImage(drillImagesRİGHT);
                    drill.setFuelLevel(drill.getFuelLevel() - 100);
                    removeBlock(root, drill.getDrillImageView(), Direction.RIGHT, goldImages, lavaImages, platınuImages,
                            obstaclImages, diamondImages, ıronıumImages, silverImages, drill);
                    nextX += step;
                    break;
                default:
                    break;
            }
            if (nextX >= 0 && nextX <= (WIDTH - drillImageView.getFitWidth()) && nextY >= 50
                    && nextY <= (HEIGHT - drillImageView.getFitHeight())) {
                boolean canMove = true;
                for (javafx.scene.Node node : root.getChildren()) {
                    if (node instanceof ImageView && node != drillImageView) {
                        ImageView obstacle = (ImageView) node;
                        if (nextX < obstacle.getX() + obstacle.getFitWidth()
                                && nextX + drillImageView.getFitWidth() > obstacle.getX() && nextY < obstacle.getY() +
                                obstacle.getFitHeight()
                                && nextY + drillImageView.getFitHeight() > obstacle.getY()) {
                            if (obstacle.getImage() == lavaImages) {
                                Platform.runLater(() -> {
                                    root.getChildren().clear();
                                    Rectangle redBackground = new Rectangle(WIDTH, HEIGHT, Color.DARKRED);
                                    root.getChildren().add(redBackground);
                                    Texts gameOverText = new Texts("GAME OVER", 50, 225, 375, Color.WHITE);
                                    root.getChildren().add(gameOverText);
                                });
                                return;
                            }
                            if (obstacle.getImage() == obstaclImages) {
                                canMove = false;
                                break;
                            }
                        }
                    }
                }
                if (canMove) {
                    drillImageView.setX(nextX);
                    drillImageView.setY(nextY);
                }
            }
        });
    }
}