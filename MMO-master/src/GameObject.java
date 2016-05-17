import java.awt.Graphics;


public abstract class GameObject {
	protected int x, y;
	protected ID id;
	public int motionX, motionY;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	
	public void setX(int x ){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setmotionX(int motionX){
		this.motionX = motionX;
	}
	public void setmotionY(int motionY){
		this.motionY = motionY;
	}
	public int getmotionX(){
		return motionX;
	}
	public int getmotionY(){
		return motionY;
	}

	public void render(Graphics g) {
		
		
	}
	
	
}