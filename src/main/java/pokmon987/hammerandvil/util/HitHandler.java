package pokmon987.hammerandvil.util;

public class HitHandler {
	/**
	 * This needs to get the hits from the stack, but there aren't any stacks in here
	 * 
	 */
	private Float hits = 0.0F;
	
	public HitHandler() {
		
	}
	
	public Float setCurrentHits(Float hitFloat) {
		return this.hits = hitFloat;
	}
	
	public Float getCurrentHits() {
		return this.hits;
	}
	
	public Float increaseHitUntilPoint(Float maxFloat) {
		if (this.hits < maxFloat) {
			return this.increaseHit();
		}
		return this.hits;
	}
	
	public Float increaseHit() {
		return this.hits++;
	}
	
	public Float resetHits() {
		return this.hits = 0.0F;
	}
}