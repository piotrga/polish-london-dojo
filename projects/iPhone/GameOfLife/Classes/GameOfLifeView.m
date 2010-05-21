#import <UIKit/UIKit.h>
#import "GameOfLifeView.h"
#include <stdlib.h>

@implementation BoardView

-(void) initWithModel:(id<BoardModel>)newModel{
	model = newModel;
}

-(void)drawRect:(CGRect)rect
{
	CGContextRef context = UIGraphicsGetCurrentContext();
	CGContextSetRGBStrokeColor(context, 1.0, 0.5, 1.0, 1.0);
	CGContextSetLineWidth(context, 2.0);
	
	for (int y=0; y<model.height; y++) {
		for (int x=0; x<model.width; x++) {
			if ([model isCellAliveAtX:2 y:y]) {
				CGContextMoveToPoint(context, x*2, y*2);
				CGContextAddLineToPoint(context, (x+1)*2, y*2);
				CGContextStrokePath(context);				
			}
		}
	}
}

-(void)boardChanged{
	[self setNeedsDisplay];
}

@end

