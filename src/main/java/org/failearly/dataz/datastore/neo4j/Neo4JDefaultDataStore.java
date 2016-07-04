/*
 * Copyright (c) 2009.
 *
 * Date: 20.05.16
 * 
 */
package org.failearly.dataz.datastore.neo4j;

import org.failearly.dataz.DataCleanup;
import org.failearly.dataz.DataSet;
import org.failearly.dataz.DataSetup;
import org.failearly.dataz.NamedDataStore;

/**
 * Neo4JDefaultDataStore is the default {@link org.failearly.dataz.datastore.DataStore} used for Neo4J DataStores,
 * in case you do not provide your own default.
 * <br><br>
 * The default {@code DataStore} instance will be, if you don't set {@link DataSet#datastores()},
 * {@link DataSetup#datastores()} or {@link DataCleanup#datastores()}.
 * <br><br>
 *
 * @see Neo4JConfigProperties
 */
@Neo4jDataStore(config = "/neo4j-datastore.properties")
public final class Neo4JDefaultDataStore extends NamedDataStore {
}
