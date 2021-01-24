import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The class defines a paddle object.
 *
 * @author 323535419
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private double leftBorder;
    private double rightBorder;

    /**
     * Constructor function.
     *
     * @param keyboard The keyboard sensor
     * @param rect The paddle's rectangle
     * @param leftBorder The paddle's left border
     * @param rightBorder The paddle's right border
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect, double leftBorder, double rightBorder) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    /**
     * The function moves the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        if ((x - 5) <= this.leftBorder) {
            this.rect = new Rectangle(new Point(this.leftBorder, y), this.rect.getWidth(), this.rect.getHeight());
        } else {
            this.rect = new Rectangle(new Point(x - 5, y), this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * The function moves the paddle to the right.
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        double tempX = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        double y = this.rect.getUpperLeft().getY();
        if ((tempX + 5) >= this.rightBorder) {
            this.rect = new Rectangle(new Point(this.rightBorder - this.rect.getWidth(), y),
                    this.rect.getWidth(), this.rect.getHeight());
        } else {
            this.rect = new Rectangle(new Point(x + 5, y), this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * The function checks if the left or right keys are pressed, if they are it moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * The function draws the paddle on a given surface.
     *
     * @param surface The given surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.darkGray);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * The function returns the collision rectangle of the paddle.
     *
     * @return Collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * The function changes the velocity according to a given collision point.
     *
     * @param collisionPoint  The given collision point
     * @param currentVelocity The given velocity
     * @return The new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        Point lowerLeftPoint = new Point(x, y + this.rect.getHeight());
        Point upperRightPoint = new Point(x + this.rect.getWidth(), y);
        Point lowerRightPoint = new Point(x + this.rect.getWidth(), y + this.rect.getHeight());
        Line leftBorderLine = new Line(this.rect.getUpperLeft(), lowerLeftPoint);
        Line rightBorderLine = new Line(upperRightPoint, lowerRightPoint);
        Line upperBorderLine = new Line(this.rect.getUpperLeft(), upperRightPoint);
        Line lowerBorderLine = new Line(lowerLeftPoint, lowerRightPoint);
        if (lowerBorderLine.pointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        if (lowerBorderLine.pointOnLine(collisionPoint) || upperBorderLine.pointOnLine(collisionPoint)) {
            if (leftBorderLine.pointOnLine(collisionPoint) || rightBorderLine.pointOnLine(collisionPoint)) {
                return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            double distance = this.rect.getUpperLeft().distance(collisionPoint);
            double lengthOfPart = this.rect.getWidth() / 4;
            int region = (int) Math.round(lengthOfPart / distance) + 1;
            // Check which region of the paddle was hit
            switch (region) {
                case 1:
                    return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                case 2:
                    return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                case 3:
                    return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
                case 4:
                    return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                case 5:
                    return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                default:
                    return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
        } else if (leftBorderLine.pointOnLine(collisionPoint) || rightBorderLine.pointOnLine(collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    /**
     * The function adds the block to the game.
     *
     * @param game The given game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}