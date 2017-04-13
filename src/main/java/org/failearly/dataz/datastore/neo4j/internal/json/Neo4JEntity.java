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

package org.failearly.dataz.datastore.neo4j.internal.json;

import java.util.Map;

/**
 * Neo4JEntity is the base class for nodes and relations.
 */
@SuppressWarnings("unused")
public abstract class Neo4JEntity {
    private final String id;
    private Map<String,?> properties;

    protected Neo4JEntity(String id) {
        this.id = id;
    }

    public final String getId() {
        return id;
    }

    public final Map<String, ?> getProperties() {
        return properties;
    }

    public final void setProperties(Map<String, ?> properties) {
        this.properties = properties;
    }
}
