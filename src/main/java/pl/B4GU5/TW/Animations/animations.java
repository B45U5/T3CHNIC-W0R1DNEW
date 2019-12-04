package pl.B4GU5.TW.Animations;

import de.schlegel11.jfxanimation.JFXAnimationTemplate;
import de.schlegel11.jfxanimation.JFXTemplateBuilder;
import javafx.animation.Interpolator;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.util.Duration;

public class animations {
	
    public static JFXTemplateBuilder<Node> fadeInBTN() {
	 	ColorAdjust color = new ColorAdjust(0, 0, 0, 0); 
	    return JFXAnimationTemplate.create()
	        .from()
	        // One execution for init behaviour.
	        .action(
	            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(color)))
	        .percent(1)
	        .action(b -> b.target(color.hueProperty()).endValue(0.00))
	        .percent(99)
	        .action(b -> b.target(color.hueProperty()).endValue(0.60))
	        .to()
	        .action(b -> b.target(color.hueProperty()).endValue(0.60))
	        .config(
	            b -> b.duration(Duration.seconds(0.3)).interpolator(Interpolator.EASE_BOTH));
	 }
    
    public static JFXTemplateBuilder<Node> fadeOutBTN() {
		 	ColorAdjust color = new ColorAdjust(0, 0, 0, 0); 
		    return JFXAnimationTemplate.create()
		        .from()
		        // One execution for init behaviour.
		        .action(
		            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(color)))
		        .percent(1)
		        .action(b -> b.target(color.hueProperty()).endValue(0.60))
		        .percent(99)
		        .action(b -> b.target(color.hueProperty()).endValue(0.00))
		        .to()
		        .action(b -> b.target(color.hueProperty()).endValue(0.00))
		        .config(
		            b -> b.duration(Duration.seconds(0.3)).interpolator(Interpolator.EASE_BOTH));
	 }
	 
    public static JFXTemplateBuilder<Node> fadeInBTNlogin() {
		 	ColorAdjust color = new ColorAdjust(0, 0, 0, 0); 
		    return JFXAnimationTemplate.create()
		        .from()
		        // One execution for init behaviour.
		        .action(
		            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(color)))
		        .percent(1)
		        .action(b -> b.target(color.hueProperty()).endValue(0.00))
		        .percent(99)
		        .action(b -> b.target(color.hueProperty()).endValue(-0.40))
		        .to()
		        .action(b -> b.target(color.hueProperty()).endValue(-0.40))
		        .config(
		            b -> b.duration(Duration.seconds(0.3)).interpolator(Interpolator.EASE_BOTH));
	}
	 
    public static JFXTemplateBuilder<Node> fadeOutBTNlogin() {
		ColorAdjust color = new ColorAdjust(0, 0, 0, 0); 
		return JFXAnimationTemplate.create()
	        .from()
	        // One execution for init behaviour.
	        .action(
	            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(color)))
	        .percent(1)
	        .action(b -> b.target(color.hueProperty()).endValue(-0.40))
	        .percent(99)
	        .action(b -> b.target(color.hueProperty()).endValue(0.00))
	        .to()
	        .action(b -> b.target(color.hueProperty()).endValue(0.00))
	        .config(
	            b -> b.duration(Duration.seconds(0.3)).interpolator(Interpolator.EASE_BOTH));
	}
    
    public static JFXTemplateBuilder<Node> blur() {
		GaussianBlur blur = new GaussianBlur(0); 
		return JFXAnimationTemplate.create()
	        .from()
	        // One execution for init behaviour.
	        .action(
	            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(blur)))
	        .percent(1)
	        .action(b -> b.target(blur.radiusProperty()).endValue(0))
	        .percent(99)
	        .action(b -> b.target(blur.radiusProperty()).endValue(8))
	        .to()
	        .action(b -> b.target(blur.radiusProperty()).endValue(8))
	        .config(
	            b -> b.duration(Duration.seconds(1.2)).interpolator(Interpolator.EASE_BOTH));
	}
    public static JFXTemplateBuilder<Node> unBlur() {
		GaussianBlur blur = new GaussianBlur(0); 
		return JFXAnimationTemplate.create()
	        .from()
	        // One execution for init behaviour.
	        .action(
	            b -> b.executions(1).onFinish((node, actionEvent) -> node.setEffect(blur)))
	        .percent(1)
	        .action(b -> b.target(blur.radiusProperty()).endValue(8))
	        .percent(99)
	        .action(b -> b.target(blur.radiusProperty()).endValue(0))
	        .to()
	        .action(b -> b.target(blur.radiusProperty()).endValue(0))
	        .config(
	            b -> b.duration(Duration.seconds(1.2)).interpolator(Interpolator.EASE_BOTH));
	}
    
    public static JFXTemplateBuilder<Node> leftOpen() {
    	 return JFXAnimationTemplate.create()
         .from()
         .action(b -> b.target(Node::translateXProperty, Node::translateYProperty).endValue(0))
         .percent(0)
         .action(
             b ->
                 b.target(Node::translateXProperty)
                     .endValue(node -> -1.00 * node.getBoundsInParent().getWidth()))
         .percent(60)
         .action(
             b ->
                 b.target(Node::translateXProperty)
                     .endValue(node -> -0.20 * node.getBoundsInParent().getWidth()))
         .percent(75)
         .action(
             b ->
                 b.target(Node::translateXProperty)
                     .endValue(node -> -0.10 * node.getBoundsInParent().getWidth()))
         .percent(85)
         .action(
             b ->
                 b.target(Node::translateXProperty)
                     .endValue(node -> -0.05 * node.getBoundsInParent().getWidth()))
         .percent(99)
         .action(
             b ->
                 b.target(Node::translateXProperty)
                     .endValue(node -> 0.0 * node.getBoundsInParent().getWidth()))
         .to()
         .action(b -> b.target(Node::translateXProperty).endValue(0))
         .config(b -> b.duration(Duration.seconds(1.5)).interpolator(Interpolator.EASE_OUT));
	 }
    public static JFXTemplateBuilder<Node> leftClose() {
   	 return JFXAnimationTemplate.create()
        .from()
        .action(b -> b.target(Node::translateXProperty, Node::translateYProperty).endValue(0))
        .percent(0)
        .action(
            b ->
                b.target(Node::translateXProperty)
                    .endValue(node -> 0 * node.getBoundsInParent().getWidth()))
        .percent(85)
        .action(
            b ->
                b.target(Node::translateXProperty)
                    .endValue(node -> -0.05 * node.getBoundsInParent().getWidth()))
        .percent(75)
        .action(
            b ->
                b.target(Node::translateXProperty)
                    .endValue(node -> -0.10 * node.getBoundsInParent().getWidth()))
        .percent(85)
        .action(
            b ->
                b.target(Node::translateXProperty)
                    .endValue(node -> -0.20 * node.getBoundsInParent().getWidth()))
        .percent(99)
        .action(
            b ->
                b.target(Node::translateXProperty)
                    .endValue(node -> -1 * node.getBoundsInParent().getWidth()))
        .to()
        .action(b -> b.target(Node::translateXProperty).endValue(node -> -1 * node.getBoundsInParent().getWidth()))
        .config(b -> b.duration(Duration.seconds(1.5)).interpolator(Interpolator.EASE_OUT));
	 }

}
