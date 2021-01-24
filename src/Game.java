import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * The class defines a Game object.
 *
 * @author 323535419
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;

    /**
     * Constructor function.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();
    }

    /**
     * The function adds a new collidable to the game.
     *
     * @param c A new collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function removes a collidable from the game.
     *
     * @param c The collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The function adds a new sprite to the game.
     *
     * @param s A new sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The function removes a sprite from the game.
     *
     * @param s The sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This function initializes the game: creates balls, blocks and a paddle and adds them to the game.
     */
    public void initialize() {
        // First ball
        Ball myBall = new Ball(new Point(200, 370), 5, java.awt.Color.BLACK, this.environment);
        myBall.setVelocity(4, 6);
        myBall.addToGame(this);
        ballCounter.increase(1);
        // Second ball
        Ball mySecondBall = new Ball(new Point(350, 200), 5, java.awt.Color.BLUE, this.environment);
        mySecondBall.setVelocity(6, 4);
        mySecondBall.addToGame(this);
        ballCounter.increase(1);
        // Bottom boundary
        Rectangle bottomRectangle = new Rectangle(new Point(0, 580), 800, 20);
        // Top boundary
        Rectangle topRectangle = new Rectangle(new Point(0, 0), 800, 20);
        // Left boundary
        Rectangle leftRectangle = new Rectangle(new Point(0, 0), 20, 600);
        // Right boundary
        Rectangle rightRectangle = new Rectangle(new Point(780, 0), 20, 600);
        // Paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Rectangle(new Point(400, 550), 100, 20),
                leftRectangle.getUpperLeft().getX() + leftRectangle.getWidth(),
                rightRectangle.getUpperLeft().getX());
        paddle.addToGame(this);
        // Boundary blocks
        // Bottom block is also the death block
        Block deathBlock = new Block(bottomRectangle, Color.GRAY);
        deathBlock.addToGame(this);
        Block leftBlock = new Block(leftRectangle, Color.GRAY);
        leftBlock.addToGame(this);
        Block rightBlock = new Block(rightRectangle, Color.GRAY);
        rightBlock.addToGame(this);
        Block topBlock = new Block(topRectangle, Color.GRAY);
        topBlock.addToGame(this);
        // Listeners
        BallRemover ballRemover = new BallRemover(this, this.ballCounter, deathBlock);
        deathBlock.addHitListener(ballRemover);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        // Score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        addSprite(scoreIndicator);
        // Regular blocks
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            for (int j = 0; j < 15 - i * 2; j++) {
                Rectangle rectangle = new Rectangle(new Point(80 + j * 45 + i * 90,
                        100 + i * 25), 45, 25);
                Block block = new Block(rectangle, new Color(r, g, b));
                block.addToGame(this);
                if (i == 0) {
                    block.setHits(2);
                } else {
                    block.setHits(1);
                }
                block.addHitListener(scoreTrackingListener);
                block.addHitListener(blockRemover);
                blockCounter.increase(1);
            }
        }
    }

    /**
     * The function starts the animation loop to run the game.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 30;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            d.setColor(Color.PINK);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blockCounter.getValue() == 0) {
                scoreCounter.increase(100);
            }
            if (ballCounter.getValue() == 0) {
                this.gui.close();
                return;
            }
        }
    }
}