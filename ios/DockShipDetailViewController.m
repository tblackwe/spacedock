#import "DockShipDetailViewController.h"

#import "DockShip+Addons.h"

@interface DockShipDetailViewController ()
@property (nonatomic, assign) CGFloat labelWidth;
@end

@implementation DockShipDetailViewController

#pragma mark - Table view data source

-(void)viewDidLoad
{
    UITableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier: @"ability"];
    _labelWidth = self.tableView.bounds.size.width - cell.textLabel.bounds.size.width;
}

-(NSInteger)rowCount
{
    NSString *str = _ship.ability;
    if (str.length > 0) {
        return kShipDetailAbility+1;
    }
    return kShipDetailAbility;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self rowCount];
}

-(UITableViewCell*)cellForAbility:(UITableView*)tableView
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: @"ability"];
    cell.textLabel.text = @"Ability";
    cell.detailTextLabel.text = _ship.ability;
    cell.detailTextLabel.numberOfLines = 0;
    return cell;
}

-(UITableViewCell*)cellForUnique:(UITableView*)tableView
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: @"default"];
    cell.textLabel.text = @"Unique";
    NSString* value = nil;
    if ([_ship isUnique]) {
        value = @"Yes";
    } else {
        value = @"No";
    }
    cell.detailTextLabel.text = value;
    return cell;
}

-(UITableViewCell*)cellForActions:(UITableView*)tableView
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: @"default"];
    cell.textLabel.text = @"Actions";
    NSArray* actions = [_ship actionStrings];
    cell.detailTextLabel.text = [actions componentsJoinedByString: @", "];
    return cell;
}

-(UITableViewCell*)cell:(UITableView*)tableView forKey:(NSString*)key label:(NSString*)label
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: @"default"];
    cell.textLabel.text = label;
    id value = [_ship valueForKey: key];
    NSString* textValue = [NSString stringWithFormat: @"%@", value];
    cell.detailTextLabel.text = textValue;
    return cell;
}

enum {
    kShipDetailTitle,
    kShipDetailClass,
    kShipDetailFaction,
    kShipDetailCost,
    kShipDetailUnique,
    kShipDetailAttack,
    kShipDetailAgility,
    kShipDetailHull,
    kShipDetailShields,
    kShipDetailCrew,
    kShipDetailTech,
    kShipDetailWeapons,
    kShipDetailActions,
    kShipDetailAbility
};

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSInteger row = [indexPath indexAtPosition: 1];

    switch (row) {
    case kShipDetailTitle:
        return [self cell: tableView forKey: @"title" label: @"Title"];
    case kShipDetailClass:
        return [self cell: tableView forKey: @"shipClass" label: @"Class"];
    case kShipDetailFaction:
        return [self cell: tableView forKey: @"faction" label: @"Faction"];
    case kShipDetailCost:
        return [self cell: tableView forKey: @"cost" label: @"Cost"];
    case kShipDetailUnique:
        return [self cellForUnique: tableView];
    case kShipDetailAttack:
        return [self cell: tableView forKey: @"attack" label: @"Attack"];
    case kShipDetailAgility:
        return [self cell: tableView forKey: @"agility" label: @"Agility"];
    case kShipDetailHull:
        return [self cell: tableView forKey: @"hull" label: @"Hull"];
    case kShipDetailShields:
        return [self cell: tableView forKey: @"shield" label: @"Shields"];
    case kShipDetailCrew:
        return [self cell: tableView forKey: @"crew" label: @"Crew"];
    case kShipDetailTech:
        return [self cell: tableView forKey: @"tech" label: @"Tech"];
    case kShipDetailWeapons:
        return [self cell: tableView forKey: @"weapon" label: @"Weapon"];
    case kShipDetailActions:
        return [self cellForActions: tableView];
    }

    return [self cellForAbility: tableView];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    CGFloat rowHeight = tableView.rowHeight;
    NSInteger lastRowIndex = [self rowCount] - 1;
    NSInteger row = [indexPath indexAtPosition: 1];
    if (row == lastRowIndex) {
        NSString *str = _ship.ability;
        if (str.length > 0) {
            CGSize size = [str sizeWithFont:[UIFont systemFontOfSize:14] constrainedToSize:CGSizeMake(_labelWidth-40, 999) lineBreakMode:NSLineBreakByWordWrapping];
            return size.height+rowHeight/2;
        }
    }
    return rowHeight;
}

@end