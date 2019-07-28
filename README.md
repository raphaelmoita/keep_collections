# Keep Collections API

Keep Collection API is a simple way to keep collection data store wherever you want to, being restored automatically at instantiation time. 

## How to use
```Java
	String keepFile = "/tmp/list_db.keep";
	List<String> list = new ArrayList<String>();
	IKeeper<List<String>> keeper = new KeeperSerializer<List<String>>(keepFile);
	list = KeepList.create(list, keeper);
	// from that point on, all changes on list will be reflected on specified keepFile.
```

## Keepers

The API contains just one Keeper, the KeeperSerializer! The KeeperSerializer, as the name says, serializes the whole collection object into disk at each collection update, and recorver it from there at inicialization time.

> KeeperSerializer recovery rule is based on the DB file used to create the object

## How to create a Keeper

Keeper creation process must follow just one rule ... implement **org.moita.keep.keeper.IKeeper** interface. IKeeper inteface define 3 basic methods used to store, restore and check if there is any previous data to recover. 

> Concurrency on storing and recovering data must be observed at time to create a Keeper
