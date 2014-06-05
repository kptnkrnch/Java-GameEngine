package graphics;

import java.awt.Rectangle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Tile;
import engine.World;
import entities.Camera;
import entities.Dialog;
import entities.Player;
import exceptions.CameraNotFoundException;

public class GraphicsController {
	
	public static Color DEFAULT_COLOR = Color.white;
	public static int VIEWPORT_X = 0;
	public static int VIEWPORT_Y = 0;
	public static Rectangle VIEWPORT_BOX = new Rectangle(VIEWPORT_X, VIEWPORT_Y, Main.ResX, Main.ResY);
	
	public static void RenderWorld(World world, Graphics g) {
		
		try {
			HandleCamera(world, g);
			
			HandleTileAnimations(world, g);
			TileCollisionDebug(world, g);
			
			HandleEntityAnimations(world, g);
			EntityCollisionDebug(world, g);
			
			GUIController.DrawMenus(world, g);
			
			DrawDialogText(world, g);
			
		} catch (CameraNotFoundException e) {
			System.err.println("Error! Camera entity does not exist!");
		}
	}
	
	private static void HandleCamera(World world, Graphics g) throws CameraNotFoundException {
		int cameraID = world.FindCamera();
		Entity camera = world.GetEntity(cameraID);
		
		int world_width = world.width * world.tilesize;
		int world_height = world.height * world.tilesize;
		
		if (Main.ResX < world_width) {
			if (Camera.IsMoveableX(world)) {
				g.translate(-(camera.x - Main.ResX / 2), 0);
				VIEWPORT_X = (camera.x - Main.ResX / 2);
			} else if (camera.x > world.width * world.tilesize / 2) {
				g.translate(-(world.width * world.tilesize - Main.ResX), 0);
			}
		} else {
			g.translate(((Main.ResX - world_width) / 2), 0);
		}
		
		if (Main.ResY < world_height) {
			if (Camera.IsMoveableY(world)) {
				g.translate(0, -(camera.y - Main.ResY / 2));
				VIEWPORT_Y = (camera.y - Main.ResY / 2);
			} else if (camera.y > world.height * world.tilesize / 2) {
				g.translate(0, -(world.height * world.tilesize - Main.ResY));
			}
		} else {
			g.translate(0, ((Main.ResY - world_height) / 2));
		}
		
		VIEWPORT_BOX.x = VIEWPORT_X;
		VIEWPORT_BOX.y = VIEWPORT_Y;
	}
	
	private static void HandleTileAnimations(World world, Graphics g) {
		for (int y = 0; y < world.height; y++) {
			for (int x = 0; x < world.width; x++) {
				Tile temp = world.GetTile(x, y);
				if (VIEWPORT_BOX.intersects(temp.collision_box)) {
					g.drawAnimation(world.tile_dictionary.GetImage(temp.type), temp.x, temp.y);
					if (Main.debug_mode) {
						if (temp.IsSolid()) {
							g.drawRect(temp.collision_box.x, temp.collision_box.y, 
									temp.collision_box.width, temp.collision_box.height);
						}
					}
				}
			}
		}
	}
	
