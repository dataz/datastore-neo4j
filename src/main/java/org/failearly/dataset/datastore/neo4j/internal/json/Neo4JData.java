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

package org.failearly.dataset.datastore.neo4j.internal.json;

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
