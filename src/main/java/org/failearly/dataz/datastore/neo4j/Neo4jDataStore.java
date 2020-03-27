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

import org.failearly.dataz.common.Property;
import org.failearly.dataz.config.Constants;
import org.failearly.dataz.datastore.DataStore;
import org.failearly.dataz.datastore.DataStoreFactory;
import org.failearly.dataz.datastore.neo4j.internal.Neo4jDataStoreFactory;
import org.failearly.dataz.NamedDataStore;

import java.lang.annotation.*;

/**
 * The Datastore annotation for NEO4J based datastores.
 *
 * Assign in to your {@link NamedDataStore} class. A sample usage could be seen at {@link Neo4JDefaultDataStore}.
 *
 * @see Neo4JDefaultDataStore
 */
@SuppressWarnings("unused")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Neo4jDataStore.Neo4jDataStores.class)
@DataStoreFactory.Definition(factory = Neo4jDataStoreFactory.class)
public @interface Neo4jDataStore {
    /**
     * If your tests uses multiple datastores per {@link NamedDataStore}, you must identify each data store impl.
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
