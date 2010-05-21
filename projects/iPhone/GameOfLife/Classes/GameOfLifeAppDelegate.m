#import "GameOfLifeAppDelegate.h"
#import "GameOfLifeView.h"

@interface BoardRandomModel : NSObject<BoardModel>{
} @end


@implementation GameOfLifeAppDelegate

@synthesize window;
@synthesize boardView;

- (IBAction) nextTurn:(id)sender{
	NSMutableArray *data = [BoardMutableModel emptyModelDataForWidth:100 height:100];
	for (int i=0; i<100; ++i) {
		for(int j=0; j<100; ++j) {
			int neighbours = 0;
			if([model isCellAliveAtX:i-1 y:j-1]) { neighbours += 1; }
			if([model isCellAliveAtX:i-1 y:j]) { neighbours += 1; }
			if([model isCellAliveAtX:i-1 y:j+1]) { neighbours += 1; }
				if([model isCellAliveAtX:i y:j-1]) { neighbours += 1; }
						if([model isCellAliveAtX:i y:j+1]) { neighbours += 1; }
							if([model isCellAliveAtX:i+1 y:j-1]) { neighbours += 1; }
								if([model isCellAliveAtX:i+1 y:j]) { neighbours += 1; }
									if([model isCellAliveAtX:i+1 y:j-1]) { neighbours += 1; }
			if (neighbours == 2 || neighbours == 3) {
				if ([model isCellAliveAtX:i y:j]) {
					[data replaceObjectAtIndex:j*100+i withObject:@"TRUE"];
				}
			}
			if (neighbours < 2 || neighbours > 3 ) {
					[data replaceObjectAtIndex:j*100+i withObject:@"FALSE"];
			}
			
			

		}
	}
	
	/*
	For a space that is 'populated':
	- Each cell with one or no neighbors dies, as if by loneliness.
		- Each cell with four or more neighbors dies, as if by overpopulation.
			- Each cell with two or three neighbors survives.
			
			For a space that is 'empty' or 'unpopulated':
			- Each cell with three neighbors becomes populated.
	*/
	//[data replaceObjectAtIndex:55 withObject:@"TRUE"];
	//[data replaceObjectAtIndex:54 withObject:@"TRUE"];
	//[data replaceObjectAtIndex:53 withObject:@"TRUE"];
	//[data replaceObjectAtIndex:52 withObject:@"TRUE"];
	//[data replaceObjectAtIndex:51 withObject:@"TRUE"];
	//[data replaceObjectAtIndex:55 withObject:@"TRUE"];
	
	[model swapBoardData:data];
}


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    

//	NSMutableArray *data = [BoardMutableModel emptyModelDataForWidth:10 height:10];
//	[data replaceObjectAtIndex:55 withObject:@"TRUE"];
//	BoardMutableModel* model = [[BoardMutableModel alloc] initWithWidth:10 height:10];
//	[model swapBoardData:data];
	model = [[BoardMutableModel alloc] initWithWidth:100 height:100];
	
	[boardView initWithModel: model];
	model.listener = boardView;
    [window makeKeyAndVisible];
	
	return YES;
}

- (void)dealloc {
    [window release];
    [super dealloc];
}
@end


@implementation BoardRandomModel

-(int)height{ return 200;}
-(int)width{ return 150;}

-(BOOL)isCellAliveAtX:(int)x y:(int)y{
	return random() %4 == 1;
}

@end

