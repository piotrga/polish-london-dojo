#import <UIKit/UIKit.h>
#import "GameOfLifeView.h"

@interface GameOfLifeAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
	BoardView *boardView;
	BoardMutableModel *model;

}

- (IBAction) nextTurn:(id)sender;

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet BoardView *boardView;

@end

