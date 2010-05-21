#import "GameOfLife.h"
#import <UIKit/UIKit.h>

@implementation BoardMutableModel

@synthesize listener;

+(NSMutableArray*) randomModelDataForWidth:(int)w height:(int)h{
	NSMutableArray *data = [[NSMutableArray alloc] initWithCapacity:w*h];
	for (int i = 0; i<w*h; i++) {
		if((random() % 2) == 1) {
			[data addObject:@"FALSE"];
		}
		else {
			[data addObject:@"TRUE"];
		}
		
	}
	return data;
}

-(id) initWithWidth:(int)newWidth height:(int)newHeight{
	self = [super init];
	width = newWidth;
	height = newHeight;
	cells = [BoardMutableModel randomModelDataForWidth: newWidth height: newHeight];
	return self;
}

-(BOOL)isCellAliveAtX:(int)x y:(int)y{
	x = (x+width)%width;
	y = (y+height)%height;
	int address = y*width+x;
	if (address > width * height) @throw @"Invalid address";
	if (address > [cells count]) return FALSE;
	return [cells objectAtIndex:address] == @"TRUE";
}

@synthesize height;
@synthesize width;

-(void) swapBoardData:(NSMutableArray*) data{
	if ([data count] != width * height) @throw @"Invalid data size";
	[cells release];
	cells = data;
	[listener boardChanged];
}

+(NSMutableArray*) emptyModelDataForWidth:(int)w height:(int)h{
	NSMutableArray *data = [[NSMutableArray alloc] initWithCapacity:w*h];
	for (int i = 0; i<w*h; i++) {
		[data addObject:@"FALSE"];
	}
	return data;
}




//-(void)setListener: l{
//	listenr = l;
//}

@end