	private static void HandleEntityAnimations(World world, Graphics g) {
		/*NOTE: Animation function StopAt() does not seem to work.
		 * -- it cannot be restarted! --*/
		for (int i = 0; i < world.GetEntityCount(); i++) {
			Entity temp = world.GetEntity(i);
			if (VIEWPORT_BOX.intersects(temp.collision_box)) {
				if (temp.type != EntityDictionary.CAMERA) {
					if (temp.animation == null) {
						switch(temp.last_animation) {
						case Direction.LEFT:
							if (temp.last_direction == Direction.NONE) {
								temp.left_anim.stop();
								temp.left_anim.setCurrentFrame(0);
							} else if (temp.left_anim.isStopped() && !temp.IsAnimating()) {
								temp.left_anim.start();
							}
							g.drawAnimation(temp.left_anim, temp.x, temp.y);
							break;
						case Direction.RIGHT:
							if (temp.last_direction == Direction.NONE) {
								temp.right_anim.stop();
								temp.right_anim.setCurrentFrame(0);
							} else if (temp.right_anim.isStopped() && !temp.IsAnimating()) {
								temp.right_anim.start();
							}
							if (!temp.attack.isStopped()) {
								g.drawAnimation(temp.attack, temp.x + 32, temp.y + 12);
							}
							if (temp.attacking) {
								if (temp.attack.isStopped()) {
									temp.attack.start();
									temp.attack.setLooping(false);
								}
								if (temp.attack.getFrame() == temp.attack.getFrameCount() - 1) {
									temp.attack.stop();
									temp.attack.setCurrentFrame(0);
									temp.attacking = false;
								}
							}
							g.drawAnimation(temp.right_anim, temp.x, temp.y);
							break;
						case Direction.UP:
							if (temp.last_direction == Direction.NONE) {
								temp.up_anim.stop();
								temp.up_anim.setCurrentFrame(0);
							} else if (temp.up_anim.isStopped() && !temp.IsAnimating()) {
								temp.up_anim.start();
							}
							g.drawAnimation(temp.up_anim, temp.x, temp.y);
							break;
						case Direction.DOWN:
							if (temp.last_direction == Direction.NONE) {
								temp.down_anim.stop();
								temp.down_anim.setCurrentFrame(0);
							} else if (temp.down_anim.isStopped() && !temp.IsAnimating()) {
								temp.down_anim.start();
							}
							g.drawAnimation(temp.down_anim, temp.x, temp.y);
							break;
						default:
							temp.down_anim.stop();
							temp.down_anim.setCurrentFrame(0);
							g.drawAnimation(temp.down_anim, temp.x, temp.y);
							break;	
						}
					} else {
						Animation animation = temp.animation.get(temp.last_animation_name);

						switch(temp.last_animation_name) {
						case AnimationController.LEFT:
							if (!temp.attacking) {
								if (temp.last_direction == Direction.NONE) {
									animation.stop();
									animation.setCurrentFrame(0);
								} else if (animation.isStopped() && !temp.IsAnimating()) {
									animation.start();
								}
								//g.drawAnimation(animation, temp.x, temp.y);
								if (temp.IsHit()) {
									animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
								} else {
									animation.draw(temp.x, temp.y);
								}
								DrawDamage(temp, g);
							} else {
								temp = HandleActionAnimation(world, g, temp);
							}
							break;
						case AnimationController.RIGHT:
							if (!temp.attacking) {
								if (temp.last_direction == Direction.NONE) {
									animation.stop();
									animation.setCurrentFrame(0);
								} else if (animation.isStopped() && !temp.IsAnimating()) {
									animation.start();
								}
								//g.drawAnimation(animation, temp.x, temp.y);
								if (temp.IsHit()) {
									animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
								} else {
									animation.draw(temp.x, temp.y);
								}
								DrawDamage(temp, g);
							} else {
								temp = HandleActionAnimation(world, g, temp);
							}
							break;
						case AnimationController.UP:
							if (!temp.attacking) {
								if (temp.last_direction == Direction.NONE) {
									animation.stop();
									animation.setCurrentFrame(0);
								} else if (animation.isStopped() && !temp.IsAnimating()) {
									animation.start();
								}
								//g.drawAnimation(animation, temp.x, temp.y);
								if (temp.IsHit()) {
									animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
								} else {
									animation.draw(temp.x, temp.y);
								}
								DrawDamage(temp, g);
							} else {
								temp = HandleActionAnimation(world, g, temp);
							}
							break;
						case AnimationController.DOWN:
							if (!temp.attacking) {
								if (temp.last_direction == Direction.NONE) {
									animation.stop();
									animation.setCurrentFrame(0);
								} else if (animation.isStopped() && !temp.IsAnimating()) {
									animation.start();
								}
								//g.drawAnimation(animation, temp.x, temp.y);
								if (temp.IsHit()) {
									animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
								} else {
									animation.draw(temp.x, temp.y);
								}
								DrawDamage(temp, g);
							} else {
								temp = HandleActionAnimation(world, g, temp);
							}
							break;
						default:
							animation.stop();
							animation.setCurrentFrame(0);
							//g.drawAnimation(animation, temp.x, temp.y);
							if (temp.IsHit()) {
								animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
							} else {
								animation.draw(temp.x, temp.y);
							}
							DrawDamage(temp, g);
							break;
						}
					}
				}
			}

			world.entities.set(i, temp);
		}
	}
	
	private static void TileCollisionDebug(World world, Graphics g) {
		if (Main.debug_mode) {
			for (int y = 0; y < world.height; y++) {
				for (int x = 0; x < world.width; x++) {
					Tile temp = world.GetTile(x, y);
					if (VIEWPORT_BOX.intersects(temp.collision_box)) {
						if (temp.IsSolid()) {
							g.drawRect(temp.collision_box.x, temp.collision_box.y, 
									temp.collision_box.width, temp.collision_box.height);
						}
					}
				}
			}
		}
	}
	
	private static void EntityCollisionDebug(World world, Graphics g) {
		if (Main.debug_mode) {
			for (int i = 0; i < world.GetEntityCount(); i++) {
				Entity temp = world.GetEntity(i);
				if (VIEWPORT_BOX.intersects(temp.collision_box)) {
					if (temp.type != EntityDictionary.CAMERA) {
						if (temp.IsSolid()) {
							g.drawRect(temp.collision_box.x, temp.collision_box.y, 
									temp.collision_box.width, temp.collision_box.height);
						}
					}
				}
			}
		}
	}
	
	private static void DrawDialogText(World world, Graphics g) {
		for (int i = 0; i < world.entities.size(); i++) {
			Entity temp = world.GetEntity(i);
			if (temp.type == EntityDictionary.DIALOG_BOX) {
				g.drawString(Dialog.text.get(Dialog.dialog_pos), temp.x + 10, temp.y + 10);
			}
		}
	}
	
	private static Entity HandleActionAnimation(World world, Graphics g, Entity temp) {
		Animation animation = null;
		switch(temp.type) {
		case EntityDictionary.PLAYER:
			animation = temp.animation.get(AnimationController.SHOOT + temp.last_animation_name);
			animation.setLooping(false);
			if (animation != null) {
				if (animation.isStopped() && temp.attacking && animation.getFrame() == 0) {
					animation.restart();
				}
				if (animation.isStopped()) {
					temp.attacking = false;
					animation.setCurrentFrame(0);
					Player.ShootBullet(world, temp);
				}
				//g.drawAnimation(animation, temp.x, temp.y);
				if (temp.IsHit()) {
					animation.drawFlash(temp.x, temp.y, animation.getWidth(), animation.getHeight(), Color.transparent);
				} else {
					animation.draw(temp.x, temp.y);
				}
				DrawDamage(temp, g);
			}
			break;
		}
		return temp;
	}
	
	public static void DrawDamage(Entity temp, Graphics g) {
		if (temp.HasLastHitText()) {
			g.setColor(temp.last_hit.text_color);
			g.drawString(temp.last_hit.text, 
					temp.collision_box.x + temp.collision_box.width, 
					temp.collision_box.y - 10);
			g.setColor(DEFAULT_COLOR);
		}
	}
	
}
