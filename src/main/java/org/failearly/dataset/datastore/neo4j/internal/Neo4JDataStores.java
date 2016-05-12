/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2016 'Marko Umek' (http://fail-early.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.failearly.dataset.datastore.neo4j.internal;

import org.failearly.dataset.AdhocDataStore;
import org.failearly.dataset.datastore.DataStore;
import org.failearly.dataset.datastore.neo4j.Neo4jDataStore;

/**
 * Neo4JDataStores is responsible for ...
 */
public final class Neo4JDataStores {
    private Neo4JDataStores() {
    }

    public static DataStore createDataStore(Neo4jDataStore dataStoreAnnotation) {
        final Neo4jDataStoreImpl dataStore = new Neo4jDataStoreImpl(dataStoreAnnotation.id(), dataStoreAnnotation.config());
        dataStore.setSetupSuffix(dataStoreAnnotation.setupSuffix());
        dataStore.setCleanupSuffix(dataStoreAnnotation.cleanupSuffix());
        return dataStore;
    }

    public static DataStore createDataStore(AdhocDataStore annotation) {
        return new Neo4jDataStoreImpl(annotation.id(), annotation.config());
    }

}
