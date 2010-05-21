#import <UIKit/UIKit.h>

@protocol BoardModelListener
	-(void) boardChanged;
@end

@protocol BoardModel
	-(BOOL)isCellAliveAtX:(int)x y:(int)y;

	@property (readonly) int width;
	@property (readonly) int height;
@end

@interface BoardMutableModel : NSObject<BoardModel>{
	NSMutableArray *cells;
	int width;
	int height;
	id<BoardModelListener> listener;
} 

	-(id) initWithWidth:(int)newWidth height:(int)newHeight;
	-(void) swapBoardData:(NSMutableArray*) data;
	+(NSMutableArray*) emptyModelDataForWidth:(int)w height:(int)h;
	@property (retain)id<BoardModelListener> listener;
@end