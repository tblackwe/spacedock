#import "DockUpgradesViewController.h"

#import "DockEquippedShip+Addons.h"
#import "DockEquippedUpgrade+Addons.h"
#import "DockShip+Addons.h"
#import "DockSquad+Addons.h"
#import "DockUpgrade+Addons.h"
#import "DockUpgradeDetailViewController.h"
#import "DockUtilsMobile.h"

@interface DockUpgradesViewController ()
@property (assign, nonatomic) BOOL wasOverridden;
@property (assign, nonatomic) BOOL overridden;
@property (assign, nonatomic) BOOL restore;
@property (assign, nonatomic) int overrideCost;
@property (nonatomic, strong) IBOutlet UIBarButtonItem* overrideBarItem;
@end

@implementation DockUpgradesViewController

-(void)viewDidLoad
{
    self.cellIdentifer = @"Upgrade";
    [super viewDidLoad];
}


-(NSIndexPath*)indexPathForUpgrade:(DockUpgrade*)upgrade
{
    NSArray* sectionLists = self.sectionLists;
    NSInteger sectionCount = sectionLists.count;
    for (int sectionIndex = 0; sectionIndex < sectionCount; ++sectionIndex) {
        NSArray* sectionObjects = sectionLists[sectionIndex];
        NSInteger sectionObjectCount = sectionObjects.count;
        for (int itemIndex = 0; itemIndex < sectionObjectCount; ++itemIndex) {
            DockUpgrade* u = sectionObjects[itemIndex];
            if (u == upgrade) {
                return [NSIndexPath indexPathForItem: itemIndex inSection: sectionIndex];
            }
        }
    }
    return nil;
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear: animated];
    [self setTitle: _upgradeTypeName];
    NSIndexPath* indexPath = nil;
    DockUpgrade* upgrade = _targetUpgrade.upgrade;

    if (_targetUpgrade) {
        if (![_targetUpgrade isPlaceholder]) {
            indexPath = [self indexPathForUpgrade: upgrade];
        }
    }

    if (_targetShip) {
        _overrideBarItem.enabled = YES;
        _overrideBarItem.title = @"Override";
    } else {
        _overrideBarItem.enabled = NO;
        _overrideBarItem.title = nil;
    }

    if (indexPath == nil && _targetShip) {
        NSString* faction = _targetShip.ship.faction;
        NSArray* sectionTitles = self.sections;
        id titleCheck = ^(id obj, NSUInteger idx, BOOL* stop) {
            id<NSFetchedResultsSectionInfo> sectionInfo = obj;
            return [[sectionInfo name] isEqualToString: faction];
        };
        NSInteger section = [sectionTitles indexOfObjectPassingTest: titleCheck];
        indexPath = [NSIndexPath indexPathForRow: 0 inSection: section];
    }

    if (indexPath != nil) {
        [self.tableView selectRowAtIndexPath: indexPath animated: YES scrollPosition: UITableViewScrollPositionMiddle];
    }
}

-(NSString*)entityName
{
    return @"Upgrade";
}

-(void)setUpType:(NSString*)upType
{
    if (![_upType isEqualToString: upType]) {
        _upType = upType;
        self.navigationController.title = upType;
    }

    self.fetchedResultsController = nil;
}

-(NSArray*)filterForCost:(NSArray*)rawList
{
    int requiredCost = self.cost;
    if (self.cost == 0) {
        return [NSArray arrayWithArray: rawList];
    }
    NSMutableArray* filtered = [NSMutableArray arrayWithCapacity: rawList.count];
    for (DockUpgrade* upgrade in rawList) {
        int upgradeCost = [[upgrade cost] intValue];
        if (_targetShip) {
            upgradeCost = [upgrade costForShip: _targetShip];
        }
        if (upgradeCost == requiredCost) {
            [filtered addObject: upgrade];
        }
    }
    return [NSArray arrayWithArray: filtered];
}

-(void)setupFetch:(NSFetchRequest*)fetchRequest context:(NSManagedObjectContext*)context
{
    [super setupFetch: fetchRequest context: context];
    NSArray* includedSets = self.includedSets;

    NSString* faction = self.faction;

    NSMutableArray* predicateTerms = [NSMutableArray arrayWithCapacity: 0];
    NSMutableArray* predicateValues = [NSMutableArray arrayWithCapacity: 0];

    [predicateTerms addObject: @"(not placeholder == YES)"];
    [predicateTerms addObject: @"(upType = %@)"];
    [predicateValues addObject: _upType];

    if (includedSets) {
        [predicateTerms addObject: @"any sets.externalId in %@"];
        [predicateValues addObject: includedSets];
    }

    if (faction != nil && [self useFactionFilter]) {
        [predicateTerms addObject: @"(faction = %@ or additionalFaction = %@)"];
        [predicateValues addObject: faction];
        [predicateValues addObject: faction];
    }

    NSString* searchTerm = self.searchTerm;
    if (searchTerm != nil) {
        [predicateTerms addObject: @"title CONTAINS[cd] %@"];
        [predicateValues addObject: searchTerm];
    }

    NSString* predicateTermString = [predicateTerms componentsJoinedByString: @" and "];
    NSPredicate *predicateTemplate = [NSPredicate predicateWithFormat: predicateTermString argumentArray: predicateValues];
    [fetchRequest setPredicate: predicateTemplate];
}

-(void)explainCantAddUpgrade:(NSError*)error
{
    NSDictionary* d = [error userInfo];
    UIAlertView* view = [[UIAlertView alloc] initWithTitle: d[NSLocalizedDescriptionKey]
                                                   message: d[NSLocalizedFailureReasonErrorKey]
                                                  delegate: nil
                                         cancelButtonTitle: nil
                                         otherButtonTitles: @"OK", nil];
    [view show];
}

