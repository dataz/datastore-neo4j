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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataset.datastore.neo4j.internal.Neo4JDataStoreException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Neo4JResponse is the root object for some responses of Neo4J. Currently we are interested only in <i>results</i> and <i>errors</i>.
 */
@SuppressWarnings("unused")
public final class Neo4JResponse {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private List<Neo4JError> errors = Collections.emptyList();
    private List<Neo4JResult> results = Collections.emptyList();

    public static Neo4JResponse fromJson(String json) throws IOException {
        return objectMapper.readValue(json, Neo4JResponse.class);
    }

    public void setResults(List<Neo4JResult> results) {
        this.results = results;
    }

    public void setErrors(List<Neo4JError> errors) {
        this.errors = errors;
    }

    public void throwOnErrors() {
        if (!isOk()) {
            throw new Neo4JDataStoreException(this.errors);
        }
    }

    public boolean isOk() {
        return this.errors.isEmpty();
    }
}
