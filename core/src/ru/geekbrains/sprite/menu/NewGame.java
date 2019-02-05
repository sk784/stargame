package ru.geekbrains.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;
import ru.geekbrains.sprite.game.MainShip;

public class NewGame extends ScaledTouchUpButton {

    private MainShip mainShip;



    public NewGame(TextureAtlas atlas,MainShip mainShip) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(0.05f);
        this.mainShip=mainShip;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.3f);
    }

    @Override
    public void action() {
        mainShip.flushDestroy();
        mainShip.hp = 1;
        mainShip.pos.x = 0f;
        }
    }


