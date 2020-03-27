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

package org.failearly.dataz.datastore.neo4j.internal.json;

import java.util.List;

/**
 * Neo4JGraph is responsible for ...
 */
@SuppressWarnings("unused")
public final class Neo4JGraph {
    private List<Neo4JNode> nodes;
    private List<Neo4JRelationship> relationships;

    public List<Neo4JNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<Neo4JNode> nodes) {
        this.nodes = nodes;
    }

    public List<Neo4JRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Neo4JRelationship> relationships) {
        this.relationships = relationships;
    }
}
