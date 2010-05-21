#import <SenTestingKit/SenTestingKit.h>
#import <UIKit/UIKit.h>
#import "GameOfLife.h"

@interface ModelTest : SenTestCase{
    BoardMutableModel *model; 
} 
@end

@implementation ModelTest

- (void) setUp{
	model = [[BoardMutableModel alloc] initWithWidth:10 height:10]; 
}

- (void) testMutableModelReturnsFalseForNotSetCells {
	STAssertFalse( [model isCellAliveAtX:1 y:1], @"");    
}

- (void) testSwappingDataWorks{
	NSMutableArray *data = [BoardMutableModel emptyModelDataForWidth:10 height:10];
	[data replaceObjectAtIndex:1 withObject:@"TRUE"];
	[model swapBoardData: data];
	STAssertTrue([model isCellAliveAtX:1 y:0], @"");
}

- (void) tearDown{
//	[model dealloc];
}

@end
