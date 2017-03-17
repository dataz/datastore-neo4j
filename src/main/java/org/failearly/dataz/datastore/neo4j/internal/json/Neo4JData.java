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

import java.util.List;

/**
 * Neo4JData collects the row and graph property.
 */
@SuppressWarnings("unused")
public final class Neo4JData {
    private List<?> row;

    private Neo4JGraph graph;

    public List<?> getRow() {
        return row;
    }
    public void setRow(List<?> row) {
        this.row = row;
    }

    public Neo4JGraph getGraph() {
        return graph;
    }

    public void setGraph(Neo4JGraph graph) {
        this.graph = graph;
    }
}
