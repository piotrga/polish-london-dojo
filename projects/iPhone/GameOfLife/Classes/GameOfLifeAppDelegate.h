#import <UIKit/UIKit.h>
#import "GameOfLifeView.h"

@interface GameOfLifeAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
	BoardView *boardView;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet BoardView *boardView;

@end

