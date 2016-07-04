/*
 * Copyright (c) 2009.
 *
 * Date: 20.05.16
 * 
 */
package org.failearly.dataz.datastore.neo4j;

/**
 * Neo4JConfigProperties holds the properties used by {@link org.failearly.dataz.datastore.neo4j.internal.Neo4jDataStoreImpl}.
 */
public interface Neo4JConfigProperties {
    String NEO4J_URL = "neo4j.url";
    String NEO4J_COMMIT_PATH = "neo4j.path";
    String NEO4J_DEFAULT_COMMIT_PATH = "/db/data/transaction/commit";
}
