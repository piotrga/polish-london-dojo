#import <UIKit/UIKit.h>

@protocol BoardModelListener
	-(void) boardChanged;
@end

@protocol BoardModel
	-(BOOL)isAliveAtX:(int)x y:(int)y;

	@property (readonly) int width;
	@property (readonly) int height;
@end

@interface BoardMutableModel : NSObject<BoardModel>{
	NSMutableArray *cells;
	int width;
	int height;
} 

	-(id) initWithWidth:(int)newWidth height:(int)newHeight;
@end