#pragma mark - Table view data source methods

// Customize the appearance of table view cells.
-(void)configureCell:(UITableViewCell*)cell atIndexPath:(NSIndexPath*)indexPath
{

    // Configure the cell to show the book's title
    DockUpgrade* upgrade = [self objectAtIndexPath: indexPath];
    cell.textLabel.text = [upgrade title];

    if (_targetShip) {
        BOOL canAdd = [_targetSquad canAddUpgrade: upgrade toShip: _targetShip error: nil];

        if (!canAdd && (_targetUpgrade.upgrade != upgrade)) {
            cell.textLabel.textColor = [UIColor grayColor];
        } else {
            cell.textLabel.textColor = [UIColor blackColor];
        }

        int actualCost = [upgrade costForShip: _targetShip];
        int cost = [[upgrade cost] intValue];

        if (cost == actualCost) {
            cell.detailTextLabel.text = [upgrade.cost stringValue];
        } else {
            cell.detailTextLabel.text = [NSString stringWithFormat: @"%d (%@)", actualCost, upgrade.cost];
        }
    } else {
        cell.textLabel.textColor = [UIColor blackColor];
        cell.detailTextLabel.text = [[upgrade cost] stringValue];
    }
}

-(BOOL)tableView:(UITableView*)tableView shouldHighlightRowAtIndexPath:(NSIndexPath*)indexPath
{
    if (_targetSquad) {
        DockUpgrade* upgrade = [self objectAtIndexPath: indexPath];
        NSError* error;

        if (_targetUpgrade.upgrade == upgrade || [_targetSquad canAddUpgrade: upgrade toShip: _targetShip error: &error]) {
            return YES;
        }

        [self explainCantAddUpgrade: error];
        return NO;
    }

    return YES;
}

-(void)tableView:(UITableView*)tableView didSelectRowAtIndexPath:(NSIndexPath*)indexPath
{
    if (_targetSquad) {
        DockUpgrade* upgrade = [self objectAtIndexPath: indexPath];
        _onUpgradePicked(upgrade, _overridden, _overrideCost);
        [self clearTarget];
    } else {
        [self performSegueWithIdentifier: @"ShowUpgradeDetails" sender: self];
    }
}

-(void)tableView:(UITableView*)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath*)indexPath
{
    [self.tableView selectRowAtIndexPath: indexPath animated: NO scrollPosition: UITableViewScrollPositionMiddle];
    [self performSegueWithIdentifier: @"ShowUpgradeDetails" sender: self];
}

-(void)targetSquad:(DockSquad*)squad onPicked:(DockUpgradePicked)onPicked
{
    [self targetSquad: squad ship: nil upgrade: nil onPicked: onPicked];
}

-(void)clearTarget
{
    _targetSquad = nil;
    _targetShip = nil;
    _targetUpgrade = nil;
    _onUpgradePicked = nil;
}

-(void)targetSquad:(DockSquad*)squad ship:(DockEquippedShip*)ship onPicked:(DockUpgradePicked)onPicked
{
    [self targetSquad: squad ship: ship upgrade: nil onPicked: onPicked];
}

-(void)targetSquad:(DockSquad*)squad ship:(DockEquippedShip*)ship upgrade:(DockEquippedUpgrade*)upgrade onPicked:(DockUpgradePicked)onPicked
{
    _wasOverridden = [upgrade.overridden boolValue];
    _targetSquad = squad;
    _targetShip = ship;
    _targetUpgrade = upgrade;
    _onUpgradePicked = onPicked;
}

#pragma mark - Navigation

-(BOOL)shouldPerformSegueWithIdentifier:(NSString*)identifier sender:(id)sender
{
    return NO;
}

-(void)prepareForSegue:(UIStoryboardSegue*)segue sender:(id)sender
{
    NSString* identifier = [segue identifier];
    id destination = [segue destinationViewController];

    if ([identifier isEqualToString: @"ShowUpgradeDetails"]) {
        DockUpgradeDetailViewController* controller = (DockUpgradeDetailViewController*)destination;
        NSIndexPath* indexPath = [self.tableView indexPathForSelectedRow];
        DockUpgrade* upgrade = [self objectAtIndexPath: indexPath];
        controller.upgrade = upgrade;
    }
}

#pragma mark - Override Cost

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex == 1) {
        _overridden = NO;
        DockUpgrade* upgrade = _targetUpgrade.upgrade;
        _onUpgradePicked(upgrade, false, 0);
    } else if (buttonIndex == 2) {
        _overridden = YES;
        UITextField* textField = [alertView textFieldAtIndex: 0];
        _overrideCost = [textField.text intValue];
        DockUpgrade* upgrade = _targetUpgrade.upgrade;
        _onUpgradePicked(upgrade, YES, _overrideCost);
    }
}

-(IBAction)overrideCost:(id)sender
{
    if (isOS7OrGreater()) {
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle: @"Override Cost"
                                                         message: @""
                                                        delegate: self
                                               cancelButtonTitle: @"Cancel"
                                               otherButtonTitles: @"Restore", @"Override", nil];
        alert.alertViewStyle = UIAlertViewStylePlainTextInput;
        UITextField* textField = [alert textFieldAtIndex: 0];
        textField.keyboardType = UIKeyboardTypeNumbersAndPunctuation;
        textField.clearButtonMode = UITextFieldViewModeWhileEditing;
        textField.placeholder = @"cost";
        textField.textAlignment = NSTextAlignmentCenter;
        [alert show];
    } else {
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle: @"Not Supported"
                                                         message: @"This feature is only supported on iOS 7 or greater."
                                                        delegate: nil
                                               cancelButtonTitle: @"Cancel"
                                               otherButtonTitles: nil];
        [alert show];
    }
}

@end
