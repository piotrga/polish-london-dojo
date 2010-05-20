#import <SenTestingKit/SenTestingKit.h>
#import <UIKit/UIKit.h>
#import "GameOfLife.h"
#import "ModelTest.h"

@implementation ModelTest

- (void) testMutableModelReturnsFalseForNotSetCells {
    id<BoardModel> model = [[BoardMutableModel alloc] initWithWidth:10 height:10]; 
	STAssertFalse( [model isAliveAtX:1 y:1], @"");
    STAssertTrue((1+1)==2, @"Compiler isn't feeling well today :-(" );
    
}


@end
