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
