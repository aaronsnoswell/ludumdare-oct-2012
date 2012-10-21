package net.tallpixel.ld12oct.utils;

public class GameEvent {
	String name;
	
	public GameEvent(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj.getClass() != this.getClass()) return false;
		return name.equals(((GameEvent) obj).name);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
}
