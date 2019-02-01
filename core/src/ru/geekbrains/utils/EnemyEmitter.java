package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.game.Enemy;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;
    private Vector2 enemySmallV = new Vector2(0, -0.2f);
    private TextureRegion[] enemySmallRegion;

    private static final float ENEMY_MIDDLE_HEIGHT = 0.15f;
    private static final float ENEMY_MIDDLE_BULLET_HEIGHT = 0.015f;
    private static final float ENEMY_MIDDLE_BULLET_VY = -0.3f;
    private static final int ENEMY_MIDDLE_DAMAGE = 2;
    private static final float ENEMY_MIDDLE_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_MIDDLE_HP = 2;
    private Vector2 enemyMiddleV = new Vector2(0, -0.3f);
    private TextureRegion[] enemyMiddleRegion;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_DAMAGE = 4;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_BIG_HP = 4;
    private Vector2 enemyBigV = new Vector2(0, -0.4f);
    private TextureRegion[] enemyBigRegion;


    private TextureRegion bulletRegion;

    private float generateInterval = 4f;
    private float generateTimer;

    private EnemyPool enemyPool;

    private Rect worldBounds;



    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {
        this.enemyPool = enemyPool;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1,2,2);

        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMiddleRegion = Regions.split(textureRegion1, 1,2,2);

        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1,2,2);

        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.worldBounds = worldBounds;
    }



    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;

            Random rand = new Random();
            int num = rand.nextInt(3);

            System.out.println(num);

            Enemy enemy = enemyPool.obtain();

            switch (num){
                case 0: enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        worldBounds,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP
                );
                    System.out.println("Вылетает маленький корабль");
                case 1: enemy.set(
                        enemyMiddleRegion,
                        enemyMiddleV,
                        worldBounds,
                        bulletRegion,
                        ENEMY_MIDDLE_BULLET_HEIGHT,
                        ENEMY_MIDDLE_BULLET_VY,
                        ENEMY_MIDDLE_DAMAGE,
                        ENEMY_MIDDLE_RELOAD_INTERVAL,
                        ENEMY_MIDDLE_HEIGHT,
                        ENEMY_MIDDLE_HP
                );
                    System.out.println("Вылетает средний корабль");
                case 2: enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        worldBounds,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP
                );
                    System.out.println("Вылетает большой корабль");
            }

            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }
}
