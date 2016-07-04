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
import org.failearly.dataz.junit4.AbstractDataSetTest;
import org.failearly.dataz.template.generator.ListGenerator;
import org.failearly.dataz.template.generator.RandomRangeGenerator;
import org.junit.Test;

/**
 * Neo4jDataStoreTest contains tests for ... .
 */
@DataCleanup
public class Neo4jDataStoreTest extends AbstractDataSetTest {

    @Test
    @DataSet(datastores = Neo4JDefaultDataStore.class, setup = "Neo4jDataStoreTest-use_template.setup.neo4j.vm")
    @ListGenerator(name = "datastores", values={"Oracle","H2","Neo4J","MongoDB"})
    @RandomRangeGenerator(name = "datastoreIds", seed=1)
    @RandomRangeGenerator(name = "datasetIds", seed=2)
    @ListGenerator(name = "datasets", values={"ds1","ds2","ds3"})
    // @SuppressCleanup
    public void use_template() throws Exception {
    }
}