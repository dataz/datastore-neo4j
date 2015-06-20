/*
 * dataSet - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2015 Marko Umek (http://fail-early.com/contact)
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

import org.failearly.dataset.*;
import org.failearly.dataset.datastore.neo4j.model.tenant.Tenants;
import org.failearly.dataset.junit4.AbstractDataSetTest;
import org.failearly.dataset.template.simple.Constant;
import org.junit.Test;

/**
 * Neo4jDataStoreTest contains tests for ... .
 */
@Neo4jDataStore
// @DataStoreSetup(setup = "/coshare.setup.neo4j.vm")
@DataCleanup
public class Neo4jDataStoreTest extends AbstractDataSetTest {

    @Test
    @DataSet
    public void use_static_resource() throws Exception {
        // assert / then
        // TODO: Test number of nodes and relations
    }

    @Test
    @Use(Tenants.class)
    @SuppressCleanup
    public void using_use_annotation() throws Exception {
    }

    @Test
    @DataSet(setup = "Neo4jDataStoreTest-template.setup.neo4j.vm")
    @Constant(name = "tenantId", value = "4711")
    @Constant(name = "tenantConfigId", value = "471100")
    @Constant(name = "adminId", value = "314000")
    @Constant(name = "mailId", value = "1234")
    @Constant(name = "tenantProxyId", value = "70815")
    @Constant(name = "tPersonId", value = "3534533")
    public void use_template() throws Exception {
        // assert / then
        // TODO: Test number of nodes and relations
    }

}