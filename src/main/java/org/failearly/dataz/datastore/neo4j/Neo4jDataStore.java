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

import org.failearly.dataz.common.Property;
import org.failearly.dataz.config.Constants;
import org.failearly.dataz.datastore.DataStore;
import org.failearly.dataz.datastore.DataStoreFactory;
import org.failearly.dataz.datastore.neo4j.internal.Neo4jDataStoreFactory;
import org.failearly.dataz.NamedDataStore;

import java.lang.annotation.*;

/**
 * Neo4jDataStore is a datastore annotation for Neo4J.
 */
@SuppressWarnings("unused")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Neo4jDataStore.Neo4jDataStores.class)
@DataStoreFactory.Definition(factory = Neo4jDataStoreFactory.class)
public @interface Neo4jDataStore {
    /**
     * If your tests uses multiple datastores per {@link NamedDataStore}, you must identify each data store annotation.
     * <br><br>
     * Remark: {@link DataStore#getId()} uses usually {@code name},
     * the {@link NamedDataStore} and the actually {@link DataStore} implementation.
     *
     * @return the (unique) data store name.
     *
     * @see DataStore#getId()
     */
    String name() default Constants.DATAZ_DEFAULT_DATASTORE_NAME;

    /**
     * The datastore (optional) configuration file will be used by the actually DataStore Implementation. So what's inside these configuration property file depends
     * on the DataStore type. Could be overwritten by {@link #properties()}.
     *
     * @return the datastore configuration file(name).
     *
     * @see #properties()
     */
    String config() default Constants.DATAZ_NO_CONFIG_FILE;

    /**
     * Optional properties or named arguments (key value pairs). Overwrites the {@link #config()}.
     *
     * @return an array of {@link Property}.
     *
     * @see #config()
     */
    Property[] properties() default {};


    /**
     * Containing Annotation Type.
     *
     * Remark: This will be used by Java8 compiler.
     *
     * @see java.lang.annotation.Repeatable
     */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Neo4jDataStores {
        Neo4jDataStore[] value();
    }
}
