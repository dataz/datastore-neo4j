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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Neo4JRelationship is responsible for ...
 */
@SuppressWarnings("unused")
public final class Neo4JRelationship extends Neo4JEntity {

    private String type;
    private String startNode;
    private String endNode;

    public Neo4JRelationship(@JsonProperty("id")String id) {
        super(id);
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
