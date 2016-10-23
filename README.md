Keep Collections API
====================

Keep Collection API is a simple way to keep collection data store wherever you want to, being restored automatically at instantiation time. 

Keepers are the ones who knows how to save and restore data.

- Projeto Eclipse Neon
- Java 1.8

###How to build
Using Ant you can run "ant -buildfile build.xml export" to create a jar file (keep_collections_1.0.0.jar).

###How to use
```Java
	String keepFile = "/tmp/list_db.keep";
	List<String> list = new ArrayList<String>();
	IKeeper<List<String>> keeper = new KeeperSerializer<List<String>>(keepFile);
	list = KeepList.create(list, keeper);
	// from that point on, all changes on list will be reflected on specified keepFile.
```

