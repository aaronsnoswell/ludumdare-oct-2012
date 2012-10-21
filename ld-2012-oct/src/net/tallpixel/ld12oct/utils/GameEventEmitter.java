package net.tallpixel.ld12oct.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.utils.Array;

public class GameEventEmitter {
	
	static HashMap<GameEvent, Array<GameEventListener>> events;
	
	public static GameEventListener addEventListener(String name, GameEventListener listener) {
		if(events == null) events = new HashMap<GameEvent, Array<GameEventListener>>();
		
		GameEvent ev = new GameEvent(name);
		if(!events.containsKey(ev)) {
			events.put(ev, new Array<GameEventListener>());
		}
		
		events.get(ev).add(listener);
		return listener;
	}
	
	public static void trigger(String name) {
		if(events == null) events = new HashMap<GameEvent, Array<GameEventListener>>();
		
		GameEvent ev = new GameEvent(name);
		if(events.containsKey(ev)) {
			for(GameEventListener l : events.get(ev)) {
				l.run();
			}
		}
	}
	
	public static void removeEventListener(String name, GameEventListener listener) {
		if(events == null) events = new HashMap<GameEvent, Array<GameEventListener>>();
		
		GameEvent ev = new GameEvent(name);
		if(events.containsKey(ev)) {
			Iterator<GameEventListener> iter = events.get(ev).iterator();
			
			while(iter.hasNext()) {
				GameEventListener l = iter.next();
				if(l.equals(listener)) {
					iter.remove();
				}
			}
		}
	}
	
	public static void removeEventListeners(String name) {
		if(events == null) events = new HashMap<GameEvent, Array<GameEventListener>>();
		
		GameEvent ev = new GameEvent(name);
		if(events.containsKey(ev)) {
			events.put(ev, new Array<GameEventListener>());
		}
	}
	
}
