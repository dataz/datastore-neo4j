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
import org.failearly.dataz.template.generator.ListGenerator;
import org.failearly.dataz.template.generator.RandomRangeGenerator;
import org.junit.Test;

/**
 * Neo4jDataStoreTest contains tests for ... .
 */
@DataCleanup
public class Neo4jDataStoreTest {

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