#import "GameOfLifeAppDelegate.h"
#import "GameOfLifeView.h"

@interface BoardRandomModel : NSObject<BoardModel>{
} @end


@implementation GameOfLifeAppDelegate

@synthesize window;
@synthesize boardView;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    

	[boardView initWithModel: [[BoardRandomModel alloc] init]];
    [window makeKeyAndVisible];
	
	return YES;
}


- (void)dealloc {
    [window release];
    [super dealloc];
}


@end


@implementation BoardRandomModel

-(int)height{ return 20;}
-(int)width{ return 150;}

-(BOOL)isAliveAtX:(int)x y:(int)y{
	return random() %4 == 1;
}

@end

