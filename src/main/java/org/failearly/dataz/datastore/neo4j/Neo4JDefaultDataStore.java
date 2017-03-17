/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
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
