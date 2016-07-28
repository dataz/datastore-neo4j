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
