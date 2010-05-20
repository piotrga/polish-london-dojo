//
//  BoardMutableModel.m
//  GameOfLife
//
//  Created by Piotr Gabryanczyk on 20/05/2010.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

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

-(BOOL)isAliveAtX:(int)x y:(int)y{
	int address = y*width+x;
	if (address > width * height) @throw @"Invalid address";
	if (address > [cells count]) return FALSE;
	return [cells objectAtIndex:address];
}

@synthesize height;
@synthesize width;

@end