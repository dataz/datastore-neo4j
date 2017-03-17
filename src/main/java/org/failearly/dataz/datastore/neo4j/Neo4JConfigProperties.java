/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.failearly.dataz.datastore.neo4j;

import org.failearly.dataz.datastore.neo4j.internal.Neo4jDataStoreImplementation;

/**
 * Neo4JConfigProperties holds the properties used by {@link Neo4jDataStoreImplementation}.
 */
public interface Neo4JConfigProperties {
    String NEO4J_URL = "neo4j.url";
    String NEO4J_COMMIT_PATH = "neo4j.path";
    String NEO4J_DEFAULT_COMMIT_PATH = "/db/data/transaction/commit";
}
