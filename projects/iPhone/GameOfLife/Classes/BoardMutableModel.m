#import "GameOfLife.h"
#import <UIKit/UIKit.h>

@implementation BoardMutableModel
-(id) initWithWidth:(int)newWidth height:(int)newHeight{
	self = [super init];
	width = newWidth;
	height = newHeight;
	cells = [[NSMutableArray alloc] initWithCapacity:width*height];
	return self;
}

-(BOOL)isCellAliveAtX:(int)x y:(int)y{
	int address = y*width+x;
	if (address > width * height) @throw @"Invalid address";
	if (address > [cells count]) return FALSE;
	return [cells objectAtIndex:address] == @"TRUE";
}

@synthesize height;
@synthesize width;

-(void) swapBoardData:(NSMutableArray*) data{
	[cells release];
	cells = data;
}

+(NSMutableArray*) emptyModelDataForWidth:(int)w height:(int)h{
	NSMutableArray *data = [[NSMutableArray alloc] initWithCapacity:w*h];
	for (int i = 0; i<w*h; i++) {
		[data addObject:@"FALSE"];
	}
	return data;
}

@end