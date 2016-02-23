import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseOutCubicInterpolator;

/**
 * Includes use cases of {@link ObjectVisualizerContext}
 * @author triforce
 *
 */
public class Main {
	public static void main(String[] args) {
		ObjectVisualizerContext context = new ObjectVisualizerContext();
		
		final Rectangle r = new Rectangle();
		context.addObject(r);
		
		TranslateAnimation anim = new TranslateAnimation(100, 100, 100, 380);
		anim.setInterpolator(new EaseOutCubicInterpolator());
		anim.setDuration(500);
		anim.setAnimationType(BounAnimation.ANIMATION_FORWARD_BACKWARD_LOOP);
		
		r.setAnimation(anim);
		r.startAnimation();
//		Thread t = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				r.startAnimation();
//			}
//		});
//		
//		t.start();
		
		
	}
}
