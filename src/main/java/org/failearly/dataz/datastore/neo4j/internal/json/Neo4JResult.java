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

import java.util.Collections;
import java.util.List;

/**
 * Neo4JResult represents a single result entry of Neo4J JSON response.
 */
@SuppressWarnings("unused")
public final class Neo4JResult {
    private List<String> columns = Collections.emptyList();
    private List<Neo4JData> dataList = Collections.emptyList();

    public List<?> getData() {
        return dataList;
    }

    public void setData(List<Neo4JData> data) {
        this.dataList = data;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

}
