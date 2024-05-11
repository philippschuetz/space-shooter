import greenfoot.Actor;

import greenfoot.*;

import java.util.List;


public class Enemy extends Actor {
    GreenfootImage image;
    private int speed;
    private int damage;
    private int hp;

    public Enemy(int tier) {
        this.turn(90);
        if (tier == 1) {
            speed = 4;
            damage = 1;
            hp = 6;
            image = new GreenfootImage("images/PNG/Enemies/enemyBlue3.png");
        }
        setImage(image);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int value) {
        this.hp = value;
    }

    private void moveSelf() {
        setLocation(getX() - speed, getY());
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    private void attack() {
        List<Player> ships = getIntersectingObjects(Player.class);
        if (ships.isEmpty()) {
            return;
        }
        Player player = getIntersectingObjects(Player.class).get(0);
        player.setHp(player.getHp() - damage);
        getWorld().removeObject(this);
    }

    @Override
    public void act() {
        moveSelf();
        if (getWorld() != null) {
            attack();
        }
        if (hp == 0) {
            getWorld().removeObject(this);
        }
    }
}