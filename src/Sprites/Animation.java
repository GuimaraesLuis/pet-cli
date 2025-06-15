package Sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private int animationDirection;
    private int totalFrames;
    private boolean stopped;
    private List<Frame> frames = new ArrayList<>();

    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frameDelay = frameDelay;
        setFrames(frames);
        this.stopped = true;
        this.animationDirection = 1;
    }

    public void start() {
        stopped = false;
    }

    public void restart() {
        currentFrame = 0;
        frameCount = 0;
        stopped = false;
    }

    public void update() {
        if (stopped || frames.isEmpty()) return;

        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame += animationDirection;
            if (currentFrame >= totalFrames) {
                currentFrame = 0;
            } else if (currentFrame < 0) {
                currentFrame = totalFrames - 1;
            }
        }
    }

    public BufferedImage getSprite() {
        if (frames.isEmpty()) return null;
        return frames.get(currentFrame).getFrame();
    }

    private static class Frame {
        private final BufferedImage image;
        private final int duration;

        public Frame(BufferedImage image, int duration) {
            this.image = image;
            this.duration = duration;
        }

        public BufferedImage getFrame() {
            return image;
        }
    }

    public void pause() {
        stopped = true;
    }

    public void resume() {
        stopped = false;
    }

    public void stop() {
        stopped = true;
    }

    public void reverse() {
        animationDirection = -animationDirection;
    }

    public void setFrameDelay(int newDelay) {
        this.frameDelay = newDelay;
    }

    public void setFrames(BufferedImage[] newFrames) {
        frames.clear();
        for (BufferedImage f : newFrames) {
            frames.add(new Frame(f, frameDelay));
        }
        totalFrames = frames.size();
        restart();
    }
}
