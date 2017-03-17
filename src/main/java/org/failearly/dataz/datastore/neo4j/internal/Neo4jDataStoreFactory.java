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

package org.failearly.dataz.datastore.neo4j.internal;

import org.failearly.dataz.datastore.DataStore;
import org.failearly.dataz.datastore.DataStoreFactory;
import org.failearly.dataz.datastore.neo4j.Neo4jDataStore;
import org.failearly.dataz.NamedDataStore;

/**
 * Neo4jDataStoreFactory is responsible for ...
 */
public final class Neo4jDataStoreFactory implements DataStoreFactory<Neo4jDataStore> {
    @Override
    public DataStore createDataStore(Class<? extends NamedDataStore> namedDataStore, Neo4jDataStore dataStoreAnnotation) {
        return new Neo4jDataStoreImplementation(namedDataStore, dataStoreAnnotation);
    }
}
