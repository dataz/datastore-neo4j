/*
 * dataSet - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2015 Marko Umek (http://fail-early.com/contact)
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataset.datastore.neo4j.internal.Neo4JDataStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Neo4JResults is responsible for ...
 */
public final class Neo4JResults {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Neo4JError> errors=new LinkedList<>();

    public static Neo4JResults fromJson(String json) throws IOException {
        return objectMapper.readValue(json, Neo4JResults.class);
    }

    @JsonIgnore
    public void setResults(List<Neo4JResult> results) {
        throw new UnsupportedOperationException("setResults() is not yet implemented");

    }

    public void setErrors(List<Neo4JError> errors) {
        this.errors.clear();
        this.errors.addAll(errors);
    }

    public void throwOnErrors() {
        if( ! isOk() ) {
            throw new Neo4JDataStoreException(this.errors);
        }
    }

    public boolean isOk() {
        return this.errors.isEmpty();
    }
}
