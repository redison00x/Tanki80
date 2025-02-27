import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEADING = 1;
    private enum Heading{
        NORTH(0 * SPRITE_SCALE,0 * SPRITE_SCALE,1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(6 * SPRITE_SCALE,0 * SPRITE_SCALE,1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(4 * SPRITE_SCALE,0 * SPRITE_SCALE,1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE,0 * SPRITE_SCALE,1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
        private int x,y,h,w;
        Heading(int x, int y, int h, int w){
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        protected BufferedImage texture(TextureAtlas atlas){
            return atlas.cut(x,y,w,h);
        }
    }

    private Heading               heading;
    private Map<Heading, Sprite>  spriteMap;
    private float scale;
    private float speed;

    public Player( float x, float y, TextureAtlas atlas, float scale,
                      float speed) {
        super(EntityType.Player, x, y);
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;
        heading = Heading.NORTH;
        for(Heading h : Heading.values()){
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas),SPRITES_PER_HEADING,SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet,scale);
            spriteMap.put(h, sprite);
        }
    }

    @Override
    public void update(Input input) {
        float newX = x;
        float newY = y;

        if(input.getKey(KeyEvent.VK_UP)){
            newY -= speed;
            heading = Heading.NORTH;
        } else if(input.getKey(KeyEvent.VK_DOWN)){
            newY += speed;
            heading = Heading.EAST;
        } else if(input.getKey(KeyEvent.VK_LEFT)){
            newX -= speed;
            heading = Heading.WEST;
        } else if(input.getKey(KeyEvent.VK_RIGHT)){
            newX += speed;
            heading = Heading.SOUTH;
        }

        if(newX<0){
            newX = 0;
        } else if (newX>=Game.WIDTH - SPRITE_SCALE *scale) {
            newX = Game.WIDTH - SPRITE_SCALE *scale;
        }
        if(newY<0){
            newY = 0;
        } else if (newY>=Game.HEIGHT - SPRITE_SCALE *scale) {
            newY = Game.HEIGHT - SPRITE_SCALE *scale;
        }
        x= newX;
        y= newY;

    }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g,x,y);
    }
}
