#import "DockUpgrade.h"

@class DockEquippedShip;

@interface DockUpgrade (Addons)
+(DockUpgrade*)placeholder:(NSString*)upType inContext:(NSManagedObjectContext*)context;
-(BOOL)isTalent;
-(BOOL)isCrew;
-(BOOL)isWeapon;
-(BOOL)isCaptain;
-(BOOL)isPlaceholder;
-(NSComparisonResult)compareTo:(DockUpgrade*)other;
-(int)limitForShip:(DockEquippedShip*)targetShip;
-(NSAttributedString*)styledDescription;
-(NSString*)targetShipClass;
@end