package pokmon987.hammerandvil.util;

public class HitHandler {
	/**
	 * This needs to get the hits from the stack, but there aren't any stacks in here
	 * 
	 */
	private int hits = 0;
	
	public HitHandler() {
		
	}
	
	public int setCurrentHits(int hit) {
		return this.hits = hit;
	}
	
	public int getCurrentHits() {
		return this.hits;
	}
	
	public int increaseHitUntilPoint(int max) {
		if (this.hits < max) {
			return this.increaseHit();
		}
		return this.hits;
	}
	
	public int increaseHit() {
		return this.hits++;
	}
	
	public int resetHits() {
		return this.hits = 0;
	}
}