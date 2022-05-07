package main;


import entities.*;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTileCollision(Entity e) {
        // Collision area in world coords.
        int entityLeftWorldX = e.worldX + e.collisionRect.x;
        int entityRightWorldX = e.worldX + e.collisionRect.x + e.collisionRect.width;
        int entityTopWorldY = e.worldY + e.collisionRect.y;
        int entityBottomWorldY = e.worldY + e.collisionRect.y + e.collisionRect.height;

        // Rows and columns of each corner of collision area.
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

    

            entityTopRow = (entityTopWorldY - e.speed) / gp.tileSize;
            tileNum1 = gp.tileManager.map[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileManager.map[entityTopRow][entityRightCol];
            if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) e.upColliding = true;
            else e.upColliding = false;

            entityBottomRow = (entityBottomWorldY + e.speed) / gp.tileSize;
            tileNum1 = gp.tileManager.map[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileManager.map[entityBottomRow][entityRightCol];
            if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) e.downColliding = true;
            else e.downColliding = false;

            entityLeftCol = (entityLeftWorldX - e.speed) / gp.tileSize;
            tileNum1 = gp.tileManager.map[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileManager.map[entityBottomRow][entityLeftCol];
            if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) e.leftColliding = true;
            else e.leftColliding = false;

            entityLeftCol = (entityLeftWorldX + e.speed) / gp.tileSize;
            tileNum1 = gp.tileManager.map[entityTopRow][entityRightCol];
            tileNum2 = gp.tileManager.map[entityBottomRow][entityRightCol];
            if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) e.rightColliding = true;
            else e.rightColliding = false;
        
    }
}
