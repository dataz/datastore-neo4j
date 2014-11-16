/*
 * dataSet - Test Support For Datastores.
 *
 * Copyright (C) 2014-2014 Marko Umek (http://fail-early.com/contact)
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
package org.failearly.dataset.datastore.neo4j;

import org.failearly.dataset.datastore.DataStore;
import org.failearly.dataset.datastore.DataStoreBase;
import org.failearly.dataset.internal.tmc.TestMethod;
import org.failearly.dataset.simplefile.DataSetParseException;

/**
 * Neo4jDataStore is responsible for ...
 */
final class Neo4jDataStore extends DataStoreBase {
    Neo4jDataStore(String dataStoreId, String dataStoreConfig) {
        super(dataStoreId, dataStoreConfig);
    }

    @Override
    public DataStore initialize() {
        // TODO: Implement Neo4jDataStore#initialize
        throw new UnsupportedOperationException("initialize not yet implemented");

    }

    @Override
    public void setup(TestMethod testMethod) throws DataSetParseException {
        // TODO: Implement Neo4jDataStore#setup
        throw new UnsupportedOperationException("setup not yet implemented");

    }

    @Override
    public void cleanup(TestMethod testMethod) throws DataSetParseException {
        // TODO: Implement Neo4jDataStore#cleanup
        throw new UnsupportedOperationException("cleanup not yet implemented");

    }

    @Override
    public void dispose() {
        // TODO: Implement Neo4jDataStore#dispose
        throw new UnsupportedOperationException("dispose not yet implemented");
    }
}
