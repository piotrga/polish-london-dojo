#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "GameOfLife.h"


@interface BoardView : UIView<BoardModelListener> {
	id<BoardModel> model;
}
-(void) initWithModel:(id<BoardModel>)model;
@end
