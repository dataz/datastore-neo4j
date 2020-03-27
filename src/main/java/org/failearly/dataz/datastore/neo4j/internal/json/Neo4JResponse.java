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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataz.datastore.neo4j.internal.Neo4JDataStoreException;

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
