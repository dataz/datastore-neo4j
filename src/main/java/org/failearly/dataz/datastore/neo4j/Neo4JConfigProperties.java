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

/**
 * Neo4JConfigProperties holds the keys of the properties expected/used by the Neo4J implementation.
 *
 * These property keys could be used by {@link Neo4jDataStore#properties()} or within the config file assigned to
 * {@link Neo4jDataStore#config()}.
 *
 * @see Neo4jDataStore
 * @see Neo4JDefaultDataStore
 * @see Neo4JDefaultDataStore#CONFIG_FILE
 */
public interface Neo4JConfigProperties {
    String NEO4J_URL = "neo4j.url";
    String NEO4J_COMMIT_PATH = "neo4j.path";
    String NEO4J_DEFAULT_COMMIT_PATH = "/db/data/transaction/commit";
}
