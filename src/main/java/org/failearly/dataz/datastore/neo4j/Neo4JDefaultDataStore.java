/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2020 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
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
@SuppressWarnings("WeakerAccess")
@Neo4jDataStore(config = Neo4JDefaultDataStore.CONFIG_FILE)
public final class Neo4JDefaultDataStore extends NamedDataStore {
    /**
     * Contains the configuration for your default Neo4J datastore.
     *
     * The expected properties could be found in {@link Neo4JConfigProperties}.
     *
     * @see Neo4JConfigProperties
     * @see Neo4jDataStore#config()
     */
    public static final String CONFIG_FILE="/sql-datastore.properties";

}
